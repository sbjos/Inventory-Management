package test.service.item;

import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.CategoryDao;
import com.inventorymanagement.dao.ItemDao;
import com.inventorymanagement.dao.LocationDao;
import com.inventorymanagement.exception.InvalidAttributeException;
import com.inventorymanagement.exception.ItemNotFoundException;
import com.inventorymanagement.result.ItemResult;
import com.inventorymanagement.service.item.UpdateItemService;
import com.inventorymanagement.table.Category;
import com.inventorymanagement.table.Item;
import com.inventorymanagement.table.Location;
import com.inventorymanagement.test.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class UpdateItemServiceTest {
    private final Item ramen = TestHelper.ramenNoodle();
    private final Location locationR2 = TestHelper.locationR2();
    @InjectMocks
    private UpdateItemService updateItemService;
    @Mock
    private ItemDao itemDao;
    @Mock
    private CategoryDao categoryDao;
    @Mock
    private LocationDao locationDao;
    private Controller controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleRequest_withValidName_ReturnUpdatedItem() {
        // GIVEN
        Category category = new Category();
        category.setCategoryName("Quick Easy Food");

        controller = Controller.builder()
                .withName(ramen.getItemName())
                .withCategory(category.getCategoryName())
                .withLocation(locationR2.getLocationName())
                .build();

        when(categoryDao.find(category.getCategoryName())).thenReturn(category);
        when(locationDao.find(locationR2.getLocationName())).thenReturn(locationR2);
        when(itemDao.find(ramen.getItemName())).thenReturn(ramen);

        // WHEN
        ItemResult result = updateItemService.handleRequest(controller, null);

        // THEN
        assertEquals(ramen.getItemName(), result.getItem().getItemName());
        assertEquals(ramen.getItemId(), result.getItem().getItemId());
        assertEquals(ramen.getItemCategory(), result.getItem().getItemCategory());
        assertEquals(ramen.getAvailability(), result.getItem().getAvailability());
        assertEquals(ramen.getItemQuantity(), result.getItem().getItemQuantity());
        assertEquals(ramen.getItemLocation(), result.getItem().getItemLocation());
    }

    @Test
    void handleRequest_withInvalidName_throwsItemNotFoundException() {
        // GIVEN
        int availQuantity = 5;

        controller = Controller.builder()
                .withName("invalidName")
                .withQuantity(availQuantity)
                .withLocation(locationR2.getLocationName())
                .build();

        when(itemDao.find(ramen.getItemName())).thenReturn(ramen);

        ramen.setItemQuantity(availQuantity);
        ramen.setItemLocation(locationR2.getLocationName());

        // WHEN - // THEN
        assertThrows(ItemNotFoundException.class, () ->
                updateItemService.handleRequest(controller, null),
                ("Unable to find this item to update. It may not exist."));
    }

    @Test
    void handleRequest_withEmptyName_throwsInvalidAttributeException() {
        // GIVEN
        int availQuantity = 5;

        controller = Controller.builder()
                .withName(" ")
                .withQuantity(availQuantity)
                .withLocation(locationR2.getLocationName())
                .build();

        when(itemDao.find(ramen.getItemName())).thenReturn(ramen);

        // WHEN - // THEN
        assertThrows(InvalidAttributeException.class, () ->
                        updateItemService.handleRequest(controller, null),
                ("Please enter a valid input"));
    }

    @Test
    void handleRequest_withEmptyAttribute_keepsPreviousValueAssigned() {
        // GIVEN
        controller = Controller.builder()
                .withName(ramen.getItemName())
                .withLocation(locationR2.getLocationName())
                .build();

        when(locationDao.find(locationR2.getLocationName())).thenReturn(locationR2);
        when(itemDao.find(ramen.getItemName())).thenReturn(ramen);

        // WHEN
        ItemResult result = updateItemService.handleRequest(controller, null);

        // THEN
        assertEquals(TestHelper.ramenNoodle().getItemName(), result.getItem().getItemName());
        assertEquals(TestHelper.ramenNoodle().getItemId(), result.getItem().getItemId());
        assertEquals(TestHelper.ramenNoodle().getItemCategory(), result.getItem().getItemCategory());
        assertEquals(TestHelper.ramenNoodle().getAvailability(), result.getItem().getAvailability());
        assertEquals(TestHelper.ramenNoodle().getItemQuantity(), result.getItem().getItemQuantity());
        assertNotEquals(TestHelper.ramenNoodle().getItemLocation(), result.getItem().getItemLocation());
    }
}
