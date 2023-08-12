package com.inventorymanagement.controller;

import java.util.Objects;

public class Controller {
    private String name;
    private String id;
    private String category;
    private String availability;
    private Integer quantity;
    private String location;
    private boolean all;

    public Controller() {}


    public Controller(String name, String Id, String category,
                      String availability, int quantity, String location, boolean all) {
        this.name = name;
        this.id = Id;
        this.category = category;
        this.availability = availability;
        this.quantity = quantity;
        this.location = location;
        this.all = all;
    }

    public Controller(Builder builder) {
        this.name = builder.name;
        this.id = builder.id;
        this.category = builder.category;
        this.availability = builder.availability;
        this.quantity = builder.quantity;
        this.location = builder.location;
        this.all = builder.all;
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

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
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

    public boolean isAll() {
        return all;
    }

    public void setAll(boolean all) {
        this.all = all;
    }

    @Override
    public String toString() {
        return "Controller{" +
                "Name='" + name + '\'' +
                ", ID='" + id + '\'' +
                ", Category='" + category + '\'' +
                ", Available=" + availability +
                ", Quantity=" + quantity +
                ", Location='" + location + '\'' +
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
        private String availability;
        private Integer quantity;
        private String location;
        private boolean all;

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

        public Builder withAvailability(String availabilityToUse) {
            this.availability = availabilityToUse;
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

        public Builder withAll(boolean findAll) {
            this.all = findAll;
            return this;
        }

        public Controller build() {
            return new Controller(this);
        }
    }
}
