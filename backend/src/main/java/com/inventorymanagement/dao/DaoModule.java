package com.inventorymanagement.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;

public interface DaoModule <Class, Type> {

    public void save(Class object);

    public Class find(Type name);

    public PaginatedQueryList<Class> findById(Type id);

    public PaginatedQueryList<Class> findByByAvailability(Type available);

    public PaginatedQueryList<Class> findByCategory(Type category);

    public PaginatedQueryList<Class> findByCategoryAndAvailability(Type category, Type available);

    public PaginatedQueryList<Class> findByByLocation(Type location);

    public PaginatedQueryList<Class> findByByLocationAndCategory(Type location, Type category);

    public PaginatedScanList<Class> findAll();

    public void delete(Class object);
}
