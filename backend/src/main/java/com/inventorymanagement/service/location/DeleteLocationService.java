package com.inventorymanagement.service.location;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.LocationDao;
import com.inventorymanagement.exception.InvalidAttributeException;
import com.inventorymanagement.exception.LocationNotFoundException;
import com.inventorymanagement.model.LocationModel;
import com.inventorymanagement.result.LocationResult;
import com.inventorymanagement.table.Location;

import javax.inject.Inject;

import static com.inventorymanagement.utility.ServiceUtility.*;

public class DeleteLocationService implements RequestHandler<Controller, LocationResult> {
    private final LocationDao locationDao;

    @Inject
    public DeleteLocationService(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Override
    public LocationResult handleRequest(Controller input, Context context) {
        String locationName = toUpperCase(input.getLocation());
        Location location = locationDao.find(locationName);

        if (isEmpty(locationName)) throw new InvalidAttributeException
                ("Please enter a valid input");

        // SafeGuard
        if (location == null) throw new LocationNotFoundException
                ("Unable to find this location to delete. It may not exist.");

        locationDao.delete(location);

        return LocationResult.builder()
                .withLocationName(LocationModel.builder().withLocationName(locationName).build())
                .build();
    }
}
