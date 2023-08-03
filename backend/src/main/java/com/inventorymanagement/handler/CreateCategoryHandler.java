package com.inventorymanagement.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.inventorymanagement.configuration.DaggerInventoryManagementAppComponent;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.result.CategoryResult;
import com.inventorymanagement.service.CreateCategoryService;

public class CreateCategoryHandler implements RequestHandler<Controller, CategoryResult> {

    @Override
    public CategoryResult handleRequest(Controller input, Context context) {
        return createCategoryService().handleRequest(input, context);
    }

    private CreateCategoryService createCategoryService() {
        return DaggerInventoryManagementAppComponent.create().provideCreateCategoryService();
    }
}
