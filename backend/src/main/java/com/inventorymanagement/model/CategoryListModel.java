package com.inventorymanagement.model;

import com.inventorymanagement.table.Category;

import java.util.List;
import java.util.Objects;

public class CategoryListModel {
    private List<Category> categoryList;

    public CategoryListModel(Builder builder) {
        this.categoryList = builder.categoryList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public String toString() {
        return "CategoryListModel{" +
                "CategoryList='" + categoryList + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ||this.getClass() != o.getClass()) return false;
        CategoryListModel that = (CategoryListModel) o;
        return Objects.equals(categoryList, that.categoryList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryList);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private List<Category> categoryList;

        public Builder withCategoryList(List<Category> categoryListToUse) {
            this.categoryList = categoryListToUse;
            return this;
        }

        public CategoryListModel build() {
            return new CategoryListModel(this);
        }
    }
}
