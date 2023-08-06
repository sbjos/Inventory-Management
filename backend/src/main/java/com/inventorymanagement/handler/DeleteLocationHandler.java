package com.inventorymanagement.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.inventorymanagement.configuration.DaggerInventoryManagementAppComponent;
import com.inventorymanagement.controller.Controller;
import com.inventorymanagement.result.LocationResult;
import com.inventorymanagement.service.DeleteLocationService;

public class DeleteLocationHandler implements RequestHandler<Controller, LocationResult> {

    @Override
    public LocationResult handleRequest(Controller input, Context context) {
        return deleteLocationService().handleRequest(input, context);
    }

    private DeleteLocationService deleteLocationService() {
        return DaggerInventoryManagementAppComponent.create().provideDeleteLocationService();
    }
}
