package com.inventorymanagement.model;

import com.inventorymanagement.table.Category;

import java.util.List;
import java.util.Objects;

public class CategoryListModel {
    private List<Category> withCategoryList;

    public CategoryListModel(Builder builder) {
        this.withCategoryList = builder.withCategoryList;
    }

    public List<Category> getCategory() {
        return withCategoryList;
    }

    public void setCategory(List<Category> withCategoryList) {
        this.withCategoryList = withCategoryList;
    }

    @Override
    public String toString() {
        return "CategoryModel{" +
                "withCategoryList='" + withCategoryList + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ||this.getClass() != o.getClass()) return false;
        CategoryListModel that = (CategoryListModel) o;
        return Objects.equals(withCategoryList, that.withCategoryList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(withCategoryList);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private List<Category> withCategoryList;

        public Builder withCategoryList(List<Category> withCategoryListToUse) {
            this.withCategoryList = withCategoryListToUse;
            return this;
        }

        public CategoryListModel build() {
            return new CategoryListModel(this);
        }
    }
}
