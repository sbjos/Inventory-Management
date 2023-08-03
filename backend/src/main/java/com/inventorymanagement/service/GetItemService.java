package com.inventorymanagement.service;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.ItemDao;
import com.inventorymanagement.exception.CategoryNotFoundException;
import com.inventorymanagement.exception.ItemNotFoundException;
import com.inventorymanagement.result.ItemResult;
import com.inventorymanagement.table.Item;
import com.inventorymanagement.utility.ModelConverter;

import javax.inject.Inject;

public class GetItemService implements RequestHandler<Controller, ItemResult> {
    private ItemDao itemDao;

    @Inject
    public GetItemService(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @Override
    public ItemResult handleRequest(Controller input, Context context) {
        String itemName = input.getName();
        String itemId = input.getId();
        String category = input.getCategory();
        String available = input.isAvailable();

        if (itemId != null) return findById(itemId);
        if (category != null) return findByCategory(category, itemName);
        if (available != null) return findByByAvailability(available, itemName);

        return find(itemName);
    }

    private ItemResult find(String itemName) {
        Item item = itemDao.find(itemName);

        if (item == null) throw new ItemNotFoundException
                ("Unable to find this item. It may not exist.");

        return ItemResult.builder()
                .withItem(new ModelConverter().itemConverter(item))
                .build();
    }

    private ItemResult findById(String itemId) {
        PaginatedQueryList<Item> itemList = itemDao.findById(itemId);
        Item item = null;

        if (itemList == null) throw new ItemNotFoundException
                ("Unable to find this item. It may not exist.");

        for (Item list : itemList) {
            if (list.getId().equals(itemId)) item = list;
            break;
        }

        return ItemResult.builder()
                .withItem(new ModelConverter().itemConverter(item))
                .build();
    }

    private ItemResult findByCategory(String category, String name) {
        PaginatedQueryList<Item> categoryList;

        if (name != null) categoryList = itemDao.findByCategory(category, name);
        else categoryList = itemDao.findByCategory(category);

        if (categoryList == null) throw new CategoryNotFoundException
                ("Unable to find items in this category.");

        return ItemResult.builder()
                .withItemList(new ModelConverter().itemListConverter(categoryList))
                .build();
    }

    private ItemResult findByByAvailability(String available, String name) {
        PaginatedQueryList<Item> availableList;

        if (name != null) availableList = itemDao.findByByAvailability(available, name);
        else availableList = itemDao.findByByAvailability(available);

        return ItemResult.builder()
                .withItemList(new ModelConverter().itemListConverter(availableList))
                .build();
    }
}