package com.inventorymanagement.test.service.location;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.inventorymanagement.configuration.awsglobalsecondaryindex.AwsGsiItem;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.LocationDao;
import com.inventorymanagement.exception.LocationNotFoundException;
import com.inventorymanagement.result.LocationResult;
import com.inventorymanagement.service.location.GetLocationService;
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

public class GetLocationServiceTest {
    private final Location locationDF1 = TestHelper.locationDF1();
    private final Location locationR2 = TestHelper.locationR2();
    @InjectMocks
    GetLocationService getLocationService;
    @Mock
    private LocationDao locationDao;
    @Mock
    private AwsGsiItem awsGsiItem;
    @Mock
    private PaginatedQueryList<Location> locationPaginatedQueryList;
    @Mock
    private PaginatedScanList<Location> locationPaginatedScanList;
    private Controller controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleRequest_validLocationName_returnResult() {
        // GIVEN
        controller = Controller.builder()
                .withLocation(locationDF1.getLocationName()).build();

        when(locationDao.find(locationDF1.getLocationName())).thenReturn(locationDF1);

        // WHEN
        LocationResult result = getLocationService.handleRequest(controller, null);

        // THEN
        assertEquals(locationDF1.getLocationName(), result.getLocation().getLocationName());
    }
    @Test
    void handleRequest_findAll_returnAllItems() {
        //GIVEN
        List<Location> existingItem = new ArrayList<>();
        existingItem.add(locationDF1);
        existingItem.add(locationR2);

        controller = Controller.builder()
                .withAll(true).build();

        when(locationPaginatedScanList.size()).thenReturn(existingItem.size());
        when(locationPaginatedScanList.isEmpty()).thenReturn(false);
        when(locationPaginatedScanList.iterator()).thenReturn(existingItem.iterator());
        when(locationPaginatedScanList.stream()).thenReturn(existingItem.stream());

        when(locationDao.findAll()).thenReturn(locationPaginatedScanList);

        // WHEN
        LocationResult result = getLocationService.handleRequest(controller, null);

        // THEN
        assertNotNull(result);
    }

    @Test
    void handleRequest_locationDoesNotExist_returnLocationNotFoundException() {
        // GIVEN
        String nonExisting = "nonExisting";

        controller = Controller.builder()
                .withLocation(nonExisting).build();

        when(locationDao.find(locationDF1.getLocationName())).thenThrow(LocationNotFoundException.class);

        // WHEN - // THEN
        assertThrows(LocationNotFoundException.class, () ->
                        getLocationService.handleRequest(controller, null),
                (String.format("Unable to find %s location. It may not exist.", nonExisting)));
    }
}