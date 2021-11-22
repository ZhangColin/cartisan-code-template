//package com.cartisan.code;
//
//import com.cartisan.code.builder.BuilderFactory;
//import com.cartisan.code.utils.JavaTypes;
//import com.cartisan.code.utils.FieldInfo;
//import com.cartisan.code.utils.StringUtils;
//
//import java.io.InputStream;
//import java.sql.*;
//import java.util.*;
//
//import static java.util.Arrays.asList;
//
///**
// * @author colin
// */
//public class CodeApplicationOld {
//    /**
//     * 配置文件
//     */
//    private static Properties properties = new Properties();
//
//    static {
//        try {
//            // 加载配置文件
//            final InputStream inputStream = CodeApplicationOld.class.getClassLoader().getResourceAsStream("applicationold.properties");
//            properties.load(inputStream);
//
//            // 加载数据库驱动
//            Class.forName(properties.getProperty("driver"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        try {
//            final String username = properties.getProperty("username");
//            final String password = properties.getProperty("password");
//
//            final Properties prop = new Properties();
//            prop.setProperty("user", username);
//            prop.setProperty("password", password);
//            prop.setProperty("remarks", "true");
//            prop.setProperty("useInformationSchema", "true");
//
//            final Connection conn = DriverManager.getConnection(CodeApplicationOld.properties.getProperty("url"), prop);
//
//
//            final DatabaseMetaData metaData = conn.getMetaData();
//
//            final String databaseType = metaData.getDatabaseProductName();
//
//            if ("MySQL".equals(databaseType)) {
//                // 获取所有表结构
//                final String databaseName = conn.getCatalog();
//                final ResultSet tables = metaData.getTables(databaseName, "%", "%", new String[]{"TABLE"});
//
//                while (tables.next()){
//                    final String tableName = tables.getString("TABLE_NAME");
//                    if (tableName.startsWith("schema")){
//                        continue;
//                    }
//
//                    final List<String> configTables = asList(Optional.ofNullable(CodeApplicationOld.properties.getProperty("tables")).orElse(",").split("\\,"));
//                    if (configTables.size()>0 && !configTables.contains(tableName)){
//                        continue;
//                    }
//
//                    Map<String, Object> modelMap = new HashMap<>();
//                    modelMap.put("serviceName", CodeApplicationOld.properties.getProperty("serviceName"));
//                    modelMap.put("package", CodeApplicationOld.properties.getProperty("package"));
//
//                    modelMap.put("tableName", tableName);
//
//                    modelMap.put("module", StringUtils.convertCamelModule(tableName));
//                    modelMap.put("Module", StringUtils.convertPascalModule(tableName));
//                    modelMap.put("modules", StringUtils.convertCamelModules(tableName));
//                    modelMap.put("Modules", StringUtils.convertPascalModules(tableName));
//
//                    modelMap.put("moduleName", tables.getString("REMARKS"));
//
//                    List<FieldInfo> fields = new ArrayList<>();
//                    Set<String> importTypes = new HashSet<>();
//
//                    modelMap.put("fields", fields);
//                    modelMap.put("importTypes", importTypes);
//
//                    final ResultSet columns = metaData.getColumns(databaseName, username, tableName, null);
//
////                    final ResultSet primaryKeys = metaData.getPrimaryKeys(databaseName, username, tableName);
//
//                    while (columns.next()){
//                        final String remarks = columns.getString("REMARKS");
//                        final String columnName = columns.getString("COLUMN_NAME");
//                        final String javaType = JavaTypes.getType(columns.getInt("DATA_TYPE"));
//
//                        if (asList("created", "updated", "active", "deleted").contains(columnName)){
//                            continue;
//                        }
//
//                        final FieldInfo field = new FieldInfo();
//                        field.setType(javaType);
//                        field.setSimpleType(JavaTypes.simpleName(javaType));
//                        field.setName(StringUtils.convertCamel(columnName));
//                        field.setColumn(columnName);
//                        field.setUpperName(StringUtils.convertPascal(columnName));
//                        field.setDesc(remarks);
//                        final boolean isKey = "id".equals(columnName);
//                        field.setId(isKey);
//                        final boolean isAutoincrement = "YES".equals(columns.getString("IS_AUTOINCREMENT"));
//                        field.setIdentity(isAutoincrement);
//
//                        if (isKey){
//                            modelMap.put("isAutoincrement", isAutoincrement);
//                        }
//
//                        fields.add(field);
//
//                        importTypes.add(javaType);
//                    }
//
//                    BuilderFactory.builder(modelMap, "/template/code", "Controller.java",
//                            ("/"+modelMap.get("package")+"/"+ modelMap.get("module")).replace(".", "/").toLowerCase(),
//                            "Controller.java");
//
//                    BuilderFactory.builder(modelMap, "/template/code", "AppService.java",
//                            ("/"+modelMap.get("package")+"/"+ modelMap.get("module")).replace(".", "/").toLowerCase(),
//                            "AppService.java");
//
//                    BuilderFactory.builder(modelMap, "/template/code", "Converter.java",
//                            ("/"+modelMap.get("package")+"/"+ modelMap.get("module")).replace(".", "/").toLowerCase(),
//                            "Converter.java");
//
//                    BuilderFactory.builder(modelMap, "/template/code", "DomainModel.java",
//                            ("/"+modelMap.get("package")+"/"+ modelMap.get("module")).replace(".", "/").toLowerCase(),
//                            ".java");
//
//                    BuilderFactory.builder(modelMap, "/template/code", "Dto.java",
//                            ("/"+modelMap.get("package")+"/"+ modelMap.get("module")).replace(".", "/").toLowerCase(),
//                            "Dto.java");
//
//                    BuilderFactory.builder(modelMap, "/template/code", "Param.java",
//                            ("/"+modelMap.get("package")+"/"+ modelMap.get("module")).replace(".", "/").toLowerCase(),
//                            "Param.java");
//
//                    BuilderFactory.builder(modelMap, "/template/code", "Query.java",
//                            ("/"+modelMap.get("package")+"/"+ modelMap.get("module")).replace(".", "/").toLowerCase(),
//                            "Query.java");
//
//                    BuilderFactory.builder(modelMap, "/template/code", "Repository.java",
//                            ("/"+modelMap.get("package")+"/"+ modelMap.get("module")).replace(".", "/").toLowerCase(),
//                            "Repository.java");
//
//                    BuilderFactory.builder(modelMap, "/template/api", "Api.http",
//                            ("/api/"+modelMap.get("package")+"/"+ modelMap.get("module")).replace(".", "/").toLowerCase(),
//                            ".Http");
//
//                    BuilderFactory.builder(modelMap, "/template/vue", "UI.vue",
//                            ("/vue/"+ modelMap.get("package")+"/"+ modelMap.get("module")).replace(".", "/").toLowerCase(),
//                            ".vue");
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
