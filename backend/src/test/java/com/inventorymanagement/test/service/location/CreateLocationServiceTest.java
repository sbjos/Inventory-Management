package com.inventorymanagement.test.service.location;

import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.LocationDao;
import com.inventorymanagement.exception.LocationAlreadyExistException;
import com.inventorymanagement.exception.InvalidAttributeException;
import com.inventorymanagement.result.LocationResult;
import com.inventorymanagement.service.location.CreateLocationService;
import com.inventorymanagement.table.Location;
import com.inventorymanagement.test.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class CreateLocationServiceTest {
    private final Location locationDF1 = TestHelper.locationDF1();
    @InjectMocks
    CreateLocationService createLocationService;
    @Mock
    private LocationDao locationDao;
    private Controller controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleRequest_withExistingLocation_throwsLocationAlreadyExistException() {
        // GIVEN
        controller = Controller.builder()
                .withLocation(locationDF1.getLocationName()).build();

        when(locationDao.find(controller.getLocation())).thenThrow(LocationAlreadyExistException.class);

        // WHEN - // THEN
        assertThrows(LocationAlreadyExistException.class, () ->
                createLocationService.handleRequest(controller, null),
                (String.format("%s already exist. Please choose a different location.", controller.getLocation())));
    }

    @Test
    void handleRequest_withValidLocationName_returnsLocationName() {
        // GIVEN
        String newLocation = "R20";

        controller = Controller
                .builder()
                .withLocation(newLocation)
                .build();

        // WHEN
        LocationResult result = createLocationService.handleRequest(controller, null);

        // THEN
        assertEquals(newLocation, result.getLocation().getLocation());
    }

    @Test
    void handleRequest_withInvalidLocationName_throwsInvalidAttributeException() {
        // GIVEN
        controller = Controller
                .builder()
                .withLocation(" ")
                .build();

        // WHEN - THEN
        assertThrows(InvalidAttributeException.class, () ->
                createLocationService.handleRequest(controller, null),
                ("Please enter a valid location name."));
    }
}
