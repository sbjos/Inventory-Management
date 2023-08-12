package com.inventorymanagement.test.service.item;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.inventorymanagement.configuration.awsglobalsecondaryindex.AwsGsiItem;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.exception.CategoryNotFoundException;
import com.inventorymanagement.exception.InvalidAttributeException;
import com.inventorymanagement.exception.ItemNotFoundException;
import com.inventorymanagement.dao.ItemDao;
import com.inventorymanagement.exception.LocationNotFoundException;
import com.inventorymanagement.result.ItemResult;
import com.inventorymanagement.service.item.GetItemService;
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
    private final Category food = TestHelper.food();
    private final Location locationDF1 = TestHelper.locationDF1();
    private final String nonExisting = "nonExisting";
    @InjectMocks
    GetItemService getItemService;
    @Mock
    private ItemDao itemDao;
    @Mock
    private Category category;
    @Mock
    private AwsGsiItem awsGsiItem;
    @Mock
    private PaginatedQueryList<Item> itemPaginatedQueryList;
    @Mock
    private PaginatedScanList<Item> itemPaginatedScanList;
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

        when(itemDao.find(ramen.getItemName())).thenReturn(ramen);

        // WHEN
        ItemResult result = getItemService.handleRequest(controller, null);

        // THEN
        assertEquals(ramen.getItemName(), result.getItem().getItemName());
        assertEquals(ramen.getItemId(), result.getItem().getItemId());
        assertEquals(ramen.getItemCategory(), result.getItem().getItemCategory());
        assertEquals(ramen.getAvailability(), result.getItem().getAvailability());
        assertEquals(ramen.getItemQuantity(), result.getItem().getItemQuantity());
        assertEquals(ramen.getItemLocation(), result.getItem().getItemLocation());
    }

    @Test
    void handleRequest_findByLowerCaseName_changesFirstLetterToUpperCase() {
        // GIVEN
        controller = Controller.builder()
                .withName("ramen Noodle").build();

        when(itemDao.find(ramen.getItemName())).thenReturn(ramen);

        // WHEN
        ItemResult result = getItemService.handleRequest(controller, null);

        // THEN
        assertEquals(ramen.getItemName(), result.getItem().getItemName());
    }

    @Test
    void handleRequest_findBydId_returnResult() {
        // GIVEN
        List<Item> existingItem = new ArrayList<>();
        existingItem.add(ramen);

        controller = Controller.builder()
                .withId(ramen.getItemId()).build();

        when(itemDao.find(ramen.getItemId())).thenReturn(ramen);

        when(itemPaginatedQueryList.size()).thenReturn(existingItem.size());
        when(itemPaginatedQueryList.isEmpty()).thenReturn(false);
        when(itemPaginatedQueryList.iterator()).thenReturn(existingItem.iterator());
        when(itemPaginatedQueryList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findById(ramen.getItemId())).thenReturn(itemPaginatedQueryList);

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
                .withAvailability("Available").build();

        when(itemPaginatedQueryList.size()).thenReturn(existingItem.size());
        when(itemPaginatedQueryList.isEmpty()).thenReturn(false);
        when(itemPaginatedQueryList.iterator()).thenReturn(existingItem.iterator());
        when(itemPaginatedQueryList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findByByAvailability("Available")).thenReturn(itemPaginatedQueryList);

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
                .withCategory(food.getCategoryName()).build();

        when(itemPaginatedQueryList.size()).thenReturn(existingItem.size());
        when(itemPaginatedQueryList.isEmpty()).thenReturn(false);
        when(itemPaginatedQueryList.iterator()).thenReturn(existingItem.iterator());
        when(itemPaginatedQueryList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findByCategory(food.getCategoryName())).thenReturn(itemPaginatedQueryList);

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
                .withAvailability("Available").build();

        when(itemPaginatedQueryList.size()).thenReturn(existingItem.size());
        when(itemPaginatedQueryList.isEmpty()).thenReturn(false);
        when(itemPaginatedQueryList.iterator()).thenReturn(existingItem.iterator());
        when(itemPaginatedQueryList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findByCategoryAndAvailability(food.getCategoryName(), "Available")).thenReturn(itemPaginatedQueryList);

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
                .withLocation(ramen.getItemLocation()).build();

        when(itemPaginatedQueryList.size()).thenReturn(existingItem.size());
        when(itemPaginatedQueryList.isEmpty()).thenReturn(false);
        when(itemPaginatedQueryList.iterator()).thenReturn(existingItem.iterator());
        when(itemPaginatedQueryList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findByByLocation(ramen.getItemLocation())).thenReturn(itemPaginatedQueryList);

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
                .withLocation(ramen.getItemLocation())
                .withCategory(ramen.getItemCategory()).build();

        when(itemPaginatedQueryList.size()).thenReturn(existingItem.size());
        when(itemPaginatedQueryList.isEmpty()).thenReturn(false);
        when(itemPaginatedQueryList.iterator()).thenReturn(existingItem.iterator());
        when(itemPaginatedQueryList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findByByLocationAndCategory(ramen.getItemLocation(), ramen.getItemCategory())).thenReturn(itemPaginatedQueryList);

        // WHEN
        ItemResult result = getItemService.handleRequest(controller, null);

        // THEN
        assertNotNull(result);
    }

    @Test
    void handleRequest_findAll_returnAllItems() {
        //GIVEN
        List<Item> existingItem = new ArrayList<>();
        existingItem.add(ramen);
        existingItem.add(lettuce);
        existingItem.add(computer);

        controller = Controller.builder()
                .withAll(true).build();

        when(itemPaginatedScanList.size()).thenReturn(existingItem.size());
        when(itemPaginatedScanList.isEmpty()).thenReturn(false);
        when(itemPaginatedScanList.iterator()).thenReturn(existingItem.iterator());
        when(itemPaginatedScanList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findAll()).thenReturn(itemPaginatedScanList);

        // WHEN
        ItemResult result = getItemService.handleRequest(controller, null);

        // THEN
        assertNotNull(result);
    }

    @Test
    void handleRequest_findByName_itemDoesNotExist_returnListEmptyException() {
        // GIVEN
        controller = Controller.builder()
                .withName(nonExisting).build();

        when(itemDao.find(ramen.getItemName())).thenReturn(ramen);

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

        when(itemDao.find(ramen.getItemId())).thenReturn(ramen);

        // WHEN - // THEN
        assertThrows(ItemNotFoundException.class, () ->
                        getItemService.handleRequest(controller, null),
                (String.format("Unable to find an item with item ID %s. It may not exist.", nonExisting)));
    }

    @Test
    void handleRequest_findByCategory_categoryDoesNotExit_returnCategoryNotFoundException() {
        // GIVEN
        List<Item> existingItem = new ArrayList<>();
        existingItem.add(ramen);

        controller = Controller.builder()
                .withCategory(nonExisting).build();

        when(itemPaginatedQueryList.size()).thenReturn(existingItem.size());
        when(itemPaginatedQueryList.isEmpty()).thenReturn(false);
        when(itemPaginatedQueryList.iterator()).thenReturn(existingItem.iterator());
        when(itemPaginatedQueryList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findByCategory(food.getCategoryName())).thenReturn(itemPaginatedQueryList);

        // WHEN - // THEN
        assertThrows(CategoryNotFoundException.class, () ->
                        getItemService.handleRequest(controller, null),
                (String.format("Unable to find the %s category.", category)));
    }

    @Test
    void handleRequest_findByCategoryAndAvailability_listIsEmpty_returnCategoryNotFoundException() {
        // GIVEN
        controller = Controller.builder()
                .withCategory(nonExisting)
                .withAvailability(nonExisting).build();

        when(itemDao.findByCategoryAndAvailability(food.getCategoryName(), "Available"))
                .thenReturn(itemPaginatedQueryList);

        // WHEN - // THEN
        assertThrows(CategoryNotFoundException.class, () ->
                        getItemService.handleRequest(controller, null),
                (String.format("Unable to find the list of items with the %s category.", category)));
    }

    @Test
    void handleRequest_findByLocation_locationDoesNotExist_throwsLocationNotFoundException() {
        // GIVEN
        controller = Controller.builder()
                .withLocation(nonExisting).build();

        when(itemDao.findByByLocation(locationDF1.getLocationName())).thenReturn(itemPaginatedQueryList);

        // WHEN - // THEN
        assertThrows(LocationNotFoundException.class, () ->
                        getItemService.handleRequest(controller, null),
                (String.format("Unable to find the %s. location", nonExisting)));
    }

    @Test
    void handleRequest_findByLocationAndCategory_listIsEmpty_returnLocationNotFoundException() {
        // GIVEN
        controller = Controller.builder()
                .withLocation(nonExisting)
                .withCategory(nonExisting).build();

        when(itemDao.findByByLocationAndCategory(locationDF1.getLocationName(), food.getCategoryName()))
                .thenReturn(itemPaginatedQueryList);

        // WHEN - // THEN
        assertThrows(LocationNotFoundException.class, () ->
                        getItemService.handleRequest(controller, null),
                (String.format("Unable to find the list of items in the %s location.", category)));
    }

    @Test
    void handleRequest_ifNameIsEmpty_throwsInvalidAttributeException() {
        // GIVEN
        controller = Controller.builder()
                .withName(" ").build();

        when(itemDao.find(ramen.getItemName())).thenReturn(ramen);

        // WHEN - THEN
        assertThrows(InvalidAttributeException.class, () ->
                getItemService.handleRequest(controller, null),
                ("Please enter a valid input"));

    }

    @Test
    void handleRequest_ifAvailabilityIsEmpty_throwsInvalidAttributeException() {
        // GIVEN
        controller = Controller.builder()
                .withAvailability(" ").build();

        when(itemDao.find(ramen.getAvailability())).thenReturn(ramen);

        // WHEN - THEN
        assertThrows(InvalidAttributeException.class, () ->
                        getItemService.handleRequest(controller, null),
                ("Please enter a valid input"));

    }

    @Test
    void handleRequest_ifLocationIsEmpty_throwsInvalidAttributeException() {
        // GIVEN
        controller = Controller.builder()
                .withLocation(" ").build();

        when(itemDao.find(ramen.getItemLocation())).thenReturn(ramen);

        // WHEN - THEN
        assertThrows(InvalidAttributeException.class, () ->
                        getItemService.handleRequest(controller, null),
                ("Please enter a valid input"));

    }
}
