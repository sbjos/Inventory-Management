package com.inventorymanagement.configuration.awsglobalsecondaryindex;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.inventorymanagement.table.Item;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class AwsGsiItem implements AwsGsiModule<Item, String> {

    @Inject
    public AwsGsiItem() {}

    @Override
    public DynamoDBQueryExpression<Item> idIndexQueryExpression(String id) {
        Map<String, AttributeValue> list = new HashMap<>();
        list.put(":id", new AttributeValue().withS(id));
        return new DynamoDBQueryExpression<Item>()
                .withIndexName("ID-Index")
                .withConsistentRead(false)
                .withKeyConditionExpression("id = :id")
                .withExpressionAttributeValues(list);
    }

    @Override
    public DynamoDBQueryExpression<Item> categoryIndexQueryExpression(String category) {
        Map<String, AttributeValue> list = new HashMap<>();
        list.put(":category", new AttributeValue().withS(category));
        return new DynamoDBQueryExpression<Item>()
                .withIndexName("ID-Index")
                .withConsistentRead(false)
                .withKeyConditionExpression("id = :id")
                .withExpressionAttributeValues(list);
    }

    @Override
    public DynamoDBQueryExpression<Item> categoryIndexQueryExpression(String category, String name) {
        Map<String, AttributeValue> list = new HashMap<>();
        list.put(":category", new AttributeValue().withS(category));
        list.put(":name", new AttributeValue().withS(name));
        return new DynamoDBQueryExpression<Item>()
                .withIndexName("ID-Index")
                .withConsistentRead(false)
                .withKeyConditionExpression("id = :id")
                .withExpressionAttributeValues(list);
    }

    @Override
    public DynamoDBQueryExpression<Item> availableIndexQueryExpression(String availability) {
        Map<String, AttributeValue> list = new HashMap<>();
        list.put(":available", new AttributeValue().withS(availability));
        return new DynamoDBQueryExpression<Item>()
                .withIndexName("ID-Index")
                .withConsistentRead(false)
                .withKeyConditionExpression("id = :id")
                .withExpressionAttributeValues(list);
    }

    @Override
    public DynamoDBQueryExpression<Item> availableIndexQueryExpression(String availability, String name) {
        Map<String, AttributeValue> list = new HashMap<>();
        list.put(":available", new AttributeValue().withS(availability));
        list.put(":name", new AttributeValue().withS(name));
        return new DynamoDBQueryExpression<Item>()
                .withIndexName("ID-Index")
                .withConsistentRead(false)
                .withKeyConditionExpression("id = :id")
                .withExpressionAttributeValues(list);
    }

    @Override
    public DynamoDBQueryExpression<Item> findAll() {
        Map<String, AttributeValue> list = new HashMap<>();
        return new DynamoDBQueryExpression<Item>()
                .withConsistentRead(false);
    }
}
