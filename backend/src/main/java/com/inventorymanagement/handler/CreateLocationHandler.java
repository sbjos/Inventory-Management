package com.inventorymanagement.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.inventorymanagement.configuration.DaggerInventoryManagementAppComponent;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.result.LocationResult;
import com.inventorymanagement.service.CreateLocationService;

public class CreateLocationHandler implements RequestHandler<Controller, LocationResult> {

    @Override
    public LocationResult handleRequest(Controller input, Context context) {
        return createLocationService().handleRequest(input, context);
    }

    private CreateLocationService createLocationService() {
        return DaggerInventoryManagementAppComponent.create().provideCreateLocationService();
    }
}
