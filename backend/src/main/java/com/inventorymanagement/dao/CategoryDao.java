package com.inventorymanagement.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.inventorymanagement.configuration.DaoModule;
import com.inventorymanagement.table.Category;

import javax.inject.Inject;

public class CategoryDao implements DaoModule<Category, String> {
    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public CategoryDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public void save(Category item) {
        dynamoDBMapper.save(item);
    }

    @Override
    public Category find(String name) {
        return dynamoDBMapper.load(Category.class, name);
    }

    @Override
    public PaginatedQueryList<Category> findById(String id) {
        return null;
    }

    @Override
    public PaginatedQueryList<Category> findByCategory(String category, String name) {
        return null;
    }

    @Override
    public PaginatedQueryList<Category> findByByAvailability(String name, String availability) {
        return null;
    }

    @Override
    public PaginatedScanList<Category> findAll() {
        return dynamoDBMapper.scan(Category.class, new DynamoDBScanExpression());
    }

    @Override
    public void delete(String name) {
        dynamoDBMapper.delete(name);
    }
}
