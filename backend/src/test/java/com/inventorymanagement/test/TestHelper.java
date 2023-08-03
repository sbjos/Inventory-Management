package com.inventorymanagement.test;

import com.inventorymanagement.table.Category;
import com.inventorymanagement.table.Item;

public final class TestHelper {

    /**
     * ItemDAO TestHelper
     */
    public static Item ramenNoodle() {
        Item item = new Item();
        item.setName("Ramen Noodle");
        item.setId("A125");
        item.setCategory("Food");
        item.setAvailable("False");
        item.setQuantity(0);
        item.setLocation("A12");

        return item;
    }

    public static Item lettuce() {
        Item item = new Item();
        item.setName("Lettuce");
        item.setId("R212");
        item.setCategory("Food");
        item.setAvailable("True");
        item.setQuantity(17);
        item.setLocation("R2");

        return item;
    }

    public static Item computer() {
        Item item = new Item();
        item.setName("Computer");
        item.setId("R212");
        item.setCategory("Electronic");
        item.setAvailable("True");
        item.setQuantity(17);
        item.setLocation("R2");

        return item;
    }


    /**
     * CategoryDAO TestHelper
     */
    public static Category food() {
        Category category = new Category();
        category.setCategoryName("Food");

        return category;
    }

    public static Category electronic() {
        Category category = new Category();
        category.setCategoryName("Electronic");

        return category;
    }
}
