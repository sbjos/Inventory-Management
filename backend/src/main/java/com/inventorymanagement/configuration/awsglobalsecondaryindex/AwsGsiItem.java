package com.inventorymanagement.configuration.awsglobalsecondaryindex;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
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
        list.put(":ItemID", new AttributeValue().withS(id));
        return new DynamoDBQueryExpression<Item>()
                .withIndexName("ID-Index")
                .withConsistentRead(false)
                .withKeyConditionExpression("ItemID = :ItemID")
                .withExpressionAttributeValues(list);
    }

    @Override
    public DynamoDBQueryExpression<Item> availableIndexQueryExpression(String available) {
        Map<String, AttributeValue> list = new HashMap<>();
        list.put(":Availability", new AttributeValue().withS(available));
        return new DynamoDBQueryExpression<Item>()
                .withIndexName("Available-Index")
                .withConsistentRead(false)
                .withKeyConditionExpression("Availability = :Availability")
                .withExpressionAttributeValues(list);
    }

    @Override
    public DynamoDBQueryExpression<Item> categoryIndexQueryExpression(String category) {
        Map<String, AttributeValue> list = new HashMap<>();
        list.put(":ItemCategory", new AttributeValue().withS(category));
        return new DynamoDBQueryExpression<Item>()
                .withIndexName("Category-Index")
                .withConsistentRead(false)
                .withKeyConditionExpression("ItemCategory = :ItemCategory")
                .withExpressionAttributeValues(list);
    }

    @Override
    public DynamoDBQueryExpression<Item> categoryAndAvailabilityIndexQueryExpression(String category, String available) {
        Map<String, AttributeValue> list = new HashMap<>();
        list.put(":ItemCategory", new AttributeValue().withS(category));
        list.put(":Availability", new AttributeValue().withS(available));
        return new DynamoDBQueryExpression<Item>()
                .withIndexName("Category-Index")
                .withConsistentRead(false)
                .withKeyConditionExpression("ItemCategory = :ItemCategory and Availability = :Availability")
                .withExpressionAttributeValues(list);
    }

    @Override
    public DynamoDBQueryExpression<Item> locationIndexQueryExpression(String location) {
        Map<String, AttributeValue> list = new HashMap<>();
        list.put(":ItemLocation", new AttributeValue().withS(location));
        return new DynamoDBQueryExpression<Item>()
                .withIndexName("Location-Index")
                .withConsistentRead(false)
                .withKeyConditionExpression("ItemLocation = :ItemLocation")
                .withExpressionAttributeValues(list);
    }
@Override
    public DynamoDBQueryExpression<Item> locationAndCategoryIndexQueryExpression(String location, String category) {
        Map<String, AttributeValue> list = new HashMap<>();
        list.put(":ItemLocation", new AttributeValue().withS(location));
        list.put(":ItemCategory", new AttributeValue().withS(category));
        return new DynamoDBQueryExpression<Item>()
                .withIndexName("Location-Index")
                .withConsistentRead(false)
                .withKeyConditionExpression("ItemLocation = :ItemLocation and ItemCategory = :ItemCategory")
                .withExpressionAttributeValues(list);
    }

    @Override
    public DynamoDBScanExpression findAll() {
        return new DynamoDBScanExpression()
                .withConsistentRead(false);
    }
}
