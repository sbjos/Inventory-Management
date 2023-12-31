package com.inventorymanagement.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.inventorymanagement.configuration.awsglobalsecondaryindex.AwsGsiCategory;
import com.inventorymanagement.table.Category;
import com.inventorymanagement.table.Item;

import javax.inject.Inject;

public class CategoryDao implements DaoModule<Category, String> {
    private final DynamoDBMapper dynamoDBMapper;
    private final AwsGsiCategory awsGsiCategory;

    @Inject
    public CategoryDao(DynamoDBMapper dynamoDBMapper, AwsGsiCategory awsGsiCategory) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.awsGsiCategory = awsGsiCategory;
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
    public PaginatedQueryList<Category> findByByAvailability(String available) {
        return null;
    }

    @Override
    public PaginatedQueryList<Category> findByCategory(String category) {
        return null;
    }

    @Override
    public PaginatedQueryList<Category> findByCategoryAndAvailability(String category, String available) {
        return null;
    }

    @Override
    public PaginatedQueryList<Category> findByByLocation(String location) {
        return null;
    }

    @Override
    public PaginatedQueryList<Category> findByByLocationAndCategory(String location, String category) {
        return null;
    }

    @Override
    public PaginatedScanList<Category> findAll() {
        return dynamoDBMapper.scan(Category.class, awsGsiCategory.findAll());
    }

    @Override
    public void delete(Category category) {
        dynamoDBMapper.delete(category);
    }
}
