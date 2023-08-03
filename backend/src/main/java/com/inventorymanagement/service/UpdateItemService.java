package com.inventorymanagement.service;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.CategoryDao;
import com.inventorymanagement.dao.ItemDao;
import com.inventorymanagement.exception.InvalidAttributeException;
import com.inventorymanagement.exception.ItemNotFoundException;
import com.inventorymanagement.result.ItemResult;
import com.inventorymanagement.table.Item;
import com.inventorymanagement.utility.ModelConverter;

import javax.inject.Inject;

public class UpdateItemService implements RequestHandler<Controller, ItemResult> {
    private ItemDao itemDao;
    private CategoryDao categoryDao;

    @Inject
    public UpdateItemService(ItemDao itemDao, CategoryDao categoryDao) {
        this.itemDao = itemDao;
        this.categoryDao = categoryDao;
    }

    @Override
    public ItemResult handleRequest(Controller input, Context context) {
        String itemName = input.getName();
        String itemId = input.getId();
        String category = input.getCategory();
        Integer quantity = input.getQuantity();
        String location = input.getLocation();
        Item item = null;

        // TODO: Name and ID should not have update options. Once the user gets the item, they
        //  have the option to update that item. No availability to update the name or the ID.

        if (itemName == null && itemId == null) throw new InvalidAttributeException
                ("Please enter a valid item name.");
        if (itemName != null) item = itemDao.find(itemName);
        if (itemName == null) item = itemDao.find(itemId);
        if (item == null) throw new ItemNotFoundException
                ("Unable to find this item. It may not exist.");

        if (category == null) item.setCategory(item.getCategory());
        else item.setCategory(category);

        if (quantity == null) item.setQuantity(item.getQuantity());
        else item.setQuantity(quantity);

        if (item.getQuantity() > 0) item.setAvailable("True");
        else item.setAvailable("False");

        if (location == null) item.setLocation(item.getLocation());
        else item.setLocation(location);

        itemDao.save(item);

        return new ItemResult.Builder()
                .withItem(new ModelConverter().itemConverter(item))
                .build();
    }
}
