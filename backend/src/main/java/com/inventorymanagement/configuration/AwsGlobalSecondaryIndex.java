package com.inventorymanagement.configuration;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.inventorymanagement.table.Item;

import java.util.HashMap;
import java.util.Map;

public class AwsGlobalSecondaryIndex {
    public static DynamoDBQueryExpression<Item> idIndexQueryExpression(String id) {
        Map<String, AttributeValue> list = new HashMap<>();
        list.put(":id", new AttributeValue().withS(id));
        return new DynamoDBQueryExpression<Item>()
                .withIndexName("ID-Index")
                .withConsistentRead(false)
                .withKeyConditionExpression("id = :id")
                .withExpressionAttributeValues(list);
    }

    public static DynamoDBQueryExpression<Item> categoryIndexQueryExpression(String category, String name) {
        Map<String, AttributeValue> list = new HashMap<>();
        list.put(":category", new AttributeValue().withS(category));
        list.put(":name", new AttributeValue().withS("name"));
        return new DynamoDBQueryExpression<Item>()
                .withIndexName("ID-Index")
                .withConsistentRead(false)
                .withKeyConditionExpression("id = :id")
                .withExpressionAttributeValues(list);
    }

    public static DynamoDBQueryExpression<Item> availableIndexQueryExpression(String availability, String name) {
        Map<String, AttributeValue> list = new HashMap<>();
        list.put(":available", new AttributeValue().withS(availability));
        list.put(":name", new AttributeValue().withS(name));
        return new DynamoDBQueryExpression<Item>()
                .withIndexName("ID-Index")
                .withConsistentRead(false)
                .withKeyConditionExpression("id = :id")
                .withExpressionAttributeValues(list);
    }

//    public static DynamoDBQueryExpression<Item> findAll() {
//        return new DynamoDBQueryExpression<Item>()
//                .withConsistentRead(false);
//    }
//
//    public static DynamoDBQueryExpression<Category> findAll() {
//        return new DynamoDBQueryExpression<Item>()
//                .withConsistentRead(false);
//    }
}
