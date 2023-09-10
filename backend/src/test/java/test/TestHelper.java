package test;

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
        item.setItemId("A125");
        item.setItemCategory(food().getCategoryName());
        item.setAvailability("Unavailable");
        item.setItemQuantity(0);
        item.setItemLocation(locationDF1().getLocationName());

        return item;
    }

    public static Item lettuce() {
        Item item = new Item();
        item.setItemName("Lettuce");
        item.setItemId("R212");
        item.setItemCategory(food().getCategoryName());
        item.setAvailability("Available");
        item.setItemQuantity(17);
        item.setItemLocation(locationR2().getLocationName());

        return item;
    }

    public static Item computer() {
        Item item = new Item();
        item.setItemName("Computer");
        item.setItemId("R212");
        item.setItemCategory(electronic().getCategoryName());
        item.setAvailability("Available");
        item.setItemQuantity(17);
        item.setItemLocation(locationE4().getLocationName());

        return item;
    }


    /**
     * Category TestHelper
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

    public static Category music() {
        Category category = new Category();
        category.setCategoryName("Music");

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
