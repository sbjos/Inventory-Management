package test.service.category;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.CategoryDao;
import com.inventorymanagement.exception.CategoryNotFoundException;
import com.inventorymanagement.exception.InvalidAttributeException;
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
    private PaginatedScanList<Category> categoryPaginatedScanList;
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

        when(categoryDao.find(food.getCategoryName())).thenReturn(food);

        // WHEN
        CategoryResult result = getCategoryService.handleRequest(controller, null);

        // THEN
        assertEquals(food.getCategoryName(), result.getCategory().getCategoryName());
    }

    @Test
    void handleRequest_emptyCategoryName_throwsInvalidAttributeException() {
        // GIVEN
        controller = Controller.builder()
                .withCategory(" ").build();

        when(categoryDao.find(food.getCategoryName())).thenReturn(food);

        // WHEN - // THEN
        assertThrows(InvalidAttributeException.class, () ->
                getCategoryService.handleRequest(controller, null),
                ("Please enter a valid input"));
    }

    @Test
    void handleRequest_lowerCaseCategoryName_changesFirstLetterToUpperCase() {
        // GIVEN
        controller = Controller.builder()
                .withCategory("food").build();

        when(categoryDao.find(food.getCategoryName())).thenReturn(food);

        // WHEN
        CategoryResult result = getCategoryService.handleRequest(controller, null);

        // THEN
        System.out.println(result.getCategory().getCategoryName());
        assertEquals(food.getCategoryName(), result.getCategory().getCategoryName());
    }

    @Test
    void handleRequest_findAll_returnAllItems() {
        //GIVEN
        List<Category> existingItem = new ArrayList<>();
        existingItem.add(food);
        existingItem.add(music);

        controller = Controller.builder()
                .withAll(true).build();

        when(categoryPaginatedScanList.size()).thenReturn(existingItem.size());
        when(categoryPaginatedScanList.isEmpty()).thenReturn(false);
        when(categoryPaginatedScanList.iterator()).thenReturn(existingItem.iterator());
        when(categoryPaginatedScanList.stream()).thenReturn(existingItem.stream());

        when(categoryDao.findAll()).thenReturn(categoryPaginatedScanList);

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

        when(categoryDao.find(food.getCategoryName())).thenReturn(food);

        // WHEN - // THEN
        assertThrows(CategoryNotFoundException.class, () ->
                        getCategoryService.handleRequest(controller, null),
                (String.format("Unable to find %s. It may not exist.", nonExisting)));
    }
}