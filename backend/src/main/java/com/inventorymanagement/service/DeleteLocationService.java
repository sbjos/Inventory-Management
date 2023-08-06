package com.inventorymanagement.service;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.LocationDao;
import com.inventorymanagement.exception.CategoryNotFoundException;
import com.inventorymanagement.model.LocationModel;
import com.inventorymanagement.result.LocationResult;
import com.inventorymanagement.table.Location;

import javax.inject.Inject;

// TODO: Create test method
public class DeleteLocationService implements RequestHandler<Controller, LocationResult> {
    private final LocationDao locationDao;

    @Inject
    public DeleteLocationService(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Override
    public LocationResult handleRequest(Controller input, Context context) {
        String locationName = input.getLocation();
        Location location = locationDao.find(locationName);

        if (location == null) throw new CategoryNotFoundException
                ("Unable to find this location. It may not exist.");

        return LocationResult.builder()
                .withLocation(LocationModel.builder()
                        .withLocation(locationName)
                        .build())
                .build();
    }
}
