package com.inventorymanagement.service.location;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.dao.LocationDao;
import com.inventorymanagement.exception.InvalidAttributeException;
import com.inventorymanagement.exception.LocationNotFoundException;
import com.inventorymanagement.result.LocationResult;
import com.inventorymanagement.table.Location;
import com.inventorymanagement.utility.ModelConverter;

import javax.inject.Inject;

import static com.inventorymanagement.utility.ServiceUtility.*;

public class GetLocationService implements RequestHandler<Controller, LocationResult> {
    private final LocationDao locationDao;

    @Inject
    public GetLocationService(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Override
    public LocationResult handleRequest(Controller input, Context context) {
        if (input.isAll()) return findAll();

        String locationName = toUpperCase(input.getLocation());

        if (isEmpty(locationName)) throw new InvalidAttributeException
                ("Please enter a valid input");

        return find(locationName);
    }

    private LocationResult find(String locationName) {
        Location location = locationDao.find(locationName);

        if (location == null) throw new LocationNotFoundException
                (String.format("Unable to find %s location. It may not exist.", locationName));

        return LocationResult.builder()
                .withLocationName(new ModelConverter().locationConverter(location))
                .build();
    }

    private LocationResult findAll() {
        return LocationResult.builder()
                .withLocationList(new ModelConverter().locationListConverter(locationDao.findAll()))
                .build();
    }
}
