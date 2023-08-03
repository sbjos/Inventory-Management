package com.inventorymanagement.result;

import com.inventorymanagement.model.ItemModel;

public class ItemResult {
    private ItemModel item;

    public ItemResult (Builder builder) {
        this.item = builder.item;
    }

    public ItemModel getItem() {
        return item;
    }

    public void setItem(ItemModel item) {
        this.item = item;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private ItemModel item;

        public Builder withItem(ItemModel item) {
            this.item = item;
            return this;
        }

        public ItemResult build() {
            return new ItemResult(this);
        }
    }
}
