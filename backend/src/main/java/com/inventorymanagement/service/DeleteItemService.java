package com.inventorymanagement.service;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.ItemDao;
import com.inventorymanagement.exception.InvalidAttributeException;
import com.inventorymanagement.exception.ItemNotFoundException;
import com.inventorymanagement.result.ItemResult;
import com.inventorymanagement.table.Item;
import com.inventorymanagement.utility.ModelConverter;

import javax.inject.Inject;

public class DeleteItemService implements RequestHandler<Controller, ItemResult> {
    private ItemDao itemDao;

    @Inject
    public DeleteItemService(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @Override
    public ItemResult handleRequest(Controller input, Context context) {
        String itemName = input.getName();
        String itemId = input.getId();
        Item item = null;

        if (itemName == null && itemId == null) throw new InvalidAttributeException
                ("Please enter a valid item name.");

        if (itemName != null) {
            item = itemDao.find(itemName);
            itemDao.delete(itemName);
        } else {
            item = itemDao.find(itemId);
            itemDao.delete(item.getName());
        }

        if (item == null) throw new ItemNotFoundException
                ("Unable to find this item. It may not exist.");

        return ItemResult.builder()
                .withItem(new ModelConverter().itemConverter(item))
                .build();
    }
}
