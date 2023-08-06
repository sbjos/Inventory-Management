package com.inventorymanagement.handler.item;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.inventorymanagement.configuration.DaggerInventoryManagementAppComponent;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.result.ItemResult;
import com.inventorymanagement.service.item.GetItemService;

public class GetItemHandler implements RequestHandler<Controller, ItemResult> {

    @Override
    public ItemResult handleRequest(Controller input, Context context) {
        return getItemService().handleRequest(input, context);
    }

    private GetItemService getItemService() {
        return DaggerInventoryManagementAppComponent.create().provideGetItemService();
    }
}
