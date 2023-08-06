package com.inventorymanagement.configuration.awsglobalsecondaryindex;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

public interface AwsGsiModule<Class, Type> {

    public DynamoDBQueryExpression<Class> idIndexQueryExpression(Type id);

    public DynamoDBQueryExpression<Class> availableIndexQueryExpression(Type available);

    public DynamoDBQueryExpression<Class> categoryIndexQueryExpression(Type category);

    public DynamoDBQueryExpression<Class> categoryIndexQueryExpression(Type category, Type available);

    public DynamoDBQueryExpression<Class> locationIndexQueryExpression(Type location);

    public DynamoDBQueryExpression<Class> locationIndexQueryExpression(Type location, Type category);

    public DynamoDBQueryExpression<Class> findAll();
}
