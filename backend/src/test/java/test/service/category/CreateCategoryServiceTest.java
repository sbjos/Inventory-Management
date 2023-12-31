package test.service.category;

import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.CategoryDao;
import com.inventorymanagement.exception.CategoryAlreadyExistException;
import com.inventorymanagement.exception.InvalidAttributeException;
import com.inventorymanagement.result.CategoryResult;
import com.inventorymanagement.service.category.CreateCategoryService;
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

public class CreateCategoryServiceTest {
    private final Category food = TestHelper.food();
    @InjectMocks
    CreateCategoryService createCategoryService;
    @Mock
    private CategoryDao categoryDao;
    private Controller controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleRequest_withExistingCategory_throwsCategoryAlreadyExistException() {
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
        String newCategory = "Fitness";

        controller = Controller
                .builder()
                .withCategory(newCategory)
                .build();

        // WHEN
        CategoryResult result = createCategoryService.handleRequest(controller, null);

        // THEN
        assertEquals(newCategory, result.getCategory().getCategoryName());
    }

    @Test
    void handleRequest_withEmptyCategoryName_throwsInvalidAttributeException() {
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

    @Test
    void handleRequest_withInvalidCategoryName_changesFirstLetterToUpperCase() {
        // GIVEN
        controller = Controller
                .builder()
                .withCategory("food")
                .build();

        // WHEN
        CategoryResult result = createCategoryService.handleRequest(controller, null);

        // THEN
        assertEquals(food.getCategoryName(), result.getCategory().getCategoryName());
    }
}
