package com.inventorymanagement.test.service;

import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.ItemDao;
import com.inventorymanagement.exception.InvalidAttributeException;
import com.inventorymanagement.exception.ItemNotFoundException;
import com.inventorymanagement.result.ItemResult;
import com.inventorymanagement.service.DeleteItemService;
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

public class DeleteItemServiceTest {
    private final Item ramen = TestHelper.ramenNoodle();
    @InjectMocks
    DeleteItemService deleteItemService;
    @Mock
    private ItemDao itemDao;
    private Controller controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleRequest_withValidName_deleteResult() {
        // GIVEN
        controller = Controller.builder()
                .withName(ramen.getName()).build();

        when(itemDao.find(controller.getName())).thenReturn(ramen);

        // WHEN
        ItemResult result = deleteItemService.handleRequest(controller, null);

        // THEN
        assertEquals(ramen.getName(), result.getItem().getName());
        assertEquals(ramen.getId(), result.getItem().getId());
        assertEquals(ramen.getCategory(), result.getItem().getCategory());
        assertEquals(ramen.isAvailable(), result.getItem().isAvailable());
        assertEquals(ramen.getQuantity(), result.getItem().getQuantity());
        assertEquals(ramen.getLocation(), result.getItem().getLocation());
    }

    @Test
    void handleRequest_withInvalidName_throwsInvalidAttributeException() {
        // GIVEN
        controller = Controller.builder()
                .withCategory(null).build();

        when(itemDao.find(" ")).thenThrow(InvalidAttributeException.class);

        // WHEN - // THEN
        assertThrows(InvalidAttributeException.class, () ->
                        deleteItemService.handleRequest(controller, null),
                ("Please enter a valid item name."));
    }

    @Test
    void handleRequest_withValidId_deletesResult() {
        // GIVEN
        controller = Controller.builder()
                .withId(ramen.getId()).build();

        when(itemDao.find(controller.getId())).thenReturn(ramen);

        // WHEN
        ItemResult result = deleteItemService.handleRequest(controller, null);

        // THEN
        assertEquals(ramen.getName(), result.getItem().getName());
        assertEquals(ramen.getId(), result.getItem().getId());
        assertEquals(ramen.getCategory(), result.getItem().getCategory());
        assertEquals(ramen.isAvailable(), result.getItem().isAvailable());
        assertEquals(ramen.getQuantity(), result.getItem().getQuantity());
        assertEquals(ramen.getLocation(), result.getItem().getLocation());
    }

    @Test
    void handleRequest_itemDoesNotExist_returnItemNotFoundException() {
        // GIVEN
        controller = Controller.builder()
                .withName("nonExistingItem").build();

        when(itemDao.find(controller.getName())).thenThrow(ItemNotFoundException.class);

        // WHEN - // THEN
        assertThrows(ItemNotFoundException.class, () ->
                        deleteItemService.handleRequest(controller, null),
                ("Unable to find this item. It may not exist."));
    }
}
