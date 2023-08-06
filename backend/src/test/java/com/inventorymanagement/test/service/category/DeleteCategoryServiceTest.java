package com.inventorymanagement.test.service.category;

import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.CategoryDao;
import com.inventorymanagement.result.CategoryResult;
import com.inventorymanagement.service.category.DeleteCategoryService;
import com.inventorymanagement.table.Category;
import com.inventorymanagement.test.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
