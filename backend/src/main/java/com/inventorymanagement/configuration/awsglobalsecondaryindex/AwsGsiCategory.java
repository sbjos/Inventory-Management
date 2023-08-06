package com.inventorymanagement.configuration.awsglobalsecondaryindex;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.inventorymanagement.table.Category;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

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
    public DynamoDBQueryExpression<Category> findAll() {
        Map<String, AttributeValue> list = new HashMap<>();
        return new DynamoDBQueryExpression<Category>()
                .withConsistentRead(false);
    }
}
