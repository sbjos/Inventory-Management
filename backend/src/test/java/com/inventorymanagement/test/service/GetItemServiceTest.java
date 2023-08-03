package com.inventorymanagement.test.service;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.exception.ItemNotFoundException;
import com.inventorymanagement.dao.ItemDao;
import com.inventorymanagement.result.ItemResult;
import com.inventorymanagement.service.GetItemService;
import com.inventorymanagement.table.Item;
import com.inventorymanagement.test.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class GetItemServiceTest {
    private final Item ramen = TestHelper.ramenNoodle();
    private final Item lettuce = TestHelper.lettuce();

    @InjectMocks
    GetItemService getItemService;
    @Mock
    private ItemDao itemDao;

    @Mock
    private PaginatedQueryList paginatedQueryList;
    private Controller controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleRequest_withValidName_returnResult() {
        // GIVEN
        controller = Controller.builder()
                .withName(ramen.getName()).build();

        when(itemDao.find(controller.getName())).thenReturn(ramen);

        // WHEN
        ItemResult result = getItemService.handleRequest(controller, null);

        // THEN
        assertEquals(ramen.getName(), result.getItem().getName());
        assertEquals(ramen.getId(), result.getItem().getId());
        assertEquals(ramen.getCategory(), result.getItem().getCategory());
        assertEquals(ramen.isAvailable(), result.getItem().isAvailable());
        assertEquals(ramen.getQuantity(), result.getItem().getQuantity());
        assertEquals(ramen.getLocation(), result.getItem().getLocation());
    }

    @Test
    void handleRequest_withValidId_returnResult() {
        // GIVEN
        List<Item> existingItem = new ArrayList<>();
        existingItem.add(ramen);
        existingItem.add(lettuce);

        controller = Controller.builder()
                .withId(ramen.getId()).build();

        when(itemDao.find(controller.getId())).thenReturn(ramen);

        when(paginatedQueryList.size()).thenReturn(existingItem.size());
        when(paginatedQueryList.isEmpty()).thenReturn(false);
        when(paginatedQueryList.iterator()).thenReturn(existingItem.iterator());
        when(paginatedQueryList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findById(controller.getId())).thenReturn(paginatedQueryList);

        // WHEN
        ItemResult result = getItemService.handleRequest(controller, null);

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
        String nonExistingItem = "nonExistingItem";

        controller = Controller.builder()
                .withName(nonExistingItem).build();

        when(itemDao.find(controller.getName())).thenThrow(ItemNotFoundException.class);

        // WHEN - // THEN
        assertThrows(ItemNotFoundException.class, () ->
                getItemService.handleRequest(controller, null),
                ("Unable to find this item. It may not exist."));
    }
}
