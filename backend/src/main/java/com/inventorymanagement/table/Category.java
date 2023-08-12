package com.inventorymanagement.table;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.Objects;

@DynamoDBTable(tableName = "IM-Category")
public class Category {
    private String categoryName;

    public Category() {}

    @DynamoDBHashKey(attributeName = "CategoryName")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String category) {
        this.categoryName = category;
    }

    @Override
    public String toString() {
        return "Category{" +
                "Category='" + categoryName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Category that = (Category) o;
        return Objects.equals(categoryName, that.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryName);
    }
}
