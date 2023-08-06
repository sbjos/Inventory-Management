package com.inventorymanagement.result;

import com.inventorymanagement.model.LocationModel;

public class LocationResult {
    private LocationModel location;

    public LocationResult(Builder builder) {
        this.location = builder.location;
    }

    public LocationModel getLocation() {
        return location;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private LocationModel location;

        public Builder withLocation(LocationModel locationToUse) {
            this.location = locationToUse;
            return this;
        }

        public LocationResult build() {
            return new LocationResult(this);
        }
    }
}
