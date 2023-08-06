package com.inventorymanagement.table;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.Objects;

@DynamoDBTable(tableName = "IM-Location")
public class Location {

    private String location;

    public Location() {}

    @DynamoDBHashKey(attributeName = "locationName")
    public String getLocationName() {
        return location;
    }

    public void setLocationName(String categoryName) {
        this.location = categoryName;
    }

    @Override
    public String toString() {
        return "Location{" +
                "location='" + location + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Location that = (Location) o;
        return Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
    }
}
