package com.inventorymanagement.service.item;

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

import static com.inventorymanagement.utility.ServiceUtility.*;

public class UpdateItemService implements RequestHandler<Controller, ItemResult> {
    private final ItemDao itemDao;
    private final CategoryDao categoryDao;

    @Inject
    public UpdateItemService(ItemDao itemDao, CategoryDao categoryDao) {
        this.itemDao = itemDao;
        this.categoryDao = categoryDao;
    }

    @Override
    public ItemResult handleRequest(Controller input, Context context) {
        String itemName = capitalizeFirstChar(input.getName());
        String category = input.getCategory();
        Integer quantity = input.getQuantity();
        String location = input.getLocation();
        Item item = null;

        item = itemDao.find(itemName);

        // SafeGuard
        if (isEmpty(itemName)) throw new InvalidAttributeException
                ("Please enter a valid input");

        if (item == null) throw new ItemNotFoundException
                ("Unable to find this item to update. It may not exist.");

        if (isEmpty(category)) item.setItemCategory(capitalizeFirstChar(item.getItemCategory()));
        else item.setItemCategory(category);

        if (quantity == null) item.setItemQuantity(item.getItemQuantity());
        else item.setItemQuantity(quantity);

        if (item.getItemQuantity() > 0) item.setAvailability("Available");
        else item.setAvailability("Unavailable");

        if (isEmpty(location)) item.setItemLocation(capitalizeFirstChar(item.getItemLocation()));
        else item.setItemLocation(location);

        itemDao.save(item);

        return new ItemResult.Builder()
                .withItem(new ModelConverter().itemConverter(item))
                .build();
    }
}
