package com.inventorymanagement.model;

import java.util.Objects;

public class CategoryModel {
    private String categoryName;

    public CategoryModel(Builder builder) {
        this.categoryName = builder.categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "CategoryModel{" +
                "Category='" + categoryName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ||this.getClass() != o.getClass()) return false;
        CategoryModel that = (CategoryModel) o;
        return Objects.equals(categoryName, that.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryName);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String categoryName;

        public Builder withCategoryName(String categoryNameToUse) {
            this.categoryName = categoryNameToUse;
            return this;
        }

        public CategoryModel build() {
            return new CategoryModel(this);
        }
    }
}
