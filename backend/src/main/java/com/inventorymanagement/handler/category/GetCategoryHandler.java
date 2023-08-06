package com.inventorymanagement.handler.category;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.inventorymanagement.configuration.DaggerInventoryManagementAppComponent;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.result.CategoryResult;
import com.inventorymanagement.service.category.GetCategoryService;

public class GetCategoryHandler implements RequestHandler<Controller, CategoryResult> {

    @Override
    public CategoryResult handleRequest(Controller input, Context context) {
        return getCategoryService().handleRequest(input, context);
    }

    private GetCategoryService getCategoryService() {
        return DaggerInventoryManagementAppComponent.create().provideGetCategoryService();
    }
}
