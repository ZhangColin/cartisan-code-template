package com.cartisan.code.domain;

import com.cartisan.code.utils.JavaTypes;
import com.cartisan.code.utils.StringUtils;
import lombok.Data;

/**
 * @author colin
 */
@Data
public class FieldData {
//    //属性类型
//    private String type;
//    //类型-只有名字
//    private String simpleType;

    private String columnName;

    private String camelName;
    private String pascalName;
    private String description;
    private String type;

    private Boolean primaryKey;
    private Boolean autoIncrement;



    public FieldData(ColumnEntity columnEntity) {
        this.columnName = columnEntity.getColumnName();

        this.camelName = StringUtils.convertCamel(this.columnName);
        this.pascalName = StringUtils.convertPascal(this.columnName);
        this.description = columnEntity.getColumnComment();
        this.type = JavaTypes.getType(columnEntity.getDataType());

        this.primaryKey = columnEntity.getColumnKey().equals("PRI");
        this.autoIncrement = columnEntity.getExtra().equals("auto_increment");
    }
}
