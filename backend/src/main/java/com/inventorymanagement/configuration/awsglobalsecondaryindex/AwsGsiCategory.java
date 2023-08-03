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
        Map<String, AttributeValue> list = new HashMap<>();
        list.put(":id", new AttributeValue().withS(id));
        return new DynamoDBQueryExpression<Category>()
                .withIndexName("ID-Index")
                .withConsistentRead(false)
                .withKeyConditionExpression("id = :id")
                .withExpressionAttributeValues(list);
    }

    @Override
    public DynamoDBQueryExpression<Category> categoryIndexQueryExpression(String category) {
        Map<String, AttributeValue> list = new HashMap<>();
        list.put(":category", new AttributeValue().withS(category));
        return new DynamoDBQueryExpression<Category>()
                .withIndexName("ID-Index")
                .withConsistentRead(false)
                .withKeyConditionExpression("id = :id")
                .withExpressionAttributeValues(list);
    }

    @Override
    public DynamoDBQueryExpression<Category> categoryIndexQueryExpression(String category, String name) {
        Map<String, AttributeValue> list = new HashMap<>();
        list.put(":category", new AttributeValue().withS(category));
        list.put(":name", new AttributeValue().withS(name));
        return new DynamoDBQueryExpression<Category>()
                .withIndexName("ID-Index")
                .withConsistentRead(false)
                .withKeyConditionExpression("id = :id")
                .withExpressionAttributeValues(list);
    }

    @Override
    public DynamoDBQueryExpression<Category> availableIndexQueryExpression(String availability) {
        Map<String, AttributeValue> list = new HashMap<>();
        list.put(":available", new AttributeValue().withS(availability));
        return new DynamoDBQueryExpression<Category>()
                .withIndexName("ID-Index")
                .withConsistentRead(false)
                .withKeyConditionExpression("id = :id")
                .withExpressionAttributeValues(list);
    }

    @Override
    public DynamoDBQueryExpression<Category> availableIndexQueryExpression(String availability, String name) {
        Map<String, AttributeValue> list = new HashMap<>();
        list.put(":available", new AttributeValue().withS(availability));
        list.put(":name", new AttributeValue().withS(name));
        return new DynamoDBQueryExpression<Category>()
                .withIndexName("ID-Index")
                .withConsistentRead(false)
                .withKeyConditionExpression("id = :id")
                .withExpressionAttributeValues(list);
    }

    @Override
    public DynamoDBQueryExpression<Category> findAll() {
        Map<String, AttributeValue> list = new HashMap<>();
        return new DynamoDBQueryExpression<Category>()
                .withConsistentRead(false);
    }
}
