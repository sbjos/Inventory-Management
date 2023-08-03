package com.inventorymanagement.model;

import java.util.Objects;

public class CategoryModel {
    private String category;

    public CategoryModel(Builder builder) {
        this.category = builder.category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CategoryModel{" +
                "category='" + category + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ||this.getClass() != o.getClass()) return false;
        CategoryModel that = (CategoryModel) o;
        return Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String category;

        public Builder withCategory(String categoryToUse) {
            this.category = categoryToUse;
            return this;
        }

        public CategoryModel build() {
            return new CategoryModel(this);
        }
    }
}
