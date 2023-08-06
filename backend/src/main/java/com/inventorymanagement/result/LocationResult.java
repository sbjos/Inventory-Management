package com.inventorymanagement.result;

import com.inventorymanagement.model.LocationListModel;
import com.inventorymanagement.model.LocationModel;

public class LocationResult {
    private LocationModel location;
    private LocationListModel locationList;

    public LocationResult(Builder builder) {
        this.location = builder.location;
        this.locationList = builder.locationList;
    }

    public LocationModel getLocation() {
        return location;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }

    public LocationListModel getLocationList() {
        return locationList;
    }

    public void setLocationList(LocationListModel locationList) {
        this.locationList = locationList;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private LocationModel location;
        private LocationListModel locationList;

        public Builder withLocation(LocationModel locationToUse) {
            this.location = locationToUse;
            return this;
        }

        public Builder withLocationList(LocationListModel locationListToUse) {
            this.locationList = locationListToUse;
            return this;
        }

        public LocationResult build() {
            return new LocationResult(this);
        }
    }
}
