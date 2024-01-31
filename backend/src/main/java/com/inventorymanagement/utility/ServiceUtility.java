package com.inventorymanagement.utility;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

public class ServiceUtility {

    private static final int ID_LENGTH = 5;

    public static boolean isEmpty(String name) {
        return name.isBlank() || name.isEmpty();
    }

    /**
     * Capitalizes the first letter of each word
     */
    public static String capitalizeFirstChar(String name) {
        if (name == null) return null;

        char[] chars = name.toLowerCase().toCharArray();
        boolean found = true;

        for (int i = 0; i < chars.length; i++) {
            if (Character.isLetter(chars[i])) {
                if (found) {
                    chars[i] = Character.toUpperCase(chars[i]);
                }
                found = false;
            } else {
                found = true;
            }
        }

        return String.valueOf(chars)
                .replace("%20", " ").trim();
    }

    /**
     * Capitalizes all the letters in a location
     */
    public static String toUpperCase(String name) {
        if (name == null) return null;

        return name.toUpperCase()
                .replace("%20", " ")
                .trim();
    }

    public static String generateId() {
        return RandomStringUtils.randomAlphanumeric(ID_LENGTH);
    }
}
