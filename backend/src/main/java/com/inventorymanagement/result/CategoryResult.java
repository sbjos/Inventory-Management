package com.inventorymanagement.result;

import com.inventorymanagement.model.CategoryModel;

public class CategoryResult {
    private CategoryModel category;

    public CategoryResult (Builder builder) {
        this.category = builder.category;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private CategoryModel category;

        public Builder withCategory(CategoryModel category) {
            this.category = category;
            return this;
        }

        public CategoryResult build() {
            return new CategoryResult(this);
        }
    }
}
