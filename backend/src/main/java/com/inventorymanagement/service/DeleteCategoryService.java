package com.inventorymanagement.service;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.CategoryDao;
import com.inventorymanagement.exception.CategoryNotFoundException;
import com.inventorymanagement.exception.InvalidAttributeException;
import com.inventorymanagement.model.CategoryModel;
import com.inventorymanagement.result.CategoryResult;
import com.inventorymanagement.table.Category;

import javax.inject.Inject;

public class DeleteCategoryService implements RequestHandler<Controller, CategoryResult> {
    private CategoryDao categoryDao;

    @Inject
    public DeleteCategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public CategoryResult handleRequest(Controller input, Context context) {
        String categoryName = input.getCategory();
        Category category = categoryDao.find(categoryName);

        if (categoryName == null) throw new InvalidAttributeException
                ("Please enter a valid category name.");


        if (category == null) throw new CategoryNotFoundException
                ("Unable to find this item. It may not exist.");

        return CategoryResult.builder()
                .withCategory(CategoryModel.builder()
                        .withCategory(categoryName)
                        .build())
                .build();
    }
}
