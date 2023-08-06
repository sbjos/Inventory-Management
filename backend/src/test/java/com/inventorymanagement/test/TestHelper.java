package com.inventorymanagement.test;

import com.inventorymanagement.table.Category;
import com.inventorymanagement.table.Item;
import com.inventorymanagement.table.Location;

public final class TestHelper {

    /**
     * Item TestHelper
     */
    public static Item ramenNoodle() {
        Item item = new Item();
        item.setItemName("Ramen Noodle");
        item.setId("A125");
        item.setCategory("Food");
        item.setAvailable("Unavailable");
        item.setQuantity(0);
        item.setLocation(locationDF1().getLocationName());

        return item;
    }

    public static Item lettuce() {
        Item item = new Item();
        item.setItemName("Lettuce");
        item.setId("R212");
        item.setCategory("Food");
        item.setAvailable("Available");
        item.setQuantity(17);
        item.setLocation(locationR2().getLocationName());

        return item;
    }

    public static Item computer() {
        Item item = new Item();
        item.setItemName("Computer");
        item.setId("R212");
        item.setCategory("Electronic");
        item.setAvailable("Available");
        item.setQuantity(17);
        item.setLocation(locationE4().getLocationName());

        return item;
    }


    /**
     * Category TestHelper
     */
    public static Category food() {
        Category category = new Category();
        category.setCategory("Food");

        return category;
    }

    public static Category electronic() {
        Category category = new Category();
        category.setCategory("Electronic");

        return category;
    }

    public static Category music() {
        Category category = new Category();
        category.setCategory("Music");

        return category;
    }

    /**
     * Location TestHelper
     */

    public static Location locationDF1() {
        Location location = new Location();
        location.setLocationName("DF1");

        return location;
    }

    public static Location locationR2() {
        Location location = new Location();
        location.setLocationName("R2");

        return location;
    }

    public static Location locationE4() {
        Location location = new Location();
        location.setLocationName("E4");

        return location;
    }
}
