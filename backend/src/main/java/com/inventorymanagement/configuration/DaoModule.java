package com.inventorymanagement.configuration;


import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;

public interface DaoModule <Class, Type> {
    public void save(Class item);

    public Class find(Type name);

    public PaginatedQueryList<Class> findById(Type id);

    public PaginatedQueryList<Class> findByCategory(Type category, Type name);

    public PaginatedQueryList<Class> findByByAvailability(Type name, Type availability);

    public PaginatedScanList<Class> findAll();

    public void delete(Type name);
}
