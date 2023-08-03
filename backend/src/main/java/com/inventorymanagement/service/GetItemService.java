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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GetItemService implements RequestHandler<Controller, ItemResult> {
    private ItemDao itemDao;

    @Inject
    public GetItemService(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @Override
    public ItemResult handleRequest(Controller input, Context context) {
//        String itemName = input.getName();
//        String itemId = input.getId();
//        Item item = null;
//
//        if (itemName == null && itemId == null) throw new InvalidAttributeException
//                ("Please enter a valid item name.");
//
//        if (itemName != null) item = itemDao.find(itemName);
//        else item = itemDao.find(itemId);
//
//        if (item == null) throw new ItemNotFoundException
//                ("Unable to find this item. It may not exist.");
//
//        return ItemResult.builder()
//                .withItem(new ModelConverter().itemConverter(item))
//                .build();

        String itemName = input.getName();
        String itemId = input.getId();
        String category = input.getCategory();
        String available = input.isAvailable();

//        if (itemName == null && itemId == null) throw new InvalidAttributeException
//                ("Please enter a valid item name.");

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
        Set<Item> itemCategory = new HashSet<>();
        Item item = new Item();

        if (name != null) categoryList = itemDao.findByCategory(category, name);
        else categoryList = itemDao.findByCategory(category);

        if (categoryList == null) throw new CategoryNotFoundException
                ("Unable to find items in this category.");

//        for (Item list : categoryList) {
//            if (list.getCategory().equals(category)) {
//                item.setName(list.getName());
//                item.setId(list.getId());
//                item.setCategory(list.getCategory());
//                item.setQuantity(list.getQuantity());
//                item.setAvailable(list.isAvailable());
//                item.setLocation(list.getLocation());
//
//                itemCategory.add(item);
//            }
//        }

        return ItemResult.builder()
                .withItemList(new ModelConverter().itemListConverter(categoryList))
                .build();
    }

    private ItemResult findByByAvailability(String available, String name) {
        PaginatedQueryList<Item> availableList;
        Set<Item> itemAvailability = new HashSet<>();
        Item item = new Item();

        if (name != null) availableList = itemDao.findByByAvailability(available, name);
        else availableList = itemDao.findByByAvailability(available);


//        for (Item list : availableList) {
//            if (list.isAvailable().equals(available)){
//
//                item.setName(list.getName());
//                item.setId(list.getId());
//                item.setCategory(list.getCategory());
//                item.setQuantity(list.getQuantity());
//                item.setAvailable(list.isAvailable());
//                item.setLocation(list.getLocation());
//
//                itemAvailability.add(list);
//            }
//        }

        return ItemResult.builder()
                .withItemList(new ModelConverter().itemListConverter(availableList))
                .build();
    }
}
