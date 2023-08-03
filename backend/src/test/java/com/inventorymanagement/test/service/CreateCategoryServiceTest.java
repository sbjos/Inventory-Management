package com.inventorymanagement.test.service;

import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.exception.CategoryAlreadyExistException;
import com.inventorymanagement.dao.CategoryDao;
import com.inventorymanagement.exception.InvalidAttributeException;
import com.inventorymanagement.result.CategoryResult;
import com.inventorymanagement.service.CreateCategoryService;
import com.inventorymanagement.table.Category;
import com.inventorymanagement.table.Item;
import com.inventorymanagement.test.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class CreateCategoryServiceTest {
    private final Item ramen = TestHelper.ramenNoodle();
    private final Item lettuce = TestHelper.lettuce();
    private final Category food = TestHelper.food();
    private final String validName = "Salmon";
    private final int availQuantity = 10;
    private final String location = "R12";
    @InjectMocks
    CreateCategoryService createCategoryService;
    @Mock
    private CategoryDao categoryDao;
    private Item item;
    private Controller controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        item = new Item();
        item.setName(validName);
        item.setCategory(food.getCategoryName());
        item.setAvailable("true");
        item.setQuantity(availQuantity);
        item.setLocation(location);
    }

    @Test
    void handleRequest_withExistingCategory_CategoryAlreadyExistException() {
        // GIVEN
        controller = Controller
                .builder()
                .withCategory(food.getCategoryName())
                .build();

        when(categoryDao.find(food.getCategoryName())).thenReturn(food);

        // WHEN - // THEN
        assertThrows(CategoryAlreadyExistException.class, () ->
                createCategoryService.handleRequest(controller, null),
                (String.format("%s already exist. Please choose a different category.", controller.getCategory())));
    }

    @Test
    void handleRequest_withValidCategoryName_returnsCategoryName() {
        // GIVEN
        controller = Controller
                .builder()
                .withCategory(food.getCategoryName())
                .build();

        // WHEN
        CategoryResult result = createCategoryService.handleRequest(controller, null);

        // THEN
        assertNotEquals(ramen.getId(), result.getCategory().getCategory());
        assertNotEquals(lettuce.getId(), result.getCategory().getCategory());
    }

    @Test
    void handleRequest_withInvalidCategoryName_returnsCategoryName() {
        // GIVEN
        controller = Controller
                .builder()
                .withCategory(" ")
                .build();

        // WHEN - THEN
        assertThrows(InvalidAttributeException.class, () ->
                createCategoryService.handleRequest(controller, null),
                ("Please enter a valid category name."));
    }
}
