package com.cartisan.code.utils;

import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author colin
 */
public class StringUtils {

    public static String firstUpper(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String firstLower(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    public static String replacePrefix(String str) {
        return str.substring(str.indexOf("_") + 1);
    }

    public static String convertCamel(String str) {
        if (str.contains("_")) {
            return str.substring(0, str.indexOf("_")) + convertPascal(str.substring(str.indexOf("_") + 1));
        }
        return str;
    }

    public static String convertPascal(String str) {
        return Stream.of(str.split("_")).map(StringUtils::firstUpper).collect(Collectors.joining());
    }
}
