package com.inventorymanagement.service.item;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.ItemDao;
import com.inventorymanagement.exception.*;
import com.inventorymanagement.result.ItemResult;
import com.inventorymanagement.table.Item;
import com.inventorymanagement.utility.ModelConverter;

import javax.inject.Inject;

import static com.inventorymanagement.utility.ServiceUtility.*;

public class GetItemService implements RequestHandler<Controller, ItemResult> {
    private final ItemDao itemDao;

    @Inject
    public GetItemService(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @Override
    public ItemResult handleRequest(Controller input, Context context) {
        String name = capitalizeFirstChar(input.getName());
        String id = input.getId();
        String available = capitalizeFirstChar(input.getAvailability());
        String category = capitalizeFirstChar(input.getCategory());
        String location = toUpperCase(input.getLocation());
        boolean findAll = input.isAll();

        if (location != null) return findByByLocation(location, category);
        if (category != null) return findByCategory(category, available);
        if (id != null) return findById(id);
        if (available != null) return findByByAvailability(available);
        if (findAll) return findAll();

        if (isEmpty(name)) throw new InvalidAttributeException
                ("Please enter a valid input.");

        return findByName(name);
    }

    private ItemResult findByName(String name) {
        if (isEmpty(name)) throw new InvalidAttributeException
                ("Please enter a valid input.");

        Item item = itemDao.find(name);

        if (item == null) throw new ItemNotFoundException
                (String.format("Unable to find %s. It may not exist.", name));

        return ItemResult.builder()
                .withItem(new ModelConverter().itemConverter(item))
                .build();
    }

    private ItemResult findById(String itemId) {
        if (isEmpty(itemId)) throw new InvalidAttributeException
                ("Please enter a valid input.");

        PaginatedQueryList<Item> item = itemDao.findById(itemId);

        if (item == null || item.isEmpty()) throw new ItemNotFoundException
                (String.format("Unable to find item ID %s. It may not exist.", itemId));

        return ItemResult.builder()
                .withItemList(new ModelConverter().itemListConverter(item))
                .build();
    }

    private ItemResult findByByAvailability(String availability) {
        if (isEmpty(availability)) throw new InvalidAttributeException
                ("Please enter a valid input.");

        PaginatedQueryList<Item> availableList = itemDao.findByByAvailability(availability);

        if (availableList.isEmpty()) throw new ItemNotFoundException("Unable to find list of items");

        return ItemResult.builder()
                .withItemList(new ModelConverter().itemListConverter(availableList))
                .build();
    }

    private ItemResult findByCategory(String category, String available) {
        PaginatedQueryList<Item> categoryList;

        if (available != null) {
            if (isEmpty(category) || isEmpty(available)) throw new InvalidAttributeException
                    ("Please enter a valid input.");

            categoryList = itemDao.findByCategoryAndAvailability(category, available);
        } else {

            categoryList = itemDao.findByCategory(category);
        }

        if (isEmpty(category)) throw new InvalidAttributeException
                ("Please enter a valid input.");

        if (categoryList == null || categoryList.isEmpty()) throw new CategoryNotFoundException
                (String.format("Unable to find items with %s category.", category));

        return ItemResult.builder()
                .withItemList(new ModelConverter().itemListConverter(categoryList))
                .build();
    }

    private ItemResult findByByLocation(String location, String category) {
        PaginatedQueryList<Item> locationList;

        if (category != null) {
            if (isEmpty(location) || isEmpty(category)) throw new InvalidAttributeException
                    ("Please enter a valid input.");

            locationList = itemDao.findByByLocationAndCategory(location, category);
        } else {

            locationList = itemDao.findByByLocation(location);
        }

        if (isEmpty(location)) throw new InvalidAttributeException
                ("Please enter a valid input.");

        if (locationList == null || locationList.isEmpty()) throw new LocationNotFoundException
                (String.format("Unable to find items in %s location.", location));

        return ItemResult.builder()
                .withItemList(new ModelConverter().itemListConverter(locationList))
                .build();
    }

    private ItemResult findAll() {
        return ItemResult.builder()
                .withItemList(new ModelConverter().itemListConverter(itemDao.findAll()))
                .build();
    }
}
