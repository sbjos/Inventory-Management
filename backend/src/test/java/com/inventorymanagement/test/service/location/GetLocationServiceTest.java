package com.inventorymanagement.test.service.location;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.inventorymanagement.configuration.awsglobalsecondaryindex.AwsGsiItem;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.LocationDao;
import com.inventorymanagement.exception.LocationNotFoundException;
import com.inventorymanagement.result.LocationResult;
import com.inventorymanagement.service.location.GetLocationService;
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
    private final Location DF1 = TestHelper.locationDF1();
    private final Location R2 = TestHelper.locationR2();
    @InjectMocks
    GetLocationService getLocationService;
    @Mock
    private LocationDao locationDao;
    @Mock
    private AwsGsiItem awsGsiItem;
    @Mock
    private PaginatedQueryList<Location> locationPaginatedQueryList;
    private Controller controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleRequest_validLocationName_returnResult() {
        // GIVEN
        controller = Controller.builder()
                .withLocation(DF1.getLocationName()).build();

        when(locationDao.find(controller.getLocation())).thenReturn(DF1);

        // WHEN
        LocationResult result = getLocationService.handleRequest(controller, null);

        // THEN
        assertEquals(DF1.getLocationName(), result.getLocation().getLocation());
    }
    @Test
    void handleRequest_findAll_returnAllItems() {
        //GIVEN
        List<Location> existingItem = new ArrayList<>();
        existingItem.add(DF1);
        existingItem.add(R2);

        controller = Controller.builder()
                .withFindAll(true).build();

        when(locationPaginatedQueryList.size()).thenReturn(existingItem.size());
        when(locationPaginatedQueryList.isEmpty()).thenReturn(false);
        when(locationPaginatedQueryList.iterator()).thenReturn(existingItem.iterator());
        when(locationPaginatedQueryList.stream()).thenReturn(existingItem.stream());

        when(locationDao.findAll()).thenReturn(locationPaginatedQueryList);

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

        when(locationDao.find(controller.getLocation())).thenThrow(LocationNotFoundException.class);

        // WHEN - // THEN
        assertThrows(LocationNotFoundException.class, () ->
                        getLocationService.handleRequest(controller, null),
                (String.format("Unable to find %s location. It may not exist.", nonExisting)));
    }
}