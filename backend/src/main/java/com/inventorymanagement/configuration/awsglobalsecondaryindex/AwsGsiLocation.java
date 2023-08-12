package com.inventorymanagement.configuration.awsglobalsecondaryindex;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.inventorymanagement.table.Location;

import javax.inject.Inject;

public class AwsGsiLocation implements AwsGsiModule<Location, String> {

    @Inject
    public AwsGsiLocation() {}

    @Override
    public DynamoDBQueryExpression<Location> idIndexQueryExpression(String id) {
        return null;
    }

    @Override
    public DynamoDBQueryExpression<Location> availableIndexQueryExpression(String available) {
        return null;
    }

    @Override
    public DynamoDBQueryExpression<Location> categoryIndexQueryExpression(String category) {
        return null;
    }

    @Override
    public DynamoDBQueryExpression<Location> categoryAndAvailabilityIndexQueryExpression(String category, String available) {
        return null;
    }

    @Override
    public DynamoDBQueryExpression<Location> locationIndexQueryExpression(String location) {
        return null;
    }

    @Override
    public DynamoDBQueryExpression<Location> locationAndCategoryIndexQueryExpression(String location, String available) {
        return null;
    }

    @Override
    public DynamoDBScanExpression findAll() {
        return new DynamoDBScanExpression()
                .withConsistentRead(false);
    }
}
