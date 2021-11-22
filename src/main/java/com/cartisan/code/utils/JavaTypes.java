package com.cartisan.code.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author colin
 */
public class JavaTypes {
    private static final Map<String, String> dbTypeJavaTypeMap = new HashMap<>();

    static {
        dbTypeJavaTypeMap.put("tinyint", "Integer");
        dbTypeJavaTypeMap.put("smallint", "Integer");
        dbTypeJavaTypeMap.put("mediumint", "Integer");
        dbTypeJavaTypeMap.put("int", "Integer");
        dbTypeJavaTypeMap.put("integer", "Integer");
        dbTypeJavaTypeMap.put("bigint", "Long");
        dbTypeJavaTypeMap.put("float", "Float");
        dbTypeJavaTypeMap.put("double", "Double");
        dbTypeJavaTypeMap.put("decimal", "BigDecimal");
        dbTypeJavaTypeMap.put("bit", "Boolean");
        dbTypeJavaTypeMap.put("char", "String");
        dbTypeJavaTypeMap.put("varchar", "String");
        dbTypeJavaTypeMap.put("tinytext", "String");
        dbTypeJavaTypeMap.put("text", "String");
        dbTypeJavaTypeMap.put("mediumtext", "String");
        dbTypeJavaTypeMap.put("longtext", "String");
        dbTypeJavaTypeMap.put("date", "LocalDate");
        dbTypeJavaTypeMap.put("datetime", "LocalDateTime");
        dbTypeJavaTypeMap.put("timestamp", "LocalDateTime");
        dbTypeJavaTypeMap.put("NUMBER", "Integer");
        dbTypeJavaTypeMap.put("INT", "Integer");
        dbTypeJavaTypeMap.put("INTEGER", "Integer");
        dbTypeJavaTypeMap.put("BINARY_INTEGER", "Integer");
        dbTypeJavaTypeMap.put("LONG", "String");
        dbTypeJavaTypeMap.put("FLOAT", "Float");
        dbTypeJavaTypeMap.put("BINARY_FLOAT", "Float");
        dbTypeJavaTypeMap.put("DOUBLE", "Double");
        dbTypeJavaTypeMap.put("BINARY_DOUBLE", "Double");
        dbTypeJavaTypeMap.put("DECIMAL", "BigDecimal");
        dbTypeJavaTypeMap.put("CHAR", "String");
        dbTypeJavaTypeMap.put("VARCHAR", "String");
        dbTypeJavaTypeMap.put("VARCHAR2", "String");
        dbTypeJavaTypeMap.put("NVARCHAR", "String");
        dbTypeJavaTypeMap.put("NVARCHAR2", "String");
        dbTypeJavaTypeMap.put("CLOB", "String");
        dbTypeJavaTypeMap.put("BLOB", "String");
        dbTypeJavaTypeMap.put("DATE", "LocalDate");
        dbTypeJavaTypeMap.put("DATETIME", "LocalDateTime");
        dbTypeJavaTypeMap.put("TIMESTAMP", "LocalDateTime");
        dbTypeJavaTypeMap.put("TIMESTAMP(6)", "LocalDate");
        dbTypeJavaTypeMap.put("int8", "Long");
        dbTypeJavaTypeMap.put("int4", "Integer");
        dbTypeJavaTypeMap.put("int2", "Integer");
        dbTypeJavaTypeMap.put("numeric", "BigDecimal");
        dbTypeJavaTypeMap.put("nvarchar", "String");
    }

    public static String getType(String dbType){
        return dbTypeJavaTypeMap.get(dbType);
    }

    public static String getType(int value){

        switch (value){
            case -6:
                return "java.lang.Integer";
            case 5:
                return "java.lang.Integer";
            case 4:
                return "java.lang.Integer";
            case -5:
                return "java.lang.Long";
            case 6:
                return "java.lang.Float";
            case -7:
                return "java.lang.Boolean";
            case 8:
                return "java.lang.Double";
            case 1:
                return "java.lang.String";
            case 12:
                return "java.lang.String";
            case -1:
                return "java.lang.String";
            case 91:
                return "java.util.Date";
            case 92:
                return "java.util.Date";
            case 93:
                return "java.util.Date";
            case 16:
                return "java.lang.Boolean";
            default:
                return "java.lang.String";
        }
    }

    /***
     * 去掉数据类型的包
     * @param type
     * @return
     */
    public static String simpleName(String type){
        return type.replace("java.lang.","").replaceFirst("java.util.","");
    }

    /***
     * 去掉数据类型的包，并且首字母小写
     * @param type
     * @return
     */
    public static  String simpleNameLowerFirst(String type){
        //去掉前缀
        type = simpleName(type);
        //将第一个字母转成小写
        return StringUtils.firstLower(type);
    }
}
