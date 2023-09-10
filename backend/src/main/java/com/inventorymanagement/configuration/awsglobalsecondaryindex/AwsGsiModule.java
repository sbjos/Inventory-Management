package com.inventorymanagement.configuration.awsglobalsecondaryindex;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

public interface AwsGsiModule<Class, Type> {

    public DynamoDBQueryExpression<Class> idIndexQueryExpression(Type id);

    public DynamoDBQueryExpression<Class> availableIndexQueryExpression(Type available);

    public DynamoDBQueryExpression<Class> categoryIndexQueryExpression(Type category);

    public DynamoDBQueryExpression<Class> categoryAndAvailabilityIndexQueryExpression(Type category, Type available);

    public DynamoDBQueryExpression<Class> locationIndexQueryExpression(Type location);

    public DynamoDBQueryExpression<Class> locationAndCategoryIndexQueryExpression(Type location, Type category);

//    public DynamoDBQueryExpression<Class> findAll();
    public DynamoDBScanExpression findAll();
}

