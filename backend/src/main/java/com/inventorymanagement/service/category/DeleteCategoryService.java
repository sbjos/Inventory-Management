package com.inventorymanagement.service.category;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.CategoryDao;
import com.inventorymanagement.exception.CategoryNotFoundException;
import com.inventorymanagement.exception.InvalidAttributeException;
import com.inventorymanagement.model.CategoryModel;
import com.inventorymanagement.result.CategoryResult;
import com.inventorymanagement.table.Category;
import com.inventorymanagement.utility.ServiceUtility;

import javax.inject.Inject;

import static com.inventorymanagement.utility.ServiceUtility.*;

public class DeleteCategoryService implements RequestHandler<Controller, CategoryResult> {
    private final CategoryDao categoryDao;

    @Inject
    public DeleteCategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public CategoryResult handleRequest(Controller input, Context context) {
        String categoryName = capitalizeFirstChar(input.getCategory());

        Category category = categoryDao.find(categoryName);

        if (ServiceUtility.isEmpty(categoryName)) throw new InvalidAttributeException
                ("Please enter a valid input.");

        // SafeGuard
        if (category == null) throw new CategoryNotFoundException
                ("Unable to find this category to delete. It may not exist.");

        categoryDao.delete(category);

        return CategoryResult.builder()
                .withCategoryName(CategoryModel.builder().withCategoryName(categoryName).build())
                .build();
    }
}
