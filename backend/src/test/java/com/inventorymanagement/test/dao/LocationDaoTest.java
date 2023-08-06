package com.inventorymanagement.test.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.inventorymanagement.table.Location;
import com.inventorymanagement.configuration.awsglobalsecondaryindex.AwsGsiLocation;
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

// TODO: Review for unnecessary or missing test
public class LocationDaoTest {
    private final Location DF1 = TestHelper.locationDF1();
    private final Location E4 = TestHelper.locationE4();
    private final Location R2 = TestHelper.locationR2();
    @InjectMocks
    private LocationDao locationDao;
    @Mock
    private DynamoDBMapper dynamoDBMapper;
    @Mock
    private AwsGsiLocation awsGsiLocation;
    @Mock
    private PaginatedQueryList<Location> paginatedQueryList;

    @BeforeEach
    // FIXME: Check what is that openMock "auto-closable" thing is.
    //  All other test class with @Mock has it.
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

        when(paginatedQueryList.iterator()).thenReturn(existingItem.iterator());
        when(paginatedQueryList.size()).thenReturn(existingItem.size());
        when(paginatedQueryList.isEmpty()).thenReturn(false);
        when(paginatedQueryList.stream()).thenReturn(existingItem.stream());

        when(locationDao.findAll()).thenReturn(paginatedQueryList);

        // WHEN
        PaginatedQueryList<Location> result = locationDao.findAll();

        // THEN
        assertNotNull(result);
    }
}
