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
                .withIndexName("IdIndex")
                .withConsistentRead(false)
                .withKeyConditionExpression("id = :id")
                .withExpressionAttributeValues(list);
    }

    @Override
    public DynamoDBQueryExpression<Item> availableIndexQueryExpression(String available) {
        Map<String, AttributeValue> list = new HashMap<>();
        list.put(":available", new AttributeValue().withS(available));
        return new DynamoDBQueryExpression<Item>()
                .withIndexName("AvailableIndex")
                .withConsistentRead(false)
                .withKeyConditionExpression("available = :available")
                .withExpressionAttributeValues(list);
    }

    @Override
    public DynamoDBQueryExpression<Item> categoryIndexQueryExpression(String category) {
        Map<String, AttributeValue> list = new HashMap<>();
        list.put(":category", new AttributeValue().withS(category));
        return new DynamoDBQueryExpression<Item>()
                .withIndexName("CategoryIndex")
                .withConsistentRead(false)
                .withKeyConditionExpression("category = :category")
                .withExpressionAttributeValues(list);
    }

    @Override
    public DynamoDBQueryExpression<Item> categoryIndexQueryExpression(String category, String available) {
        Map<String, AttributeValue> list = new HashMap<>();
        list.put(":category", new AttributeValue().withS(category));
        list.put(":available", new AttributeValue().withS(available));
        return new DynamoDBQueryExpression<Item>()
                .withIndexName("CategoryIndex")
                .withConsistentRead(false)
                .withKeyConditionExpression("category = :category and available = :available")
                .withExpressionAttributeValues(list);
    }

    @Override
    public DynamoDBQueryExpression<Item> locationIndexQueryExpression(String location) {
        Map<String, AttributeValue> list = new HashMap<>();
        list.put(":location", new AttributeValue().withS(location));
        return new DynamoDBQueryExpression<Item>()
                .withIndexName("AvailableIndex")
                .withConsistentRead(false)
                .withKeyConditionExpression("location = :location")
                .withExpressionAttributeValues(list);
    }
@Override
    public DynamoDBQueryExpression<Item> locationIndexQueryExpression(String location, String category) {
        Map<String, AttributeValue> list = new HashMap<>();
        list.put(":location", new AttributeValue().withS(location));
        list.put(":category", new AttributeValue().withS(category));
        return new DynamoDBQueryExpression<Item>()
                .withIndexName("AvailableIndex")
                .withConsistentRead(false)
                .withKeyConditionExpression("available = :available and category = :category")
                .withExpressionAttributeValues(list);
    }

    @Override
    public DynamoDBQueryExpression<Item> findAll() {
        Map<String, AttributeValue> list = new HashMap<>();
        return new DynamoDBQueryExpression<Item>()
                .withConsistentRead(false);
    }
}
