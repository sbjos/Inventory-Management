package com.inventorymanagement.service.category;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.CategoryDao;
import com.inventorymanagement.exception.CategoryAlreadyExistException;
import com.inventorymanagement.exception.InvalidAttributeException;
import com.inventorymanagement.result.CategoryResult;
import com.inventorymanagement.table.Category;
import com.inventorymanagement.utility.ModelConverter;

import javax.inject.Inject;

import static com.inventorymanagement.utility.ServiceUtility.*;

public class CreateCategoryService implements RequestHandler<Controller, CategoryResult> {
    private final CategoryDao categoryDao;

    @Inject
    public CreateCategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public CategoryResult handleRequest(Controller input, Context context) {
        String categoryName = capitalizeFirstChar(input.getCategory());
        Category existingCategory = categoryDao.find(categoryName);

        if (isEmpty(categoryName)) throw new InvalidAttributeException
                ("Please enter a valid input.");

        if (existingCategory != null) throw new CategoryAlreadyExistException
                (String.format("%s already exist. Please choose a different category.", existingCategory.getCategoryName()));

        Category category = new Category();
        category.setCategoryName(categoryName);

        categoryDao.save(category);

        return new CategoryResult.Builder()
                .withCategoryName(new ModelConverter().categoryConverter(category))
                .build();
    }
}
