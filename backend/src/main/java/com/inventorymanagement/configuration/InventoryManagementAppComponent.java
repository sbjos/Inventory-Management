package com.inventorymanagement.configuration;

import com.inventorymanagement.configuration.awsglobalsecondaryindex.AwsGsiCategory;
import com.inventorymanagement.configuration.awsglobalsecondaryindex.AwsGsiItem;
import com.inventorymanagement.dao.CategoryDao;
import com.inventorymanagement.dao.ItemDao;
import com.inventorymanagement.service.*;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {AwsDynamoDBConfiguration.class})
public interface InventoryManagementAppComponent {
    ItemDao provideItemDao();
    CategoryDao provideCategoryDao();
    CreateItemService provideCreateItemService();
    GetItemService provideGetItemService();
    UpdateItemService provideUpdateItemService();
    DeleteItemService provideDeleteItemService();
    CreateCategoryService provideCreateCategoryService();
    DeleteCategoryService provideDeleteCategoryService();
}
