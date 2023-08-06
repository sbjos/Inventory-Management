package com.inventorymanagement.handler.item;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.inventorymanagement.configuration.DaggerInventoryManagementAppComponent;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.result.ItemResult;
import com.inventorymanagement.service.item.DeleteItemService;

public class DeleteItemHandler implements RequestHandler<Controller, ItemResult> {

    @Override
    public ItemResult handleRequest(Controller input, Context context) {
        return deleteItemService().handleRequest(input, context);
    }

    private DeleteItemService deleteItemService() {
        return DaggerInventoryManagementAppComponent.create().provideDeleteItemService();
    }
}
