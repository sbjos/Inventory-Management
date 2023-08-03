package com.inventorymanagement.utility;

import com.inventorymanagement.model.CategoryModel;
import com.inventorymanagement.model.ItemListModel;
import com.inventorymanagement.model.ItemModel;
import com.inventorymanagement.table.Category;
import com.inventorymanagement.table.Item;

import java.util.List;

public class ModelConverter {

    /**
     * Converts a provided {@link Item} into a {@link ItemModel} representation.
     * @param item the playlist to convert
     * @return the converted playlist
     */
    public ItemModel itemConverter(Item item) {
        return ItemModel.builder()
                .withName(item.getName())
                .withId(item.getId())
                .withCategory(item.getCategory())
                .withAvailable(item.isAvailable())
                .withQuantity(item.getQuantity())
                .withLocation(item.getLocation())
                .build();
    }

    public ItemListModel itemListConverter(List<Item> item) {
        return ItemListModel.builder()
                .withItemList(item)
                .build();
    }

    public CategoryModel categoryConverter(Category category) {
        return CategoryModel.builder()
                .withCategory(category.getCategoryName())
                .build();
    }
}
