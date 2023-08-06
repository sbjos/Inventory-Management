package com.inventorymanagement.configuration.awsglobalsecondaryindex;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.inventorymanagement.table.Location;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class AwsGsiLocation implements AwsGsiModule<Location, String> {

    @Inject
    public AwsGsiLocation() {}

    @Override
    public DynamoDBQueryExpression<Location
            > idIndexQueryExpression(String id) {
        return null;
    }

    @Override
    public DynamoDBQueryExpression<Location
            > availableIndexQueryExpression(String available) {
        return null;
    }

    @Override
    public DynamoDBQueryExpression<Location
            > categoryIndexQueryExpression(String category) {
        return null;
    }

    @Override
    public DynamoDBQueryExpression<Location
            > categoryIndexQueryExpression(String category, String available) {
        return null;
    }

    @Override
    public DynamoDBQueryExpression<Location
            > locationIndexQueryExpression(String location) {
        return null;

    }

    @Override
    public DynamoDBQueryExpression<Location
            > locationIndexQueryExpression(String location, String available) {
        return null;
    }

    @Override
    public DynamoDBQueryExpression<Location
            > findAll() {
        Map<String, AttributeValue> list = new HashMap<>();
        return new DynamoDBQueryExpression<Location
                >()
                .withConsistentRead(false);
    }
}
