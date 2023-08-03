package com.inventorymanagement.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.inventorymanagement.configuration.DaggerInventoryManagementAppComponent;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.result.CategoryResult;
import com.inventorymanagement.service.DeleteCategoryService;

public class DeleteCategoryHandler implements RequestHandler<Controller, CategoryResult> {

    @Override
    public CategoryResult handleRequest(Controller input, Context context) {
        return deleteCategoryService().handleRequest(input, context);
    }

    private DeleteCategoryService deleteCategoryService() {
        return DaggerInventoryManagementAppComponent.create().provideDeleteCategoryService();
    }
}
