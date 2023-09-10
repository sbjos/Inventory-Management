package test.service.category;

import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.CategoryDao;
import com.inventorymanagement.exception.CategoryNotFoundException;
import com.inventorymanagement.exception.InvalidAttributeException;
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
    void handleRequest_withValidCategoryName_deleteResult() {
        // GIVEN
        controller = Controller.builder()
                .withCategory(food.getCategoryName()).build();

        when(categoryDao.find(food.getCategoryName())).thenReturn(food);

        // WHEN
        CategoryResult result = deleteCategoryService.handleRequest(controller, null);

        // THEN
        assertEquals(food.getCategoryName(), result.getCategory().getCategoryName());
    }

    @Test
    void handleRequest_lowerCaseCategoryName_changesFirstLetterToUpperCase() {
        // GIVEN
        controller = Controller
                .builder()
                .withCategory("food").build();

        when(categoryDao.find(food.getCategoryName())).thenReturn(food);

        // WHEN
        CategoryResult result = deleteCategoryService.handleRequest(controller, null);

        // THEN
        assertEquals(food.getCategoryName(), result.getCategory().getCategoryName());
    }

    @Test
    void handleRequest_withNonExistentCategory_throwsCategoryNotFoundException() {
        // GIVEN
        controller = Controller
                .builder()
                .withCategory("nonexistant").build();

        when(categoryDao.find(food.getCategoryName())).thenReturn(food);

        // WHEN - THEN
        assertThrows(CategoryNotFoundException.class, () ->
                deleteCategoryService.handleRequest(controller, null),
                ("Unable to find this category to delete. It may not exist."));
    }

    @Test
    void handleRequest_emptyCategoryName_throwsInvalidAttributeException() {
        // GIVEN
        controller = Controller.builder()
                .withCategory(" ").build();

        when(categoryDao.find(food.getCategoryName())).thenReturn(food);

        // WHEN - // THEN
        assertThrows(InvalidAttributeException.class, () ->
                        deleteCategoryService.handleRequest(controller, null),
                ("Please enter a valid input"));
    }
}
