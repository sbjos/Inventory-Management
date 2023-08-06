package com.inventorymanagement.service;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.ItemDao;
import com.inventorymanagement.exception.ItemNotFoundException;
import com.inventorymanagement.result.ItemResult;
import com.inventorymanagement.table.Item;
import com.inventorymanagement.utility.ModelConverter;

import javax.inject.Inject;

public class DeleteItemService implements RequestHandler<Controller, ItemResult> {
    private final ItemDao itemDao;

    @Inject
    public DeleteItemService(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @Override
    public ItemResult handleRequest(Controller input, Context context) {
        String itemName = input.getName();
        Item item = itemDao.find(itemName);

        // SafeGuard
        if (item == null) throw new ItemNotFoundException
                ("Unable to find this item to delete. It may not exist.");

        itemDao.delete(itemName);

        return ItemResult.builder()
                .withItem(new ModelConverter().itemConverter(item))
                .build();
    }
}
