package com.inventorymanagement.utility;

import org.apache.commons.lang3.RandomStringUtils;

public class ServiceUtility {

    private static final int ID_LENGTH = 5;

    public static boolean isEmpty(String name) {
        return name.isBlank() || name.isEmpty();
    }

    /**
     * Capitalizes the first letter of each word
     */
    public static String capitalizeFirstChar(String name) {
        char[] chars = name.toLowerCase().toCharArray();
        boolean found = false;

        for (int i = 0; i < chars.length; i++) {
            if (Character.isLetter(chars[i])) {
                if (!found) {
                    chars[i] = Character.toUpperCase(chars[i]);
                }
                found = true;
            } else {
                found = false;
            }
        }

        return String.valueOf(chars);
    }

    public static String generateId() {
        return RandomStringUtils.randomAlphanumeric(ID_LENGTH);
    }
}
