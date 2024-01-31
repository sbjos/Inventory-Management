package com.inventorymanagement.test.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.inventorymanagement.table.Location;
import com.inventorymanagement.dao.LocationDao;
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

public class LocationDaoTest {
    private final Location DF1 = TestHelper.locationDF1();
    private final Location E4 = TestHelper.locationE4();
    private final Location R2 = TestHelper.locationR2();
    @InjectMocks
    private LocationDao locationDao;
    @Mock
    private DynamoDBMapper dynamoDBMapper;
    @Mock
    private PaginatedScanList<Location> paginatedScanList;

    @BeforeEach
    public void Setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void find_ReturnsItem() {
        // GIVEN
        when(dynamoDBMapper.load(Location.class, DF1.getLocationName())).thenReturn(DF1);

        // WHEN
        Location result = locationDao.find(DF1.getLocationName());

        // THEN
        assertEquals(DF1, result);
    }

    @Test
    void findAll_ReturnsItem() {
        // GIVEN
        List<Location> existingItem = new ArrayList<>();
        existingItem.add(DF1);
        existingItem.add(R2);
        existingItem.add(E4);

        when(paginatedScanList.iterator()).thenReturn(existingItem.iterator());
        when(paginatedScanList.size()).thenReturn(existingItem.size());
        when(paginatedScanList.isEmpty()).thenReturn(false);
        when(paginatedScanList.stream()).thenReturn(existingItem.stream());

        when(locationDao.findAll()).thenReturn(paginatedScanList);

        // WHEN
        PaginatedScanList<Location> result = locationDao.findAll();

        // THEN
        assertNotNull(result);
    }
}
