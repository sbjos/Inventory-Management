package com.inventorymanagement.model;

import com.inventorymanagement.table.Location;

import java.util.List;
import java.util.Objects;

public class LocationListModel {
    private List<Location> withLocationList;

    public LocationListModel(Builder builder) {
        this.withLocationList = builder.withLocationList;
    }

    public List<Location> getLocation() {
        return withLocationList;
    }

    public void setLocation(List<Location> withLocationList) {
        this.withLocationList = withLocationList;
    }

    @Override
    public String toString() {
        return "LocationModel{" +
                "withLocationList='" + withLocationList + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ||this.getClass() != o.getClass()) return false;
        LocationListModel that = (LocationListModel) o;
        return Objects.equals(withLocationList, that.withLocationList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(withLocationList);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private List<Location> withLocationList;

        public Builder withLocationList(List<Location> withLocationListToUse) {
            this.withLocationList = withLocationListToUse;
            return this;
        }

        public LocationListModel build() {
            return new LocationListModel(this);
        }
    }
}
