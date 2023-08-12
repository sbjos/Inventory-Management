package com.inventorymanagement.table;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.Objects;

@DynamoDBTable(tableName = "IM-Item")
public class Item {
    public static final String ID_TYPE_INDEX = "ID-Index";
    public static final String AVAILABLE_TYPE_INDEX = "Available-Index";
    public static final String CATEGORY_TYPE_INDEX = "Category-Index";
    public static final String LOCATION_TYPE_INDEX = "Location-Index";

    private String itemName;
    private String itemId;
    private String itemCategory;
    private String availability;
    private int itemQuantity;
    private String itemLocation;

    public Item() {}

    @DynamoDBHashKey(attributeName = "ItemName")
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @DynamoDBAttribute(attributeName = "ItemID")
    @DynamoDBIndexHashKey(globalSecondaryIndexName = ID_TYPE_INDEX, attributeName = "ItemID")
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @DynamoDBAttribute(attributeName = "ItemCategory")
    @DynamoDBIndexHashKey(globalSecondaryIndexName = CATEGORY_TYPE_INDEX, attributeName = "ItemCategory")
    @DynamoDBIndexRangeKey(globalSecondaryIndexNames = {LOCATION_TYPE_INDEX}, attributeName = "ItemCategory")
    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    @DynamoDBAttribute(attributeName = "Availability")
    @DynamoDBIndexHashKey(globalSecondaryIndexName = AVAILABLE_TYPE_INDEX, attributeName = "Availability")
    @DynamoDBIndexRangeKey(globalSecondaryIndexNames = {CATEGORY_TYPE_INDEX}, attributeName = "Availability")
    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @DynamoDBAttribute(attributeName = "ItemQuantity")
    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    @DynamoDBAttribute(attributeName = "ItemLocation")
    @DynamoDBIndexHashKey(globalSecondaryIndexName = LOCATION_TYPE_INDEX, attributeName = "ItemLocation")
    public String getItemLocation() {
        return itemLocation;
    }

    public void setItemLocation(String itemLocation) {
        this.itemLocation = itemLocation;
    }

    @Override
    public String toString() {
        return "Item{" +
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
        if (o == null || this.getClass() != o.getClass()) return false;
        Item that = (Item) o;
        return Objects.equals(itemId, that.itemId) && Objects.equals(itemName, that.itemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, itemName);
    }
}
