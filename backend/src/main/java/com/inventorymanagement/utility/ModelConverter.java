package com.inventorymanagement.utility;

import com.inventorymanagement.model.*;
import com.inventorymanagement.table.Category;
import com.inventorymanagement.table.Item;
import com.inventorymanagement.table.Location;

import java.util.List;

public class ModelConverter {

    public ItemModel itemConverter(Item item) {
        return ItemModel.builder()
                .withItemName(item.getItemName())
                .withId(item.getItemId())
                .withCategory(item.getItemCategory())
                .withAvailability(item.getAvailability())
                .withQuantity(item.getItemQuantity())
                .withItemLocation(item.getItemLocation())
                .build();
    }

    public ItemListModel itemListConverter(List<Item> item) {
        return ItemListModel.builder()
                .withItemList(item)
                .build();
    }

    public CategoryModel categoryConverter(Category category) {
        return CategoryModel.builder()
                .withCategoryName(category.getCategoryName())
                .build();
    }

    public CategoryListModel categoryListConverter(List<Category> category) {
        return CategoryListModel.builder()
                .withCategoryList(category)
                .build();
    }

    public LocationModel locationConverter(Location location) {
        return LocationModel.builder()
                .withLocationName(location.getLocationName())
                .build();
    }
public LocationListModel locationListConverter(List<Location> location) {
        return LocationListModel.builder()
                .withLocationList(location)
                .build();
    }
}
