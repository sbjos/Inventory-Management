package com.inventorymanagement.test.service;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.exception.InvalidAttributeException;
import com.inventorymanagement.exception.ItemAlreadyExistException;
import com.inventorymanagement.dao.CategoryDao;
import com.inventorymanagement.dao.ItemDao;
import com.inventorymanagement.result.ItemResult;
import com.inventorymanagement.service.CreateItemService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CreateItemServiceTest {
    private final Item ramen = TestHelper.ramenNoodle();
    private final Item lettuce = TestHelper.lettuce();
    private final Category food = TestHelper.food();
    private final String validName = "Salmon";
    private final int availQuantity = 10;
    private final String location = "R12";
    @InjectMocks
    CreateItemService createItemService;
    @Mock
    private ItemDao itemDao;
    @Mock
    private CategoryDao categoryDao;
    @Mock
    private PaginatedQueryList<Item> paginatedQueryList;
    private Item item;
    private Controller controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        item = new Item();
        item.setItemName(validName);
        item.setCategory(food.getCategory());
        item.setAvailable("Available");
        item.setQuantity(availQuantity);
        item.setLocation(location);
    }

    @Test
    void handleRequest_withValidName_returnResultAndAssignsItemId() {
        // GIVEN
        List<Item> existingItem = new ArrayList<>();
        existingItem.add(ramen);
        existingItem.add(lettuce);

        controller = Controller.builder()
                .withName(validName)
                .withCategory(food.getCategory())
                .withQuantity(availQuantity)
                .withLocation(location)
                .build();

        when(paginatedQueryList.size()).thenReturn(existingItem.size());
        when(paginatedQueryList.isEmpty()).thenReturn(false);
        when(paginatedQueryList.iterator()).thenReturn(existingItem.iterator());
        when(paginatedQueryList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findById(any())).thenReturn(paginatedQueryList);
        when(categoryDao.find(food.getCategory())).thenReturn(food);

        // WHEN
        ItemResult result = createItemService.handleRequest(controller, null);

        // THEN
        System.out.println(result.getItem());
        System.out.println(result.getItem());
        assertEquals(item.getItemName(), result.getItem().getName());
        assertEquals(item.getCategory(), result.getItem().getCategory());
        assertEquals(item.isAvailable(), result.getItem().isAvailable());
        assertEquals(item.getQuantity(), result.getItem().getQuantity());
        assertEquals(item.getLocation(), result.getItem().getLocation());
        assertNotNull(result.getItem().getId());
    }

    @Test
    void handleRequest_withInvalidName_returnInvalidAttributeException() {
        // GIVEN
        String invalidName = " ";
        controller = Controller.builder()
                .withName(invalidName)
                .withCategory(food.getCategory())
                .withQuantity(availQuantity)
                .withLocation(location)
                .build();

        // WHEN - // THEN
        assertThrows(InvalidAttributeException.class, () ->
                        createItemService.handleRequest(controller, null),
                "Please enter a valid item name.");
    }

    @Test
    void handleRequest_withQuantityLessThanOne_setsAvailabilityToFalse() {
        // GIVEN
        List<Item> existingItem = new ArrayList<>();
        existingItem.add(ramen);
        existingItem.add(lettuce);

        controller = Controller.builder()
                .withName(validName)
                .withCategory(food.getCategory())
                .withQuantity(0)
                .withLocation(location)
                .build();

        when(paginatedQueryList.size()).thenReturn(existingItem.size());
        when(paginatedQueryList.isEmpty()).thenReturn(false);
        when(paginatedQueryList.iterator()).thenReturn(existingItem.iterator());
        when(paginatedQueryList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findById(any())).thenReturn(paginatedQueryList);
        when(categoryDao.find(food.getCategory())).thenReturn(food);

        // WHEN
        ItemResult result = createItemService.handleRequest(controller, null);

        // THEN
        assertEquals("Unavailable", result.getItem().isAvailable());
    }

    @Test
    void handleRequest_withExistingItemName_throwsItemAlreadyExistException() {
        // GIVEN
        List<Item> existingItem = new ArrayList<>();
        existingItem.add(ramen);
        existingItem.add(lettuce);

        controller = Controller.builder()
                .withName(ramen.getItemName())
                .withCategory(food.getCategory())
                .withQuantity(availQuantity)
                .withLocation(location)
                .build();

        when(itemDao.find(controller.getName())).thenReturn(ramen);

        // WHEN - THEN
        assertThrows(ItemAlreadyExistException.class, () ->
                createItemService.handleRequest(controller, null),
                (String.format("%s already exist. Please choose a different name.", controller.getName())));
    }

    @Test
    void handleRequest_looksForExistingItemId_ifMatchIsFound_assignsNewItemId() {
        // GIVEN
        List<Item> existingItem = new ArrayList<>();
        existingItem.add(ramen);
        existingItem.add(lettuce);

        controller = Controller
                .builder()
                .withName(validName)
                .withCategory(food.getCategory())
                .withQuantity(availQuantity)
                .withLocation(location)
                .build();

        when(paginatedQueryList.size()).thenReturn(existingItem.size());
        when(paginatedQueryList.isEmpty()).thenReturn(false);
        when(paginatedQueryList.iterator()).thenReturn(existingItem.iterator());
        when(paginatedQueryList.stream()).thenReturn(existingItem.stream());

        when(categoryDao.find(controller.getCategory())).thenReturn(food);
        when(itemDao.findById(any())).thenReturn(paginatedQueryList);

        // WHEN
        ItemResult result = createItemService.handleRequest(controller, null);

        // THEN
        assertNotEquals(ramen.getId(), result.getItem().getId());
        assertNotEquals(lettuce.getId(), result.getItem().getId());
    }
}
