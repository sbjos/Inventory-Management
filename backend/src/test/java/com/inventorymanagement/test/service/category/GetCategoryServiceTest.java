package com.inventorymanagement.test.service.category;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.inventorymanagement.configuration.awsglobalsecondaryindex.AwsGsiItem;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.CategoryDao;
import com.inventorymanagement.exception.CategoryNotFoundException;
import com.inventorymanagement.result.CategoryResult;
import com.inventorymanagement.service.category.GetCategoryService;
import com.inventorymanagement.table.Category;
import com.inventorymanagement.test.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class GetCategoryServiceTest {
    private final Category food = TestHelper.food();
    private final Category music = TestHelper.music();
    @InjectMocks
    GetCategoryService getCategoryService;
    @Mock
    private CategoryDao categoryDao;
    @Mock
    private AwsGsiItem awsGsiItem;
    @Mock
    private PaginatedQueryList<Category> categoryPaginatedQueryList;
    private Controller controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleRequest_validCategoryName_returnResult() {
        // GIVEN
        controller = Controller.builder()
                .withCategory(food.getCategoryName()).build();

        when(categoryDao.find(controller.getCategory())).thenReturn(food);

        // WHEN
        CategoryResult result = getCategoryService.handleRequest(controller, null);

        // THEN
        assertEquals(food.getCategoryName(), result.getCategory().getCategory());
    }
    @Test
    void handleRequest_findAll_returnAllItems() {
        //GIVEN
        List<Category> existingItem = new ArrayList<>();
        existingItem.add(food);
        existingItem.add(music);

        controller = Controller.builder()
                .withFindAll(true).build();

        when(categoryPaginatedQueryList.size()).thenReturn(existingItem.size());
        when(categoryPaginatedQueryList.isEmpty()).thenReturn(false);
        when(categoryPaginatedQueryList.iterator()).thenReturn(existingItem.iterator());
        when(categoryPaginatedQueryList.stream()).thenReturn(existingItem.stream());

        when(categoryDao.findAll()).thenReturn(categoryPaginatedQueryList);

        // WHEN
        CategoryResult result = getCategoryService.handleRequest(controller, null);

        // THEN
        assertNotNull(result);
    }

    @Test
    void handleRequest_categoryDoesNotExist_returnCategoryNotFoundException() {
        // GIVEN
        String nonExisting = "nonExisting";

        controller = Controller.builder()
                .withCategory(nonExisting).build();

        when(categoryDao.find(controller.getCategory())).thenThrow(CategoryNotFoundException.class);

        // WHEN - // THEN
        assertThrows(CategoryNotFoundException.class, () ->
                        getCategoryService.handleRequest(controller, null),
                (String.format("Unable to find %s. It may not exist.", nonExisting)));
    }
}