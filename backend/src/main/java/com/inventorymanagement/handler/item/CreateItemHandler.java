package com.inventorymanagement.handler.item;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.inventorymanagement.configuration.DaggerInventoryManagementAppComponent;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.result.ItemResult;
import com.inventorymanagement.service.item.CreateItemService;

public class CreateItemHandler implements RequestHandler<Controller, ItemResult> {

    @Override
    public ItemResult handleRequest(Controller input, Context context) {
        return createItemService().handleRequest(input, context);
    }

    private CreateItemService createItemService() {
        return DaggerInventoryManagementAppComponent.create().provideCreateItemService();
    }
}
