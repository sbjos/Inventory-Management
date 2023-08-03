package com.inventorymanagement.model;

import com.inventorymanagement.table.Item;

import java.util.List;

public class ItemListModel {
    private List<Item> itemList;

    public ItemListModel(Builder builder) {
        this.itemList = builder.itemList;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "ItemListModel{" +
                "itemList=" + "\n" + itemList + '\'' +
                '}';
    }



    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private List<Item> itemList;

        public Builder withItemList(List<Item> itemListToUse) {
            this.itemList = itemListToUse;
            return this;
        }

        public ItemListModel build() {
            return new ItemListModel(this);
        }
    }
}
