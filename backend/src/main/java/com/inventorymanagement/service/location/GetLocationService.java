package com.inventorymanagement.service.location;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.LocationDao;
import com.inventorymanagement.exception.ItemListNotFoundException;
import com.inventorymanagement.exception.LocationNotFoundException;
import com.inventorymanagement.result.LocationResult;
import com.inventorymanagement.table.Location;
import com.inventorymanagement.utility.ModelConverter;

import javax.inject.Inject;


public class GetLocationService implements RequestHandler<Controller, LocationResult> {
    private final LocationDao locationDao;

    @Inject
    public GetLocationService(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Override
    public LocationResult handleRequest(Controller input, Context context) {
        String location = input.getLocation();
        boolean findAll = input.FindAll();

        if (findAll) return findAll();

        if (location == null) throw new ItemListNotFoundException
                ("Unable to find the list of items");

        return find(location);
    }

    private LocationResult find(String locationName) {
        Location location = locationDao.find(locationName);

        if (location == null) throw new LocationNotFoundException
                (String.format("Unable to find %s location. It may not exist.", locationName));

        return LocationResult.builder()
                .withLocation(new ModelConverter().locationConverter(location))
                .build();
    }

    private LocationResult findAll() {

        return LocationResult.builder()
                .withLocationList(new ModelConverter().locationListConverter(locationDao.findAll()))
                .build();
    }
}
