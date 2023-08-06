package com.inventorymanagement.test.service;

import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.exception.ItemNotFoundException;
import com.inventorymanagement.dao.ItemDao;
import com.inventorymanagement.result.ItemResult;
import com.inventorymanagement.service.UpdateItemService;
import com.inventorymanagement.table.Category;
import com.inventorymanagement.table.Item;
import com.inventorymanagement.test.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class UpdateItemServiceTest {
    private final Item ramen = TestHelper.ramenNoodle();
    private final int availQuantity = 5;
    private final String location = "A24";
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
        category.setCategory("Quick and Easy Food");

        controller = Controller.builder()
                .withName(ramen.getItemName())
                .withCategory(category.getCategory())
                .withLocation(location)
                .build();

        when(itemDao.find(controller.getName())).thenReturn(ramen);

        // WHEN
        ItemResult result = updateItemService.handleRequest(controller, null);

        // THEN
        assertEquals(ramen.getItemName(), result.getItem().getName());
        assertEquals(ramen.getId(), result.getItem().getId());
        assertEquals(ramen.getCategory(), result.getItem().getCategory());
        assertEquals(ramen.isAvailable(), result.getItem().isAvailable());
        assertEquals(ramen.getQuantity(), result.getItem().getQuantity());
        assertEquals(ramen.getLocation(), result.getItem().getLocation());
    }

    @Test
    void handleRequest_withInvalidName_throwsItemNotFoundException() {
        // GIVEN
        controller = Controller.builder()
                .withName("invalidName")
                .withQuantity(availQuantity)
                .withLocation(location)
                .build();

        when(itemDao.find(controller.getName())).thenThrow(ItemNotFoundException.class);

        ramen.setQuantity(availQuantity);
        ramen.setLocation(location);


        // WHEN - // THEN
        assertThrows(ItemNotFoundException.class, () ->
                updateItemService.handleRequest(controller, null),
                ("Unable to find this item to update. It may not exist."));
    }
}
