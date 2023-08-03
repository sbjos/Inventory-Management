package com.inventorymanagement.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.inventorymanagement.configuration.DaggerInventoryManagementAppComponent;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.result.ItemResult;
import com.inventorymanagement.service.UpdateItemService;

public class UpdateItemHandler implements RequestHandler<Controller, ItemResult> {

    @Override
    public ItemResult handleRequest(Controller input, Context context) {
        return updateItemService().handleRequest(input, context);
    }

    private UpdateItemService updateItemService() {
        return DaggerInventoryManagementAppComponent.create().provideUpdateItemService();
    }
}
