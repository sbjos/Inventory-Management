package com.inventorymanagement.test.service.item;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.exception.InvalidAttributeException;
import com.inventorymanagement.exception.ItemAlreadyExistException;
import com.inventorymanagement.dao.CategoryDao;
import com.inventorymanagement.dao.ItemDao;
import com.inventorymanagement.result.ItemResult;
import com.inventorymanagement.service.item.CreateItemService;
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
        item.setItemCategory(food.getCategoryName());
        item.setAvailability("Available");
        item.setItemQuantity(availQuantity);
        item.setItemLocation(location);
    }

    @Test
    void handleRequest_withValidName_returnResultAndAssignsItemId() {
        // GIVEN
        List<Item> existingItem = new ArrayList<>();
        existingItem.add(ramen);
        existingItem.add(lettuce);

        controller = Controller.builder()
                .withName(validName)
                .withCategory(food.getCategoryName())
                .withQuantity(availQuantity)
                .withLocation(location)
                .build();

        when(paginatedQueryList.size()).thenReturn(existingItem.size());
        when(paginatedQueryList.isEmpty()).thenReturn(false);
        when(paginatedQueryList.iterator()).thenReturn(existingItem.iterator());
        when(paginatedQueryList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findById(ramen.getItemId())).thenReturn(paginatedQueryList);
        when(categoryDao.find(food.getCategoryName())).thenReturn(food);

        // WHEN
        ItemResult result = createItemService.handleRequest(controller, null);

        // THEN
        assertEquals(item.getItemName(), result.getItem().getItemName());
        assertEquals(item.getItemCategory(), result.getItem().getItemCategory());
        assertEquals(item.getAvailability(), result.getItem().getAvailability());
        assertEquals(item.getItemQuantity(), result.getItem().getItemQuantity());
        assertEquals(item.getItemLocation(), result.getItem().getItemLocation());
        assertNotNull(result.getItem().getItemId());
    }

    @Test
    void handleRequest_withEmptyName_returnInvalidAttributeException() {
        // GIVEN
        String empty = " ";
        controller = Controller.builder()
                .withName(empty)
                .withCategory(food.getCategoryName())
                .withQuantity(availQuantity)
                .withLocation(location)
                .build();

        // WHEN - // THEN
        assertThrows(InvalidAttributeException.class, () ->
                        createItemService.handleRequest(controller, null),
                "Please enter a valid item name.");
    }

    @Test
    void handleRequest_withInvalidName_changesFirstLetterToUpperCase() {
        // GIVEN
        String invalidName = "ramen Noodle";
        controller = Controller.builder()
                .withName(invalidName)
                .withCategory(food.getCategoryName())
                .withQuantity(availQuantity)
                .withLocation(location)
                .build();

        // WHEN
        ItemResult result = createItemService.handleRequest(controller, null);

        // THEN
        assertEquals(ramen.getItemName(), result.getItem().getItemName());
    }

    @Test
    void handleRequest_withQuantityLessThanOne_setsAvailabilityToUnavailable() {
        // GIVEN
        List<Item> existingItem = new ArrayList<>();
        existingItem.add(ramen);
        existingItem.add(lettuce);

        controller = Controller.builder()
                .withName(validName)
                .withCategory(food.getCategoryName())
                .withQuantity(0)
                .withLocation(location)
                .build();

        when(paginatedQueryList.size()).thenReturn(existingItem.size());
        when(paginatedQueryList.isEmpty()).thenReturn(false);
        when(paginatedQueryList.iterator()).thenReturn(existingItem.iterator());
        when(paginatedQueryList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findById(any())).thenReturn(paginatedQueryList);
        when(categoryDao.find(food.getCategoryName())).thenReturn(food);

        // WHEN
        ItemResult result = createItemService.handleRequest(controller, null);

        // THEN
        assertEquals("Unavailable", result.getItem().getAvailability());
    }

    @Test
    void handleRequest_withExistingItemName_throwsItemAlreadyExistException() {
        // GIVEN
        List<Item> existingItem = new ArrayList<>();
        existingItem.add(ramen);
        existingItem.add(lettuce);

        controller = Controller.builder()
                .withName(ramen.getItemName())
                .withCategory(food.getCategoryName())
                .withQuantity(availQuantity)
                .withLocation(location)
                .build();

        when(itemDao.find(ramen.getItemName())).thenReturn(ramen);

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
                .withCategory(food.getCategoryName())
                .withQuantity(availQuantity)
                .withLocation(location)
                .build();

        when(paginatedQueryList.size()).thenReturn(existingItem.size());
        when(paginatedQueryList.isEmpty()).thenReturn(false);
        when(paginatedQueryList.iterator()).thenReturn(existingItem.iterator());
        when(paginatedQueryList.stream()).thenReturn(existingItem.stream());

        when(categoryDao.find(food.getCategoryName())).thenReturn(food);
        when(itemDao.findById(ramen.getItemId())).thenReturn(paginatedQueryList);

        // WHEN
        ItemResult result = createItemService.handleRequest(controller, null);

        // THEN
        assertNotEquals(ramen.getItemId(), result.getItem().getItemId());
        assertNotEquals(lettuce.getItemId(), result.getItem().getItemId());
    }
}
