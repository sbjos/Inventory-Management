package com.inventorymanagement.service.item;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.ItemDao;
import com.inventorymanagement.dao.LocationDao;
import com.inventorymanagement.exception.InvalidAttributeException;
import com.inventorymanagement.exception.ItemAlreadyExistException;
import com.inventorymanagement.dao.CategoryDao;
import com.inventorymanagement.result.ItemResult;
import com.inventorymanagement.table.Item;
import com.inventorymanagement.utility.ModelConverter;

import javax.inject.Inject;

import static com.inventorymanagement.utility.ServiceUtility.*;

public class CreateItemService implements RequestHandler<Controller, ItemResult> {
    private final ItemDao itemDao;
    private final CategoryDao categoryDao;
    private final LocationDao locationDao;

    @Inject
    public CreateItemService(ItemDao itemDao, CategoryDao categoryDao, LocationDao locationDao) {
        this.itemDao = itemDao;
        this.categoryDao = categoryDao;
        this.locationDao = locationDao;
    }

    @Override
    public ItemResult handleRequest(Controller input, Context context) {
        String name = capitalizeFirstChar(input.getName());
        Integer quantity = input.getQuantity();
        String category = capitalizeFirstChar(input.getCategory());
        String location = input.getLocation().toUpperCase();
        Item existingItem = itemDao.find(name);

        // TODO: Be more specific on the input that is invalid.
        if (isEmpty(name)
                || quantity == null
                || isEmpty(category)
                || isEmpty(location))
            throw new InvalidAttributeException ("Please enter a valid input.");

        if (existingItem != null) throw new ItemAlreadyExistException
                (String.format("%s already exist. Please choose a different name.", existingItem.getItemName()));

        Item item = new Item();
        item.setItemName(name);
        item.setItemLocation(location);
        item.setItemCategory(category);
        item.setItemQuantity(quantity);
        item.setItemId(generateId());

        if (input.getQuantity() > 0) item.setAvailability("Available");
        else item.setAvailability("Unavailable");

        PaginatedQueryList<Item> existingId = itemDao.findById(item.getItemId());
        if (existingId != null) {
            for (Item itemId : existingId) {
                if (item.getItemId().equals(itemId.getItemId())) item.setItemId(generateId());
            }
        }

        itemDao.save(item);

        return new ItemResult.Builder()
                .withItem(new ModelConverter().itemConverter(item))
                .build();
    }
}
