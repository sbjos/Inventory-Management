package com.inventorymanagement.service;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.CategoryDao;
import com.inventorymanagement.exception.CategoryAlreadyExistException;
import com.inventorymanagement.exception.InvalidAttributeException;
import com.inventorymanagement.result.CategoryResult;
import com.inventorymanagement.table.Category;
import com.inventorymanagement.utility.ModelConverter;
import com.inventorymanagement.utility.ServiceUtility;

import javax.inject.Inject;

public class CreateCategoryService implements RequestHandler<Controller, CategoryResult> {
    private final CategoryDao categoryDao;

    @Inject
    public CreateCategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public CategoryResult handleRequest(Controller input, Context context) {
        String categoryName = input.getCategory();
        Category existingCategory = categoryDao.find(categoryName);

        if (existingCategory != null) throw new CategoryAlreadyExistException
                (String.format("%s already exist. Please choose a different category.", existingCategory));

        if (ServiceUtility.isValid(categoryName)) throw new InvalidAttributeException
                ("Please enter a valid category name.");

        Category category = new Category();
        category.setCategory(categoryName);

        categoryDao.save(category);

        return new CategoryResult.Builder()
                .withCategory(new ModelConverter().categoryConverter(category))
                .build();
    }
}
