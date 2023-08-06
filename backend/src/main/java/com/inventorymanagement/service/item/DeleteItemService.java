package com.inventorymanagement.service.item;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.ItemDao;
import com.inventorymanagement.model.ItemModel;
import com.inventorymanagement.result.ItemResult;

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

        itemDao.delete(itemName);

        return ItemResult.builder()
                .withItem(ItemModel.builder().withName(itemName).build())
                .build();
    }
}
