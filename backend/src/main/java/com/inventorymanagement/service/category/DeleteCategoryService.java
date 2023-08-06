package com.inventorymanagement.service.category;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.CategoryDao;
import com.inventorymanagement.exception.CategoryNotFoundException;
import com.inventorymanagement.exception.ItemNotFoundException;
import com.inventorymanagement.model.CategoryModel;
import com.inventorymanagement.result.CategoryResult;
import com.inventorymanagement.table.Category;

import javax.inject.Inject;

public class DeleteCategoryService implements RequestHandler<Controller, CategoryResult> {
    private final CategoryDao categoryDao;

    @Inject
    public DeleteCategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    // TODO: Check what happens if the item that is to be deleted does not exist
    @Override
    public CategoryResult handleRequest(Controller input, Context context) {
        String category = input.getCategory();

        categoryDao.delete(category);

        return CategoryResult.builder()
                .withCategory(CategoryModel.builder().withCategory(category).build())
                .build();
    }
}
