package com.inventorymanagement.test.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.inventorymanagement.configuration.awsglobalsecondaryindex.AwsGsiItem;
import com.inventorymanagement.dao.*;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class ItemDaoTest {
    private final Item ramen = TestHelper.ramenNoodle();
    private final Item lettuce = TestHelper.lettuce();
    private final Item electronic = TestHelper.computer();
    private final Category food = TestHelper.food();
    private final Location R2 = TestHelper.locationR2();
    @InjectMocks
    private ItemDao itemDao;
    @Mock
    private DynamoDBMapper dynamoDBMapper;
    @Mock
    private AwsGsiItem awsGsiItem;
    @Mock
    private PaginatedQueryList<Item> paginatedQueryList;

    @BeforeEach
    // FIXME: Check what is that openMock "auto-closable" thing is.
    //  All other test class with @Mock has it.
    public void Setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void find_ReturnsItem() {
        // GIVEN
        when(dynamoDBMapper.load(Item.class, ramen.getItemName())).thenReturn(ramen);

        // WHEN
        Item result = itemDao.find(ramen.getItemName());

        // THEN
        assertEquals(ramen, result);
    }

    @Test
    void findById_returnsItem() {
        // GIVEN
        when(dynamoDBMapper.load(Item.class, ramen.getId())).thenReturn(ramen);

        // WHEN
        Item result = itemDao.find(ramen.getId());

        // THEN
        assertEquals(ramen, result);
    }

    @Test
    void findByByAvailability_returnsListOfAvailable() {
        List<Item> existingItem = new ArrayList<>();
        existingItem.add(electronic);

        when(paginatedQueryList.size()).thenReturn(existingItem.size());
        when(paginatedQueryList.isEmpty()).thenReturn(false);
        when(paginatedQueryList.iterator()).thenReturn(existingItem.iterator());
        when(paginatedQueryList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findByByAvailability(electronic.isAvailable())).thenReturn(paginatedQueryList);

        // WHEN
        PaginatedQueryList<Item> result = itemDao.findAll();

        // THEN
        assertNotNull(result);
    }

    @Test
    void findByCategory_returnsListOfCategory() {
        List<Item> existingItem = new ArrayList<>();
        existingItem.add(ramen);
        existingItem.add(lettuce);

        when(paginatedQueryList.size()).thenReturn(existingItem.size());
        when(paginatedQueryList.isEmpty()).thenReturn(false);
        when(paginatedQueryList.iterator()).thenReturn(existingItem.iterator());
        when(paginatedQueryList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findByCategory(food.getCategoryName())).thenReturn(paginatedQueryList);

        // WHEN
        PaginatedQueryList<Item> result = itemDao.findAll();

        // THEN
        assertNotNull(result);
    }

    @Test
    void findByCategoryAndAvailability_returnsListOfCategory() {
        List<Item> existingItem = new ArrayList<>();
        existingItem.add(lettuce);

        when(paginatedQueryList.size()).thenReturn(existingItem.size());
        when(paginatedQueryList.isEmpty()).thenReturn(false);
        when(paginatedQueryList.iterator()).thenReturn(existingItem.iterator());
        when(paginatedQueryList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findByCategoryAndAvailability(food.getCategoryName(), lettuce.isAvailable())).thenReturn(paginatedQueryList);

        // WHEN
        PaginatedQueryList<Item> result = itemDao.findAll();

        // THEN
        assertNotNull(result);
    }

    @Test
    void findByByLocation_returnsListOfLocation() {
        List<Item> existingItem = new ArrayList<>();
        existingItem.add(lettuce);

        when(paginatedQueryList.size()).thenReturn(existingItem.size());
        when(paginatedQueryList.isEmpty()).thenReturn(false);
        when(paginatedQueryList.iterator()).thenReturn(existingItem.iterator());
        when(paginatedQueryList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findByByLocation(R2.getLocationName())).thenReturn(paginatedQueryList);

        // WHEN
        PaginatedQueryList<Item> result = itemDao.findAll();

        // THEN
        assertNotNull(result);
    }

    @Test
    void findByByLocationAndCategory_returnsListOfLocation() {
        List<Item> existingItem = new ArrayList<>();
        existingItem.add(lettuce);

        when(paginatedQueryList.size()).thenReturn(existingItem.size());
        when(paginatedQueryList.isEmpty()).thenReturn(false);
        when(paginatedQueryList.iterator()).thenReturn(existingItem.iterator());
        when(paginatedQueryList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findByByLocationAndCategory(R2.getLocationName(), food.getCategoryName())).thenReturn(paginatedQueryList);

        // WHEN
        PaginatedQueryList<Item> result = itemDao.findAll();

        // THEN
        assertNotNull(result);
    }

    @Test
    void findAll_returnAllItemsInTable() {
        // GIVEN
        List<Item> existingItem = new ArrayList<>();
        existingItem.add(ramen);
        existingItem.add(lettuce);
        existingItem.add(electronic);

        when(paginatedQueryList.size()).thenReturn(existingItem.size());
        when(paginatedQueryList.isEmpty()).thenReturn(false);
        when(paginatedQueryList.iterator()).thenReturn(existingItem.iterator());
        when(paginatedQueryList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findAll()).thenReturn(paginatedQueryList);

        // WHEN
        PaginatedQueryList<Item> result = itemDao.findAll();

        // THEN
        assertNotNull(result);
    }
}
