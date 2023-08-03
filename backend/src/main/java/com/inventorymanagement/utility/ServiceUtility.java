package com.inventorymanagement.utility;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.regex.Pattern;

public class ServiceUtility {
    private static final Pattern INVALID_CHARACTER = Pattern.compile("[\"\'\\\\]");

    private static final int ID_LENGTH = 5;

    public static boolean isValid(final String itemName) {
        if (itemName.isBlank() || itemName.isEmpty()) {
            return true;
        }

        return INVALID_CHARACTER.matcher(itemName).find();
    }

    public static String generateId() {
        return RandomStringUtils.randomAlphanumeric(ID_LENGTH);
    }
}
