package com.inventorymanagement.table;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Data;

import java.util.Objects;

@Data
@DynamoDBTable(tableName = "IM-Item")
public class Item {

    public static final String ID_TYPE_INDEX = "id";
    public static final String CATEGORY_TYPE_INDEX = "categoryIndex";
    public static final String AVAILABLE_TYPE_INDEX = "availableIndex";

    private String name;
    private String id;
    private String category;
    private String available;
    private int quantity;
    private String location;

    public Item() {}

    @DynamoDBHashKey(attributeName = "name")
    @DynamoDBIndexRangeKey(globalSecondaryIndexNames = {CATEGORY_TYPE_INDEX, AVAILABLE_TYPE_INDEX},
                            attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = ID_TYPE_INDEX, attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = CATEGORY_TYPE_INDEX, attributeName = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @DynamoDBAttribute(attributeName = "available")
    @DynamoDBIndexHashKey(globalSecondaryIndexName = AVAILABLE_TYPE_INDEX, attributeName = "available")
    public String isAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    @DynamoDBAttribute(attributeName = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @DynamoDBAttribute(attributeName = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", available'=" + available +
                ", quantity=" + quantity +
                ", location='" + location + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Item that = (Item) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
