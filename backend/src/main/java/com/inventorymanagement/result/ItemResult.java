package com.inventorymanagement.result;

import com.inventorymanagement.model.ItemListModel;
import com.inventorymanagement.model.ItemModel;

public class ItemResult {
    private ItemModel item;
    private ItemListModel itemList;

    public ItemResult (Builder builder) {
        this.item = builder.item;
        this.itemList = builder.itemList;
    }

    public ItemModel getItem() {
        return item;
    }

    public void setItem(ItemModel item) {
        this.item = item;
    }

    public ItemListModel getItemList() {
        return itemList;
    }

    public void setItemList(ItemListModel itemList) {
        this.itemList = itemList;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private ItemModel item;
        private ItemListModel itemList;

        public Builder withItem(ItemModel itemToUse) {
            this.item = itemToUse;
            return this;
        }

        public Builder withItemList(ItemListModel itemListToUse) {
            this.itemList = itemListToUse;
            return this;
        }

        public ItemResult build() {
            return new ItemResult(this);
        }
    }
}
