package com.inventorymanagement.test.service.item;

import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.ItemDao;
import com.inventorymanagement.exception.InvalidAttributeException;
import com.inventorymanagement.result.ItemResult;
import com.inventorymanagement.service.item.DeleteItemService;
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
    private final String nonExistent = "nonExistent";
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

        when(itemDao.find(ramen.getItemName())).thenReturn(ramen);

        // WHEN
        ItemResult result = deleteItemService.handleRequest(controller, null);

        // THEN
        assertEquals(ramen.getItemName(), result.getItem().getItemName());
    }

    @Test
    void handleRequest_lowerCaseItemName_changesFirstLetterToUpperCase() {
        // GIVEN
        controller = Controller
                .builder()
                .withName("Ramen noodle").build();

        when(itemDao.find(ramen.getItemName())).thenReturn(ramen);

        // WHEN
        ItemResult result = deleteItemService.handleRequest(controller, null);

        // THEN
        assertEquals(ramen.getItemName(), result.getItem().getItemName());
    }

    @Test
    void handleRequest_withEmptyName_returnInvalidAttributeException() {
        // GIVEN
        controller = Controller.builder()
                .withName(" ").build();

        // WHEN - // THEN
        assertThrows(InvalidAttributeException.class, () ->
                        deleteItemService.handleRequest(controller, null),
                "Please enter a valid item name.");
    }
}
