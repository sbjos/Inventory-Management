package com.inventorymanagement.service.location;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.LocationDao;
import com.inventorymanagement.exception.InvalidAttributeException;
import com.inventorymanagement.exception.LocationAlreadyExistException;
import com.inventorymanagement.result.LocationResult;
import com.inventorymanagement.table.Location;
import com.inventorymanagement.utility.ModelConverter;

import javax.inject.Inject;

import static com.inventorymanagement.utility.ServiceUtility.*;

// TODO: Create test method
public class CreateLocationService implements RequestHandler<Controller, LocationResult> {
    private final LocationDao locationDao;

    @Inject
    public CreateLocationService(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Override
    public LocationResult handleRequest(Controller input, Context context) {
        String locationName = toUpperCase(input.getLocation());
        Location existingLocation = locationDao.find(locationName);

        if (existingLocation != null) throw new LocationAlreadyExistException
                (String.format("%s already exist. Please choose a different location.", existingLocation.getLocationName()));

        if (isEmpty(locationName)) throw new InvalidAttributeException
                ("Please enter a valid location name.");

        Location location = new Location();
        location.setLocationName(locationName);

        locationDao.save(location);

        return new LocationResult.Builder()
                .withLocationName(new ModelConverter().locationConverter(location))
                .build();
    }
}
