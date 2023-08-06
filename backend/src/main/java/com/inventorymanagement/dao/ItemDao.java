package com.inventorymanagement.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.inventorymanagement.configuration.awsglobalsecondaryindex.AwsGsiItem;
import com.inventorymanagement.table.Item;

import javax.inject.Inject;

public class ItemDao implements DaoModule<Item, String> {
    private final DynamoDBMapper dynamoDBMapper;
    private final AwsGsiItem awsGsiItem;

    @Inject
    public ItemDao(DynamoDBMapper dynamoDBMapper, AwsGsiItem awsGsiItem) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.awsGsiItem = awsGsiItem;
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
                awsGsiItem.idIndexQueryExpression(id));
    }

    @Override
    public PaginatedQueryList<Item> findByByAvailability(String available) {
        return dynamoDBMapper.query(Item.class,
                awsGsiItem.availableIndexQueryExpression(available));
    }

    @Override
    public PaginatedQueryList<Item> findByCategory(String category) {
        return dynamoDBMapper.query(Item.class,
                awsGsiItem.categoryIndexQueryExpression(category));
    }

    @Override
    public PaginatedQueryList<Item> findByCategoryAndAvailability(String category, String available) {
        return dynamoDBMapper.query(Item.class,
                awsGsiItem.categoryAndAvailabilityIndexQueryExpression(category, available));
    }

    @Override
    public PaginatedQueryList<Item> findByByLocation(String location) {
        return dynamoDBMapper.query(Item.class,
                awsGsiItem.locationIndexQueryExpression(location));
    }

    @Override
    public PaginatedQueryList<Item> findByByLocationAndCategory(String location, String category) {
        return dynamoDBMapper.query(Item.class,
                awsGsiItem.locationAndCategoryIndexQueryExpression(location, category));
    }

    @Override
    public PaginatedQueryList<Item> findAll() {
        return dynamoDBMapper.query(Item.class,
                awsGsiItem.findAll());
    }

    @Override
    public void delete(String nameOrId) {
        dynamoDBMapper.delete(nameOrId);
    }
}
