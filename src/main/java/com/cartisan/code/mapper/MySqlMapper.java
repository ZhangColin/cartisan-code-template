package com.cartisan.code.mapper;

import com.cartisan.code.domain.ColumnEntity;
import com.cartisan.code.domain.TableEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MySqlMapper {
    @Select("select table_name tableName " +
            "from information_schema.tables " +
            "where table_schema = (select database())")
    List<String> getAllTableNames();

    @Select("select table_name, table_comment " +
            "from information_schema.tables " +
            "where table_schema = (select database()) and table_name = #{tableName}")
    List<TableEntity> getTable(String tableName);

    @Select("select column_name, data_type, column_comment, column_key, extra " +
            "from information_schema.columns " +
            "where table_name = #{tableName} and table_schema = (select database()) " +
            "order by ordinal_position")
    List<ColumnEntity> getColumns(String tableName);
}
