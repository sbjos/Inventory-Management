package com.inventorymanagement.model;

import java.util.Objects;

public class LocationModel {
    private String location;

    public LocationModel(Builder builder) {
        this.location = builder.location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "CategoryModel{" +
                "location='" + location + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ||this.getClass() != o.getClass()) return false;
        LocationModel that = (LocationModel) o;
        return Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String location;

        public Builder withLocation(String locationToUse) {
            this.location = locationToUse;
            return this;
        }

        public LocationModel build() {
            return new LocationModel(this);
        }
    }
}
