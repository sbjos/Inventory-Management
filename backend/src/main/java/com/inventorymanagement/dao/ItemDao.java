package com.inventorymanagement.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.inventorymanagement.configuration.AwsGlobalSecondaryIndex;
import com.inventorymanagement.configuration.DaoModule;
import com.inventorymanagement.table.Item;

import javax.inject.Inject;

public class ItemDao implements DaoModule<Item, String> {
    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public ItemDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public void save(Item item) {
        dynamoDBMapper.save(item);
    }

    @Override
    public Item find(String name) {
        return dynamoDBMapper.load(Item.class, name);
    }

    @Override
    public PaginatedQueryList<Item> findById(String id) {
        return dynamoDBMapper.query(Item.class,
                AwsGlobalSecondaryIndex.idIndexQueryExpression(id));
    };

    @Override
    public PaginatedQueryList<Item> findByCategory(String category, String name) {
        return dynamoDBMapper.query(Item.class,
                AwsGlobalSecondaryIndex.categoryIndexQueryExpression(category, name));
    }

    @Override
    public PaginatedQueryList<Item> findByByAvailability(String availability, String name) {
        return dynamoDBMapper.query(Item.class,
                AwsGlobalSecondaryIndex.availableIndexQueryExpression(name, availability));
    }

    @Override
    public PaginatedScanList<Item> findAll() {
        return dynamoDBMapper.scan(Item.class, new DynamoDBScanExpression());
    }

    @Override
    public void delete(String nameOrId) {
        dynamoDBMapper.delete(nameOrId);
    }
}
