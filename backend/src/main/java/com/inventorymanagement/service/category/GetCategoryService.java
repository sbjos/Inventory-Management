package com.inventorymanagement.service.category;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.CategoryDao;
import com.inventorymanagement.exception.CategoryNotFoundException;
import com.inventorymanagement.exception.InvalidAttributeException;
import com.inventorymanagement.result.CategoryResult;
import com.inventorymanagement.table.Category;
import com.inventorymanagement.utility.ModelConverter;

import javax.inject.Inject;

import static com.inventorymanagement.utility.ServiceUtility.*;

public class GetCategoryService implements RequestHandler<Controller, CategoryResult> {
    private final CategoryDao categoryDao;

    @Inject
    public GetCategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public CategoryResult handleRequest(Controller input, Context context) {
        if (input.isAll()) return findAll();

        String categoryName = capitalizeFirstChar(input.getCategory());

        if (isEmpty(categoryName)) throw new InvalidAttributeException
                ("Please enter a valid input.");

        return find(categoryName);
    }

    private CategoryResult find(String categoryName) {
        Category category = categoryDao.find(categoryName);

        if (category == null) throw new CategoryNotFoundException
                (String.format("Unable to find %s category. It may not exist.", categoryName));

        return CategoryResult.builder()
                .withCategoryName(new ModelConverter().categoryConverter(category))
                .build();
    }

    private CategoryResult findAll() {
        return CategoryResult.builder()
                .withCategoryList(new ModelConverter().categoryListConverter(categoryDao.findAll()))
                .build();
    }
}
