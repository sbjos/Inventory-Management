package com.inventorymanagement.test.service;

import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.LocationDao;
import com.inventorymanagement.exception.LocationNotFoundException;
import com.inventorymanagement.result.LocationResult;
import com.inventorymanagement.service.DeleteLocationService;
import com.inventorymanagement.table.Location;
import com.inventorymanagement.test.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class DeleteLocationServiceTest {
    private final Location locationDF1 = TestHelper.locationDF1();
    @InjectMocks
    DeleteLocationService deleteLocationService;
    @Mock
    private LocationDao locationDao
            ;
    private Controller controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleRequest_withValidLocation_deleteResult() {
        // GIVEN
        controller = Controller.builder()
                .withLocation(locationDF1.getLocationName()).build();

        when(locationDao.find(controller.getLocation())).thenReturn(locationDF1);

        // WHEN
        LocationResult result = deleteLocationService.handleRequest(controller, null);

        // THEN
        assertEquals(locationDF1.getLocationName(), result.getLocation().getLocation());
    }

    @Test
    void handleRequest_locationDoesNotExist_returnLocationNotFoundException() {
        // GIVEN
        controller = Controller.builder()
                .withCategory("nonExisting").build();

        when(locationDao.find(controller.getName())).thenThrow(LocationNotFoundException.class);

        // WHEN - // THEN
        assertThrows(LocationNotFoundException.class, () ->
                        deleteLocationService.handleRequest(controller, null),
                ("Unable to find this location. It may not exist."));
    }
}
