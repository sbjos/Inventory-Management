package com.inventorymanagement.configuration.awsglobalsecondaryindex;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

public interface AwsGsiModule<Class, Type> {

    public DynamoDBQueryExpression<Class> idIndexQueryExpression(Type id);

    public DynamoDBQueryExpression<Class> categoryIndexQueryExpression(Type category);

    public DynamoDBQueryExpression<Class> categoryIndexQueryExpression(Type category, Type name);

    public DynamoDBQueryExpression<Class> availableIndexQueryExpression(Type availability);

    public DynamoDBQueryExpression<Class> availableIndexQueryExpression(Type availability, Type name);

    public DynamoDBQueryExpression<Class> findAll();
}
