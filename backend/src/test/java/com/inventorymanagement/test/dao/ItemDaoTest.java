package com.inventorymanagement.test.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
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

    @InjectMocks
    private ItemDao itemDao;
    @Mock
    private DynamoDBMapper dynamoDBMapper;
    @Mock
    private PaginatedQueryList<Item> paginatedQueryList;
    @Mock
    private PaginatedScanList<Item> paginatedScanList;

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
    void findAll_returnAllItemsInTable() {
        // GIVEN
        List<Item> existingItem = new ArrayList<>();
        existingItem.add(ramen);
        existingItem.add(lettuce);

        when(paginatedScanList.size()).thenReturn(existingItem.size());
        when(paginatedScanList.isEmpty()).thenReturn(false);
        when(paginatedScanList.iterator()).thenReturn(existingItem.iterator());
        when(paginatedScanList.stream()).thenReturn(existingItem.stream());

        when(itemDao.findAll()).thenReturn(paginatedScanList);

        // WHEN
        PaginatedScanList<Item> result = itemDao.findAll();

        // THEN
        System.out.println(result);
        assertEquals(paginatedQueryList, result);
    }
}
