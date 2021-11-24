package com.cartisan.code.domain;

import com.cartisan.code.utils.JavaTypes;
import com.cartisan.code.utils.StringUtils;
import lombok.Data;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author colin
 */
@Data
public class FieldData {
    private String columnName;

    private String camelName;
    private String pascalName;
    private String description;
    private String type;

    private Boolean primaryKey;
    private Boolean autoIncrement;

    private Boolean require;
    private String maxLength;
    private String defaultValue;

    private Boolean isNumber;
    private Boolean isBoolean;

    public FieldData(ColumnEntity columnEntity) {
        this.columnName = columnEntity.getColumnName();

        this.camelName = StringUtils.convertCamel(this.columnName);
        this.pascalName = StringUtils.convertPascal(this.columnName);
        this.description = columnEntity.getColumnComment();
        this.type = JavaTypes.getType(columnEntity.getDataType());

        this.primaryKey = columnEntity.getColumnKey().equals("PRI");
        this.autoIncrement = columnEntity.getExtra().equals("auto_increment");

        this.require = columnEntity.getIsNullable().equals("NO");
        this.maxLength = columnEntity.getMaxLength();

        this.isNumber = asList("Integer", "Float", "Double", "BigDecimal").contains(this.type);
        this.isBoolean = this.type.equals("Boolean");

        this.defaultValue = getDefaultValue(columnEntity.getColumnDefault());
    }

    private String getDefaultValue(String columnDefault) {
        if (this.isNumber) {
            return columnDefault != null?columnDefault:"0";
        }

        if (this.isBoolean) {
            if (columnDefault == null) {
                return "false";
            }
            if (columnDefault.equals("b'1'")) {
                return "true";
            }
            return "false";
        }

        return "";
    }
}
