package com.inventorymanagement.test.service;

import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.exception.CategoryNotFoundException;
import com.inventorymanagement.exception.InvalidAttributeException;
import com.inventorymanagement.exception.ItemNotFoundException;
import com.inventorymanagement.dao.CategoryDao;
import com.inventorymanagement.result.CategoryResult;
import com.inventorymanagement.service.DeleteCategoryService;
import com.inventorymanagement.table.Category;
import com.inventorymanagement.test.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class DeleteCategoryServiceTest {
    private final Category food = TestHelper.food();
    @InjectMocks
    DeleteCategoryService deleteCategoryService;
    @Mock
    private CategoryDao categoryDao;
    private Controller controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleRequest_withValidCategory_deleteResult() {
        // GIVEN
        controller = Controller.builder()
                .withCategory(food.getCategoryName()).build();

        when(categoryDao.find(controller.getCategory())).thenReturn(food);

        // WHEN
        CategoryResult result = deleteCategoryService.handleRequest(controller, null);

        // THEN
        assertEquals(food.getCategoryName(), result.getCategory().getCategory());
    }

    @Test
    void handleRequest_withInvalidCategory_throwsInvalidAttributeException() {
        // GIVEN
        controller = Controller.builder()
                .withCategory(null).build();

        when(categoryDao.find(controller.getCategory())).thenReturn(food);

        // WHEN - // THEN
        assertThrows(InvalidAttributeException.class, () ->
                        deleteCategoryService.handleRequest(controller, null),
                ("Please enter a valid category name."));
    }

    @Test
    void handleRequest_CategoryDoesNotExist_returnCategoryNotFoundException() {
        // GIVEN
        controller = Controller.builder()
                .withCategory("nonExistingCategory").build();

        when(categoryDao.find(controller.getName())).thenThrow(ItemNotFoundException.class);

        // WHEN - // THEN
        assertThrows(CategoryNotFoundException.class, () ->
                        deleteCategoryService.handleRequest(controller, null),
                ("Unable to find this item. It may not exist."));
    }
}
