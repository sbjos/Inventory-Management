package com.inventorymanagement.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.inventorymanagement.configuration.awsglobalsecondaryindex.AwsGsiLocation;
import com.inventorymanagement.table.Location;

import javax.inject.Inject;

public class LocationDao implements DaoModule<Location, String> {
    private final DynamoDBMapper dynamoDBMapper;
    private final AwsGsiLocation awsGsiLocation;

    @Inject
    public LocationDao(DynamoDBMapper dynamoDBMapper, AwsGsiLocation awsGsiLocation) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.awsGsiLocation = awsGsiLocation;
    }

    @Override
    public void save(Location location) {
        dynamoDBMapper.save(location);
    }

    @Override
    public Location find(String name) {
        return dynamoDBMapper.load(Location.class, name);
    }

    @Override
    public PaginatedQueryList<Location> findById(String id) {
        return null;
    }

    @Override
    public PaginatedQueryList<Location> findByByAvailability(String available) {
        return null;
    }

    @Override
    public PaginatedQueryList<Location> findByCategory(String category) {
        return null;
    }

    @Override
    public PaginatedQueryList<Location> findByCategoryAndAvailability(String category, String available) {
        return null;
    }

    @Override
    public PaginatedQueryList<Location> findByByLocation(String location) {
        return null;
    }

    @Override
    public PaginatedQueryList<Location> findByByLocationAndCategory(String location, String category) {
        return null;
    }

    @Override
    public PaginatedScanList<Location> findAll() {
        return dynamoDBMapper.scan(Location.class, awsGsiLocation.findAll());
    }

    @Override
    public void delete(Location location) {
        dynamoDBMapper.delete(location);
    }
}
