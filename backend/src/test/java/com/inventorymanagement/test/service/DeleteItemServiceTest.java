package com.inventorymanagement.test.service;

import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.ItemDao;
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
                .withName(ramen.getItemName()).build();

        when(itemDao.find(controller.getName())).thenReturn(ramen);

        // WHEN
        ItemResult result = deleteItemService.handleRequest(controller, null);

        // THEN
        assertEquals(ramen.getItemName(), result.getItem().getName());
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
                ("Unable to find this item to delete. It may not exist."));
    }
}
