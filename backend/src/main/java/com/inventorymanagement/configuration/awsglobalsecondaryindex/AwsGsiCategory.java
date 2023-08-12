package com.inventorymanagement.configuration.awsglobalsecondaryindex;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.inventorymanagement.table.Category;

import javax.inject.Inject;

public class AwsGsiCategory implements AwsGsiModule<Category, String> {

    @Inject
    public AwsGsiCategory() {}

    @Override
    public DynamoDBQueryExpression<Category> idIndexQueryExpression(String id) {
        return null;
    }

    @Override
    public DynamoDBQueryExpression<Category> availableIndexQueryExpression(String available) {
        return null;
    }

    @Override
    public DynamoDBQueryExpression<Category> categoryIndexQueryExpression(String category) {
        return null;
    }

    @Override
    public DynamoDBQueryExpression<Category> categoryAndAvailabilityIndexQueryExpression(String category, String available) {
        return null;
    }

    @Override
    public DynamoDBQueryExpression<Category> locationIndexQueryExpression(String location) {
        return null;

    }

    @Override
    public DynamoDBQueryExpression<Category> locationAndCategoryIndexQueryExpression(String location, String available) {
        return null;
    }

    @Override
    public DynamoDBScanExpression findAll() {
        return new DynamoDBScanExpression()
                .withConsistentRead(false);
    }
}
