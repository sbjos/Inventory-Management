package com.inventorymanagement.test.service;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.inventorymanagement.configuration.awsglobalsecondaryindex.AwsGsiItem;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.exception.CategoryNotFoundException;
import com.inventorymanagement.exception.ItemListNotFoundException;
import com.inventorymanagement.exception.ItemNotFoundException;
import com.inventorymanagement.dao.ItemDao;
import com.inventorymanagement.exception.LocationNotFoundException;
import com.inventorymanagement.result.ItemResult;
import com.inventorymanagement.service.GetItemService;
import com.inventorymanagement.table.Category;
import com.inventorymanagement.table.Item;
import com.inventorymanagement.table.Location;
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
    private final Category music = TestHelper.music();
    private final Location locationDF1 = TestHelper.locationDF1();
    private String nonExisting = "nonExisting";
    @InjectMocks
    GetItemService getItemService;
    @Mock
    private ItemDao itemDao;
    @Mock
    private Category category;
    @Mock
    private AwsGsiItem awsGsiItem;

    @Mock
    private PaginatedQueryList<Item> paginatedQueryItemList;
    @Mock
    private PaginatedQueryList<Category> paginatedQueryCategoryList;
    @Mock
    private PaginatedQueryList<Location> paginatedQueryLocationList;
    private Controller controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleRequest_findByName_returnResult() {
        // GIVEN
        controller = Controller.builder()
                .withName(ramen.getItemName()).build();

        when(itemDao.find(controller.getName())).thenReturn(ramen);

        // WHEN
        ItemResult result = getItemService.handleRequest(controller, null);

        // THEN
        assertEquals(ramen.getItemName(), result.getItem().getName());
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

        controller = Controller.builder()
                .withId(ramen.getId()).build();

        when(itemDao.find(controller.getId())).thenReturn(ramen);

        when(paginatedQueryItemList.size()).thenReturn(existingItem.size());
        when(paginatedQueryItemList.isEmpty()).thenReturn(false);
        when(paginatedQueryItemList.iterator()).thenReturn(existingItem.iterator());
        when(paginatedQueryItemList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findById(controller.getId())).thenReturn(paginatedQueryItemList);

        // WHEN
        ItemResult result = getItemService.handleRequest(controller, null);

        // THEN
        assertNotNull(result);
    }

    @Test
    void handleRequest_findByAvailability_returnResult() {
        // GIVEN
        List<Item> existingItem = new ArrayList<>();
        existingItem.add(ramen);
        existingItem.add(lettuce);
        existingItem.add(computer);

        controller = Controller.builder()
                .withAvailable("Available").build();

        when(paginatedQueryItemList.size()).thenReturn(existingItem.size());
        when(paginatedQueryItemList.isEmpty()).thenReturn(false);
        when(paginatedQueryItemList.iterator()).thenReturn(existingItem.iterator());
        when(paginatedQueryItemList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findByByAvailability(controller.isAvailable())).thenReturn(paginatedQueryItemList);

        // WHEN
        ItemResult result = getItemService.handleRequest(controller, null);

        // THEN
        assertNotNull(result);
    }

    @Test
    void handleRequest_findByCategory_returnResult() {
        // GIVEN
        List<Item> existingItem = new ArrayList<>();
        existingItem.add(ramen);
        existingItem.add(lettuce);

        controller = Controller.builder()
                .withCategory("Food").build();

        when(paginatedQueryItemList.size()).thenReturn(existingItem.size());
        when(paginatedQueryItemList.isEmpty()).thenReturn(false);
        when(paginatedQueryItemList.iterator()).thenReturn(existingItem.iterator());
        when(paginatedQueryItemList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findByCategory(controller.getCategory())).thenReturn(paginatedQueryItemList);

        // WHEN
        ItemResult result = getItemService.handleRequest(controller, null);

        // THEN
        assertNotNull(result);
    }

    @Test
    void handleRequest_findByCategoryAndAvailability_returnResult() {
        // GIVEN
        List<Item> existingItem = new ArrayList<>();
        existingItem.add(lettuce);

        controller = Controller.builder()
                .withCategory("Food")
                .withAvailable("True").build();

        when(paginatedQueryItemList.size()).thenReturn(existingItem.size());
        when(paginatedQueryItemList.isEmpty()).thenReturn(false);
        when(paginatedQueryItemList.iterator()).thenReturn(existingItem.iterator());
        when(paginatedQueryItemList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findByCategory(controller.getCategory(), controller.isAvailable())).thenReturn(paginatedQueryItemList);

        // WHEN
        ItemResult result = getItemService.handleRequest(controller, null);

        // THEN
        assertNotNull(result);
    }

    @Test
    void handleRequest_findByLocation_returnResult() {
        // GIVEN
        List<Item> existingItem = new ArrayList<>();
        existingItem.add(ramen);

        controller = Controller.builder()
                .withLocation(ramen.getLocation()).build();

        when(paginatedQueryItemList.size()).thenReturn(existingItem.size());
        when(paginatedQueryItemList.isEmpty()).thenReturn(false);
        when(paginatedQueryItemList.iterator()).thenReturn(existingItem.iterator());
        when(paginatedQueryItemList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findByByLocation(controller.getLocation())).thenReturn(paginatedQueryItemList);

        // WHEN
        ItemResult result = getItemService.handleRequest(controller, null);

        // THEN
        assertNotNull(result);
    }

    @Test
    void handleRequest_findByLocationAndCategory_returnResult() {
        // GIVEN
        List<Item> existingItem = new ArrayList<>();
        existingItem.add(ramen);

        controller = Controller.builder()
                .withLocation("A12")
                .withCategory("Food").build();

        when(paginatedQueryItemList.size()).thenReturn(existingItem.size());
        when(paginatedQueryItemList.isEmpty()).thenReturn(false);
        when(paginatedQueryItemList.iterator()).thenReturn(existingItem.iterator());
        when(paginatedQueryItemList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findByByLocation(controller.getLocation(), controller.getCategory())).thenReturn(paginatedQueryItemList);

        // WHEN
        ItemResult result = getItemService.handleRequest(controller, null);

        // THEN
        for (Item list: paginatedQueryItemList)
            System.out.println(list);
        assertNotNull(result);
    }

    @Test
    void handleRequest_findByName_itemDoesNotExist_returnListEmptyException() {
        // GIVEN
        controller = Controller.builder()
                .withName(nonExisting).build();

        when(itemDao.find(controller.getName())).thenThrow(ItemNotFoundException.class);

        // WHEN - // THEN
        assertThrows(ItemNotFoundException.class, () ->
                getItemService.handleRequest(controller, null),
                (String.format("Unable to find %s. It may not exist.", nonExisting)));
    }

    @Test
    void handleRequest_findById_itemDoesNotExist_returnItemNotFoundException() {
        // GIVEN
        controller = Controller.builder()
                .withId(nonExisting).build();

        when(itemDao.find(controller.getId())).thenThrow(ItemNotFoundException.class);

        // WHEN - // THEN
        assertThrows(ItemNotFoundException.class, () ->
                        getItemService.handleRequest(controller, null),
                (String.format("Unable to find item ID %s. It may not exist.", nonExisting)));
    }

    @Test
    void handleRequest_findByCategory_categoryDoesNotExit_returnCategoryNotFoundException() {
        // GIVEN
        List<Category> existingItem = new ArrayList<>();
        existingItem.add(music);

        controller = Controller.builder()
                .withCategory(music.getCategory()).build();

        when(paginatedQueryCategoryList.size()).thenReturn(existingItem.size());
        when(paginatedQueryCategoryList.isEmpty()).thenReturn(false);
        when(paginatedQueryCategoryList.iterator()).thenReturn(existingItem.iterator());
        when(paginatedQueryCategoryList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findByCategory(controller.getCategory())).thenThrow(CategoryNotFoundException.class);

        // WHEN - // THEN
        assertThrows(CategoryNotFoundException.class, () ->
                        getItemService.handleRequest(controller, null),
                (String.format("Unable to find the %s category.", category)));
    }

    @Test
    void handleRequest_findByLocation_locationDoesNotExist_throwsLocationNotFoundException() {
        // GIVEN
        List<Location> existingItem = new ArrayList<>();
        existingItem.add(locationDF1);

        controller = Controller.builder()
                .withLocation(nonExisting).build();

        when(paginatedQueryLocationList.size()).thenReturn(existingItem.size());
        when(paginatedQueryLocationList.isEmpty()).thenReturn(false);
        when(paginatedQueryLocationList.iterator()).thenReturn(existingItem.iterator());
        when(paginatedQueryLocationList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findByByLocation(controller.getLocation())).thenThrow(LocationNotFoundException.class);

        // WHEN - // THEN
        assertThrows(LocationNotFoundException.class, () ->
                        getItemService.handleRequest(controller, null),
                (String.format("Unable to find the %s. location", nonExisting)));
    }

    @Test
    void handleRequest_findByCategoryAndAvailability_itemListIsEmpty_returnItemListNotFoundException() {
        // GIVEN
        List<Item> existingItem = new ArrayList<>();

        controller = Controller.builder()
                .withCategory("Food")
                .withAvailable("Available").build();

        when(paginatedQueryItemList.size()).thenReturn(existingItem.size());
        when(paginatedQueryItemList.isEmpty()).thenReturn(false);
        when(paginatedQueryItemList.iterator()).thenReturn(existingItem.iterator());
        when(paginatedQueryItemList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findByCategory(controller.getCategory(), controller.isAvailable())).thenThrow(ItemListNotFoundException.class);

        // WHEN - // THEN
        assertThrows(ItemListNotFoundException.class, () ->
                        getItemService.handleRequest(controller, null),
                (String.format("Unable to find the list of items in the %s category.", category)));
    }

    @Test
    void handleRequest_findByLocationAndCategory_itemListIsEmpty_returnItemListNotFoundException() {
        // GIVEN
        controller = Controller.builder()
                .withCategory(nonExisting).build();

        when(itemDao.find(controller.getCategory())).thenThrow(CategoryNotFoundException.class);

        // WHEN - // THEN
        assertThrows(CategoryNotFoundException.class, () ->
                        getItemService.handleRequest(controller, null),
                (String.format("Unable to find the %s category.", category)));
    }
}
