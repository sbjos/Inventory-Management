package com.inventorymanagement.model;

import java.util.Objects;

public class ItemModel {
    private String itemName;
    private String itemId;
    private String itemCategory;
    private String availability;
    private long itemQuantity;
    private String itemLocation;

    public ItemModel(Builder builder) {
        this.itemName = builder.itemName;
        this.itemId = builder.id;
        this.itemCategory = builder.category;
        this.availability = builder.availability;
        this.itemQuantity = builder.quantity;
        this.itemLocation = builder.itemLocation;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public long getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(long itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getItemLocation() {
        return itemLocation;
    }

    public void setItemLocation(String itemLocation) {
        this.itemLocation = itemLocation;
    }

    @Override
    public String toString() {
        return "ItemModel{" +
                "Name='" + itemName + '\'' +
                ", ID='" + itemId + '\'' +
                ", Category='" + itemCategory + '\'' +
                ", Available'=" + availability +
                ", Quantity=" + itemQuantity +
                ", Location='" + itemLocation + '\'' +
                '}' +  "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ||this.getClass() != o.getClass()) return false;
        ItemModel that = (ItemModel) o;
        return Objects.equals(itemName, that.itemName) && Objects.equals(itemId, that.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, itemId);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String itemName;
        private String id;
        private String category;
        private String availability;
        private int quantity;
        private String itemLocation;

        public Builder withItemName(String itemNameToUse) {
            this.itemName = itemNameToUse;
            return this;
        }

        public Builder withId(String idToUse) {
            this.id = idToUse;
            return this;
        }

        public Builder withCategory(String categoryToUse) {
            this.category = categoryToUse;
            return this;
        }

        public Builder withAvailability(String availabilityToUse) {
            this.availability = availabilityToUse;
            return this;
        }

        public Builder withQuantity(int quantityToUse) {
            this.quantity = quantityToUse;
            return this;
        }

        public Builder withItemLocation(String itemLocationToUse) {
            this.itemLocation = itemLocationToUse;
            return this;
        }

        public ItemModel build() {
            return new ItemModel(this);
        }
    }
}
