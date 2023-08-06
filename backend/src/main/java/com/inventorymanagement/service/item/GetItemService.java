package com.inventorymanagement.service.item;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.ItemDao;
import com.inventorymanagement.exception.CategoryNotFoundException;
import com.inventorymanagement.exception.ItemListNotFoundException;
import com.inventorymanagement.exception.ItemNotFoundException;
import com.inventorymanagement.exception.LocationNotFoundException;
import com.inventorymanagement.result.ItemResult;
import com.inventorymanagement.table.Item;
import com.inventorymanagement.utility.ModelConverter;

import javax.inject.Inject;

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
        String available = input.isAvailable();
        String category = input.getCategory();
        String location = input.getLocation();
        boolean findAll = input.FindAll();

        if (location != null) return findByByLocation(location, category);
        if (category != null) return findByCategory(category, available);
        if (id != null) return findById(id);
        if (available != null) return findByByAvailability(available);
        if (findAll) return findAll();

        if (name == null) throw new ItemListNotFoundException
                ("Unable to find the list of items");

        return find(name);
    }

    private ItemResult find(String name) {
        Item item = itemDao.find(name);

        if (item == null) throw new ItemNotFoundException
                (String.format("Unable to find %s. It may not exist.", name));

        return ItemResult.builder()
                .withItem(new ModelConverter().itemConverter(item))
                .build();
    }

    private ItemResult findById(String itemId) {
        PaginatedQueryList<Item> item = itemDao.findById(itemId);

        if (item == null) throw new ItemNotFoundException
                (String.format("Unable to find item ID %s. It may not exist.", itemId));

        return ItemResult.builder()
                .withItemList(new ModelConverter().itemListConverter(item))
                .build();
    }

    private ItemResult findByByAvailability(String available) {
        PaginatedQueryList<Item> availableList = itemDao.findByByAvailability(available);

        return ItemResult.builder()
                .withItemList(new ModelConverter().itemListConverter(availableList))
                .build();
    }

    private ItemResult findByCategory(String category, String available) {
        PaginatedQueryList<Item> categoryList;

        if (available != null) categoryList = itemDao.findByCategoryAndAvailability(category, available);
         else categoryList = itemDao.findByCategory(category);

        // SafeGuard - Category will be provided as a list in the UI
        if (categoryList == null) throw new CategoryNotFoundException
                (String.format("Unable to find the %s category.", category));

        for (Item list : categoryList) {
            if (list == null) throw new ItemListNotFoundException
                    (String.format("Unable to find the list of items in the %s category.", category));
        }

        return ItemResult.builder()
                .withItemList(new ModelConverter().itemListConverter(categoryList))
                .build();
    }

    private ItemResult findByByLocation(String location, String category) {
        PaginatedQueryList<Item> locationList;

        if (category != null) locationList = itemDao.findByByLocationAndCategory(location, category);
        else locationList = itemDao.findByByLocation(location);

        // SafeGuard - Location will be provided as a list in the UI
        if (locationList == null) throw new LocationNotFoundException
                (String.format("Unable to find the %s. location", location));

        for (Item list : locationList) {
            if (list == null) throw new ItemListNotFoundException
                    (String.format("Unable to find the %s category in the %s location.", category, location));
        }

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
