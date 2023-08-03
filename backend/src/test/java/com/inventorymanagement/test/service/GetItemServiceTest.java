package com.inventorymanagement.test.service;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.inventorymanagement.configuration.awsglobalsecondaryindex.AwsGsiItem;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.exception.CategoryNotFoundException;
import com.inventorymanagement.exception.ItemNotFoundException;
import com.inventorymanagement.dao.ItemDao;
import com.inventorymanagement.result.ItemResult;
import com.inventorymanagement.service.GetItemService;
import com.inventorymanagement.table.Category;
import com.inventorymanagement.table.Item;
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

public class GetItemServiceTest {
    private final Item ramen = TestHelper.ramenNoodle();
    private final Item lettuce = TestHelper.lettuce();
    private final Item computer = TestHelper.computer();
    private String nonExistingItem = "nonExistingItem";
    @InjectMocks
    GetItemService getItemService;
    @Mock
    private ItemDao itemDao;
    @Mock
    private Category category;
    @Mock
    private AwsGsiItem awsGsiItem;

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
    void handleRequest_findBydId_returnResult() {
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
    void handleRequest_findByCategory_returnResult() {
        // GIVEN
        List<Item> existingItem = new ArrayList<>();
        existingItem.add(ramen);
        existingItem.add(lettuce);

        controller = Controller.builder()
                .withCategory(ramen.getCategory()).build();

        when(paginatedQueryList.size()).thenReturn(existingItem.size());
        when(paginatedQueryList.isEmpty()).thenReturn(false);
        when(paginatedQueryList.iterator()).thenReturn(existingItem.iterator());
        when(paginatedQueryList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findByCategory(controller.getCategory())).thenReturn(paginatedQueryList);

        // WHEN
        ItemResult result = getItemService.handleRequest(controller, null);

        // THEN
        System.out.println(result.getItemList().getItemList());
        assertNotNull(result.getItemList());
    }

    @Test
    void handleRequest_findByAvailability_returnResult() {
        // GIVEN
        List<Item> existingItem = new ArrayList<>();
        existingItem.add(ramen);
        existingItem.add(lettuce);
        existingItem.add(computer);

        controller = Controller.builder()
                .withAvailable("True").build();

        when(paginatedQueryList.size()).thenReturn(existingItem.size());
        when(paginatedQueryList.isEmpty()).thenReturn(false);
        when(paginatedQueryList.iterator()).thenReturn(existingItem.iterator());
        when(paginatedQueryList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findByByAvailability(controller.isAvailable())).thenReturn(paginatedQueryList);

        // WHEN
        ItemResult result = getItemService.handleRequest(controller, null);

        // THEN
        System.out.println(result.getItemList());
        assertNotNull(result.getItemList());
    }

    @Test
    void handleRequest_findByName_itemDoesNotExist_returnListEmptyException() {
        // GIVEN
        controller = Controller.builder()
                .withName(nonExistingItem).build();

        when(itemDao.find(controller.getName())).thenThrow(ItemNotFoundException.class);

        // WHEN - // THEN
        assertThrows(ItemNotFoundException.class, () ->
                getItemService.handleRequest(controller, null),
                ("Unable to find this item. It may not exist."));
    }

    @Test
    void handleRequest_findById_itemDoesNotExist_returnItemNotFoundException() {
        // GIVEN
        controller = Controller.builder()
                .withId(nonExistingItem).build();

        when(itemDao.find(controller.getId())).thenThrow(ItemNotFoundException.class);

        // WHEN - // THEN
        assertThrows(ItemNotFoundException.class, () ->
                        getItemService.handleRequest(controller, null),
                ("Unable to find this item. It may not exist."));
    }

    @Test
    void handleRequest_findByCategory_itemDoesNotExist_returnCategoryNotFoundException() {
        // GIVEN
        controller = Controller.builder()
                .withCategory(nonExistingItem).build();

        when(itemDao.find(controller.getCategory())).thenThrow(CategoryNotFoundException.class);

        // WHEN - // THEN
        assertThrows(CategoryNotFoundException.class, () ->
                        getItemService.handleRequest(controller, null),
                ("Unable to find items in this category."));
    }
}
