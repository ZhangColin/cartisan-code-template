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

    @Select("select table_name tableName, table_comment tableComment " +
            "from information_schema.tables " +
            "where table_schema = (select database()) and table_name = #{tableName}")
    TableEntity getTable(String tableName);

    @Select("select column_name columnName, COLUMN_DEFAULT columnDefault, IS_NULLABLE isNullable, data_type dataType, " +
            "    CHARACTER_MAXIMUM_LENGTH maxLength,column_comment columnComment, column_key columnKey, extra " +
            "from information_schema.columns " +
            "where table_name = #{tableName} and table_schema = (select database()) " +
            "order by ordinal_position")
    List<ColumnEntity> getColumns(String tableName);
}
