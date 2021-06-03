package com.cartisan.code;

import com.cartisan.code.utils.JavaTypes;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author colin
 */
public class CodeApplication {
    /**
     * 配置文件
     */
    private static Properties properties = new Properties();

    static {
        try {
            // 加载配置文件
            final InputStream inputStream = CodeApplication.class.getClassLoader().getResourceAsStream("application.properties");
            properties.load(inputStream);

            // 加载数据库驱动
            Class.forName(properties.getProperty("driver"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            final String username = properties.getProperty("username");
            final String password = properties.getProperty("password");
            final Connection conn = DriverManager.getConnection(properties.getProperty("url"),
                    username,
                    password);

            final DatabaseMetaData metaData = conn.getMetaData();

            final String databaseType = metaData.getDatabaseProductName();

            if ("MySQL".equals(databaseType)) {
                // 获取所有表结构
                final ResultSet tables = metaData.getTables(null, "%", "%", new String[]{"TABLE"});

                final String databaseName = conn.getCatalog();

                while (tables.next()){
                    final String tableName = tables.getString("TABLE_NAME");

                    final ResultSet columns = metaData.getColumns(databaseName, username, tableName, null);

                    final ResultSet primaryKeys = metaData.getPrimaryKeys(databaseName, username, tableName);

                    while (columns.next()){
                        final String remarks = columns.getString("REMARKS");
                        final String columnName = columns.getString("COLUMN_NAME");
                        final String javaType = JavaTypes.getType(columns.getInt("DATA_TYPE"));

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
