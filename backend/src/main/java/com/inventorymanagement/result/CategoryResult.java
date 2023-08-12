package com.inventorymanagement.result;

import com.inventorymanagement.model.CategoryListModel;
import com.inventorymanagement.model.CategoryModel;

public class CategoryResult {
    private CategoryModel category;
    private CategoryListModel categoryList;

    public CategoryResult (Builder builder) {
        this.category = builder.categoryName;
        this.categoryList = builder.categoryList;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public CategoryListModel getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(CategoryListModel categoryList) {
        this.categoryList = categoryList;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private CategoryModel categoryName;
        private CategoryListModel categoryList;

        public Builder withCategoryName(CategoryModel categoryNameToUse) {
            this.categoryName = categoryNameToUse;
            return this;
        }

        public Builder withCategoryList(CategoryListModel categoryListToUse) {
            this.categoryList = categoryListToUse;
            return this;
        }

        public CategoryResult build() {
            return new CategoryResult(this);
        }
    }
}
