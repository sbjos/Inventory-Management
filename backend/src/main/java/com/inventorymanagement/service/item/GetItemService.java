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
        String name = input.getName();
        String id = input.getId();
        String available = input.getAvailability();
        String category = input.getCategory();
        String location = input.getLocation();
        boolean findAll = input.isAll();

        if (location != null) return findByByLocation(location, category);
        if (category != null) return findByCategory(category, available);
        if (id != null) return findById(id);
        if (available != null) return findByByAvailability(available);
        if (findAll) return findAll();

        if (isEmpty(name)) throw new InvalidAttributeException
                ("Please enter a valid input.");

        return find(name);
    }

    private ItemResult find(String name) {
        Item item = itemDao.find(capitalizeFirstChar(name));

        if (item == null) throw new ItemNotFoundException
                (String.format("Unable to find %s. It may not exist.", name));

        return ItemResult.builder()
                .withItem(new ModelConverter().itemConverter(item))
                .build();
    }

    private ItemResult findById(String itemId) {
        PaginatedQueryList<Item> item = itemDao.findById(itemId);

        if (isEmpty(itemId)) throw new InvalidAttributeException
                ("Please enter a valid input.");

        if (item == null) throw new ItemNotFoundException
                (String.format("Unable to find item ID %s. It may not exist.", itemId));

        return ItemResult.builder()
                .withItemList(new ModelConverter().itemListConverter(item))
                .build();
    }

    private ItemResult findByByAvailability(String available) {
        String availability = capitalizeFirstChar(available);

        PaginatedQueryList<Item> availableList = itemDao.findByByAvailability(availability);

        if (isEmpty(availability)) throw new InvalidAttributeException
                ("Please enter a valid input.");

        if (availableList == null) throw new ItemNotFoundException
                (String.format("Unable to find item ID %s. It may not exist.", availability));

        return ItemResult.builder()
                .withItemList(new ModelConverter().itemListConverter(availableList))
                .build();
    }

    private ItemResult findByCategory(String category, String available) {
        String validCategoryName;
        String validAvailability;
        PaginatedQueryList<Item> categoryList;

        if (available != null) {
            validCategoryName = capitalizeFirstChar(category);
            validAvailability = capitalizeFirstChar(available);

            if (isEmpty(validCategoryName) || isEmpty(validAvailability)) throw new InvalidAttributeException
                    ("Please enter a valid input.");

            categoryList = itemDao.findByCategoryAndAvailability(validCategoryName, validAvailability);
        } else {

            validCategoryName = capitalizeFirstChar(category);
            categoryList = itemDao.findByCategory(validCategoryName);
        }

        if (isEmpty(validCategoryName)) throw new InvalidAttributeException
                ("Please enter a valid input.");

        if (categoryList == null || categoryList.isEmpty()) throw new CategoryNotFoundException
                (String.format("Unable to find the list of items with the %s category.", category));

        return ItemResult.builder()
                .withItemList(new ModelConverter().itemListConverter(categoryList))
                .build();
    }

    private ItemResult findByByLocation(String location, String category) {
        String validLocationName;
        String validCategoryName = null;
        PaginatedQueryList<Item> locationList;

        if (category != null) {
            validLocationName = location.toUpperCase();
            validCategoryName = capitalizeFirstChar(category);

            if (isEmpty(validLocationName) || isEmpty(validCategoryName)) throw new InvalidAttributeException
                    ("Please enter a valid input.");

            locationList = itemDao.findByByLocationAndCategory(validLocationName, validCategoryName);
        } else {

            validLocationName = location.toUpperCase();
            locationList = itemDao.findByByLocation(validLocationName);
        }

        if (isEmpty(validLocationName)) throw new InvalidAttributeException
                ("Please enter a valid input.");

        if (locationList == null || locationList.isEmpty()) throw new LocationNotFoundException
                (String.format("Unable to find the list of items in the %s location.", validLocationName));

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
