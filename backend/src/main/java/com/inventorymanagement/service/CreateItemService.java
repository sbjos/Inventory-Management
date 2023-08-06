package com.inventorymanagement.service;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.ItemDao;
import com.inventorymanagement.exception.InvalidAttributeException;
import com.inventorymanagement.exception.ItemAlreadyExistException;
import com.inventorymanagement.dao.CategoryDao;
import com.inventorymanagement.result.ItemResult;
import com.inventorymanagement.table.Item;
import com.inventorymanagement.utility.ModelConverter;
import com.inventorymanagement.utility.ServiceUtility;

import javax.inject.Inject;

public class CreateItemService implements RequestHandler<Controller, ItemResult> {
    private final ItemDao itemDao;
    private final CategoryDao categoryDao;

    @Inject
    public CreateItemService(ItemDao itemDao, CategoryDao categoryDao) {
        this.itemDao = itemDao;
        this.categoryDao = categoryDao;
    }

    @Override
    public ItemResult handleRequest(Controller input, Context context) {
        String itemName = input.getName();
        Item existingItem = itemDao.find(itemName);

        if (existingItem != null) throw new ItemAlreadyExistException
                (String.format("%s already exist. Please choose a different name.", existingItem.getItemName()));

        if (ServiceUtility.isValid(itemName)) throw new InvalidAttributeException
                ("Please enter a valid item name.");

        Item item = new Item();
        item.setItemName(itemName);

        // FIXME: Generate ID in uppercase Only
        item.setId(ServiceUtility.generateId());

        //TODO: See if there is a more efficient way to compare existing item ID in the database to avoid duplicate
        PaginatedQueryList<Item> existingId = itemDao.findById(item.getId());
        for (Item itemId : existingId) {
            if (item.getId().equals(itemId.getId())) item.setId(ServiceUtility.generateId());
        }

        item.setCategory(input.getCategory());
        item.setQuantity(input.getQuantity());

        if (input.getQuantity() > 0) item.setAvailable("Available");
        else item.setAvailable("Unavailable");

        item.setLocation(input.getLocation());

        itemDao.save(item);

        return new ItemResult.Builder()
                .withItem(new ModelConverter().itemConverter(item))
                .build();
    }
}
