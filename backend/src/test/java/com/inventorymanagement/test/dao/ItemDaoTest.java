package com.inventorymanagement.test.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.inventorymanagement.configuration.awsglobalsecondaryindex.AwsGsiItem;
import com.inventorymanagement.dao.*;
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
import static org.mockito.Mockito.when;

public class ItemDaoTest {
    private final Item ramen = TestHelper.ramenNoodle();
    private final Item lettuce = TestHelper.lettuce();
    private final Item electronic = TestHelper.computer();
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
        when(dynamoDBMapper.load(Item.class, ramen.getName())).thenReturn(ramen);

        // WHEN
        Item result = itemDao.find(ramen.getName());

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
    void findByCategory_returnsListOfCategory() {
        List<Item> existingItem = new ArrayList<>();
        existingItem.add(electronic);

        when(paginatedQueryList.size()).thenReturn(existingItem.size());
        when(paginatedQueryList.isEmpty()).thenReturn(false);
        when(paginatedQueryList.iterator()).thenReturn(existingItem.iterator());
        when(paginatedQueryList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findByCategory(electronic.getCategory())).thenReturn(paginatedQueryList);

        // WHEN
        PaginatedQueryList<Item> result = itemDao.findAll();

        // THEN
        assertEquals(paginatedQueryList, result);
    }

    @Test
    void findByByAvailability_returnsListOfAvailable() {
        List<Item> existingItem = new ArrayList<>();
        existingItem.add(lettuce);
        existingItem.add(electronic);

        when(paginatedQueryList.size()).thenReturn(existingItem.size());
        when(paginatedQueryList.isEmpty()).thenReturn(false);
        when(paginatedQueryList.iterator()).thenReturn(existingItem.iterator());
        when(paginatedQueryList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findByCategory(electronic.getCategory())).thenReturn(paginatedQueryList);

        // WHEN
        PaginatedQueryList<Item> result = itemDao.findAll();

        // THEN
        assertEquals(paginatedQueryList, result);
    }

    @Test
    void findAll_returnAllItemsInTable() {
        // GIVEN
        List<Item> existingItem = new ArrayList<>();
        existingItem.add(ramen);
        existingItem.add(lettuce);

        when(paginatedQueryList.size()).thenReturn(existingItem.size());
        when(paginatedQueryList.isEmpty()).thenReturn(false);
        when(paginatedQueryList.iterator()).thenReturn(existingItem.iterator());
        when(paginatedQueryList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findAll()).thenReturn(paginatedQueryList);

        // WHEN
        PaginatedQueryList<Item> result = itemDao.findAll();

        // THEN
        assertEquals(paginatedQueryList, result);
    }
}