package com.inventorymanagement.service.item;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.ItemDao;
import com.inventorymanagement.exception.InvalidAttributeException;
import com.inventorymanagement.exception.ItemNotFoundException;
import com.inventorymanagement.model.ItemModel;
import com.inventorymanagement.result.ItemResult;
import com.inventorymanagement.table.Item;

import javax.inject.Inject;

import static com.inventorymanagement.utility.ServiceUtility.*;

public class DeleteItemService implements RequestHandler<Controller, ItemResult> {
    private final ItemDao itemDao;

    @Inject
    public DeleteItemService(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @Override
    public ItemResult handleRequest(Controller input, Context context) {
        String itemName = capitalizeFirstChar(input.getName());
        Item item = itemDao.find(itemName);

        if (isEmpty(itemName)) throw new InvalidAttributeException
                ("Please enter a valid input");

        if (item == null) throw new ItemNotFoundException
                ("Unable to find this item to delete. It may not exist.");

        itemDao.delete(item);

        return ItemResult.builder()
                .withItem(ItemModel.builder().withItemName(itemName).build())
                .build();
    }
}
