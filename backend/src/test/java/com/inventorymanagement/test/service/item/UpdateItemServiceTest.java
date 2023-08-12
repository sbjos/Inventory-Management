package com.inventorymanagement.test.service.item;

import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.exception.InvalidAttributeException;
import com.inventorymanagement.exception.ItemNotFoundException;
import com.inventorymanagement.dao.ItemDao;
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

import static com.inventorymanagement.test.TestHelper.locationDF1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class UpdateItemServiceTest {
    private final Item ramen = TestHelper.ramenNoodle();
    private final Location locationR2 = TestHelper.locationR2();
    @InjectMocks
    private UpdateItemService updateItemService;
    @Mock
    private ItemDao itemDao;
    private Controller controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleRequest_withValidName_ReturnUpdatedItem() {
        // GIVEN
        Category category = new Category();
        category.setCategoryName("Quick and Easy Food");

        controller = Controller.builder()
                .withName(ramen.getItemName())
                .withCategory(category.getCategoryName())
                .withLocation(locationR2.getLocationName())
                .build();

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

        when(itemDao.find(ramen.getItemName())).thenThrow(ItemNotFoundException.class);

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

        ramen.setItemQuantity(availQuantity);
        ramen.setItemLocation(locationR2.getLocationName());

        // WHEN - // THEN
        assertThrows(InvalidAttributeException.class, () ->
                        updateItemService.handleRequest(controller, null),
                ("Please enter a valid input"));
    }

    @Test
    void handleRequest_withEmptyAttribute_keepsPreviousValueAssigned() {
        Category category = new Category();
        category.setCategoryName("");

        Item item = new Item();
        item.setItemName("Ramen Noodle");
        item.setItemId("A125");
        item.setItemCategory("Food");
        item.setAvailability("Unavailable");
        item.setItemQuantity(0);
        item.setItemLocation(locationDF1().getLocationName());

        controller = Controller.builder()
                .withName(ramen.getItemName())
                .withCategory(category.getCategoryName())
                .withLocation(locationDF1().getLocationName())
                .build();

        when(itemDao.find(ramen.getItemName())).thenReturn(ramen);

        // WHEN
        ItemResult result = updateItemService.handleRequest(controller, null);

        // THEN
        System.out.println(result.getItem());
        System.out.println(item);
        assertEquals(item.getItemName(), result.getItem().getItemName());
        assertEquals(item.getItemId(), result.getItem().getItemId());
        assertEquals(item.getItemCategory(), result.getItem().getItemCategory());
        assertEquals(item.getAvailability(), result.getItem().getAvailability());
        assertEquals(item.getItemQuantity(), result.getItem().getItemQuantity());
        assertEquals(item.getItemLocation(), result.getItem().getItemLocation());
    }
}
