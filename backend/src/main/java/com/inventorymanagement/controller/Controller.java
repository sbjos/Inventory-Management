package com.inventorymanagement.controller;

import java.util.Objects;

public class Controller {
    private String name;
    private String id;
    private String category;
    private String available;
    private Integer quantity;
    private String location;


    public Controller() {}

    public Controller(String name, String id, String category,
                      String available, int quantity, String location) {
        this.name = name;
        this.id = id;
        this.category = category;
        this.available = available;
        this.quantity = quantity;
        this.location = location;
    }

    public Controller(Builder builder) {
        this.name = builder.name;
        this.id = builder.id;
        this.category = builder.category;
        this.available = builder.available;
        this.quantity = builder.quantity;
        this.location = builder.location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String isAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "ItemController{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", available=" + available +
                ", quantity=" + quantity +
                ", location='" + location + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ||this.getClass() != o.getClass()) return false;
        Controller that = (Controller) o;
        return Objects.equals(name, that.name) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String name;
        private String id;
        private String category;
        private String available;
        private Integer quantity;
        private String location;

        public Builder withName(String nameToUse) {
            this.name = nameToUse;
            return this;
        }

        public Builder withId(String idToUse) {
            this.id = idToUse;
            return this;
        }

        public Builder withCategory(String categoryToUse) {
            this.category = categoryToUse;
            return this;
        }

        public Builder withAvailable(String availableToUse) {
            this.available = availableToUse;
            return this;
        }

        public Builder withQuantity(Integer quantityToUse) {
            this.quantity = quantityToUse;
            return this;
        }

        public Builder withLocation(String locationToUse) {
            this.location = locationToUse;
            return this;
        }

        public Controller build() {
            return new Controller(this);
        }
    }
}
