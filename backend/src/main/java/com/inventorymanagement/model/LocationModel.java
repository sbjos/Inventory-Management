package com.inventorymanagement.model;

import java.util.Objects;

public class LocationModel {
    private String locationName;

    public LocationModel(Builder builder) {
        this.locationName = builder.locationName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @Override
    public String toString() {
        return "LocationModel{" +
                "LocationName='" + locationName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ||this.getClass() != o.getClass()) return false;
        LocationModel that = (LocationModel) o;
        return Objects.equals(locationName, that.locationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationName);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String locationName;

        public Builder withLocationName(String locationNameToUse) {
            this.locationName = locationNameToUse;
            return this;
        }

        public LocationModel build() {
            return new LocationModel(this);
        }
    }
}
