package com.inventorymanagement.table;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.Objects;

@DynamoDBTable(tableName = "IM-Category")
public class Category {
    private String category;

    public Category() {}

    @DynamoDBHashKey(attributeName = "category")
    public String getCategoryName() {
        return category;
    }

    public void setCategoryName(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category='" + category + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Category that = (Category) o;
        return Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category);
    }
}
