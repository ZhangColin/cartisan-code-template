package com.cartisan.code.utils;

import java.util.Arrays;
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

    public static String convertCamelModule(String tableName){
        final String str = replacePrefix(tableName);
        if (!str.contains("_")){
            return Inflector.singularize(str);
        }

        final String[] split = str.split("_");

        return firstLower(split[0])+
                Arrays.stream(split).skip(1).limit(split.length-2).map(StringUtils::firstUpper).collect(Collectors.joining())+
                firstUpper(Inflector.singularize(split[split.length-1]));
    }

    public static String convertPascalModule(String tableName){
        final String str = replacePrefix(tableName);
        if (!str.contains("_")){
            return firstUpper(Inflector.singularize(str));
        }

        final String[] split = str.split("_");

        return firstUpper(split[0])+
                Arrays.stream(split).skip(1).limit(split.length-2).map(StringUtils::firstUpper).collect(Collectors.joining())+
                        firstUpper(Inflector.singularize(split[split.length-1]));
    }

    public static String convertCamelModules(String tableName){
        return convertCamel(replacePrefix(tableName));
    }

    public static String convertPascalModules(String tableName){
        return convertPascal(replacePrefix(tableName));
    }

}
