package com.inventorymanagement.model;

import com.inventorymanagement.table.Location;

import java.util.List;
import java.util.Objects;

public class LocationListModel {
    private List<Location> locationList;

    public LocationListModel(Builder builder) {
        this.locationList = builder.LocationList;
    }

    public List<Location> getSectionList() {
        return locationList;
    }

    public void setSectionList(List<Location> withLocationList) {
        this.locationList = withLocationList;
    }

    @Override
    public String toString() {
        return "LocationListModel{" +
                "LocationList='" + locationList + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ||this.getClass() != o.getClass()) return false;
        LocationListModel that = (LocationListModel) o;
        return Objects.equals(locationList, that.locationList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationList);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private List<Location> LocationList;

        public Builder withLocationList(List<Location> locationListToUse) {
            this.LocationList = locationListToUse;
            return this;
        }

        public LocationListModel build() {
            return new LocationListModel(this);
        }
    }
}
