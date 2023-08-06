package com.inventorymanagement.configuration;

import com.inventorymanagement.configuration.awsglobalsecondaryindex.AwsGsiCategory;
import com.inventorymanagement.configuration.awsglobalsecondaryindex.AwsGsiItem;
import com.inventorymanagement.dao.CategoryDao;
import com.inventorymanagement.dao.ItemDao;
import com.inventorymanagement.service.category.CreateCategoryService;
import com.inventorymanagement.service.category.DeleteCategoryService;
import com.inventorymanagement.service.category.GetCategoryService;
import com.inventorymanagement.service.item.CreateItemService;
import com.inventorymanagement.service.item.DeleteItemService;
import com.inventorymanagement.service.item.GetItemService;
import com.inventorymanagement.service.item.UpdateItemService;
import com.inventorymanagement.service.location.CreateLocationService;
import com.inventorymanagement.service.location.DeleteLocationService;
import com.inventorymanagement.service.location.GetLocationService;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {AwsDynamoDBConfiguration.class})
public interface InventoryManagementAppComponent {
    AwsGsiItem provideAwsGsiItem();
    AwsGsiCategory provideAwsGsiCategory();
    ItemDao provideItemDao();
    CategoryDao provideCategoryDao();
    CreateItemService provideCreateItemService();
    GetItemService provideGetItemService();
    UpdateItemService provideUpdateItemService();
    DeleteItemService provideDeleteItemService();
    CreateCategoryService provideCreateCategoryService();
    GetCategoryService provideGetCategoryService();
    DeleteCategoryService provideDeleteCategoryService();
    CreateLocationService provideCreateLocationService();
    GetLocationService provideGetLocationService();
    DeleteLocationService provideDeleteLocationService();
}
