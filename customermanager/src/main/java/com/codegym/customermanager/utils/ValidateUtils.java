package com.codegym.customermanager.utils;

import java.util.regex.Pattern;

public class ValidateUtils {
    public static final String ADDRESS_REGEX = "^[A-Za-z0-9][A-Za-z0-9\\s]{5,9}$";

    public static final String NAME_REGEX = "^[A-Za-z0-9][A-Za-z0-9\\s]{7,14}$";
    public static boolean isAddressValid(String address) {
        return Pattern.matches(ADDRESS_REGEX, address);
    }
    public static boolean isNameValid(String name) {
        return Pattern.matches(NAME_REGEX, name);
    }
}
