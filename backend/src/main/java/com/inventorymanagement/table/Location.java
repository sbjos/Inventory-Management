package com.inventorymanagement.table;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.Objects;

@DynamoDBTable(tableName = "IM-Location")
public class Location {

    private String locationName;

    public Location() {}

    @DynamoDBHashKey(attributeName = "LocationName")
    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String categoryName) {
        this.locationName = categoryName;
    }

    @Override
    public String toString() {
        return "Location{" +
                "Location='" + locationName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Location that = (Location) o;
        return Objects.equals(locationName, that.locationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationName);
    }
}
