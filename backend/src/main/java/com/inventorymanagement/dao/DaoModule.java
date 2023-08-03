package com.inventorymanagement.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;

public interface DaoModule <Class, Type> {

    public void save(Class item);

    public Class find(Type name);

    public PaginatedQueryList<Class> findById(Type id);

    public PaginatedQueryList<Class> findByCategory(Type category);

    public PaginatedQueryList<Class> findByCategory(Type category, Type name);

    public PaginatedQueryList<Class> findByByAvailability(Type name);

    public PaginatedQueryList<Class> findByByAvailability(Type name, Type availability);

    public PaginatedQueryList<Class> findAll();

    public void delete(Type name);
}
