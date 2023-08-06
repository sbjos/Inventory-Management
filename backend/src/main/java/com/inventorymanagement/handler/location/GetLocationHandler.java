package com.inventorymanagement.handler.location;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.inventorymanagement.configuration.DaggerInventoryManagementAppComponent;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.result.LocationResult;
import com.inventorymanagement.service.location.GetLocationService;

public class GetLocationHandler implements RequestHandler<Controller, LocationResult> {

    @Override
    public LocationResult handleRequest(Controller input, Context context) {
        return getLocationService().handleRequest(input, context);
    }

    private GetLocationService getLocationService() {
        return DaggerInventoryManagementAppComponent.create().provideGetLocationService();
    }
}
