package com.cartisan.code.domain;

import com.cartisan.code.utils.JavaTypes;
import com.cartisan.code.utils.StringUtils;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    private String title;
    private String type;

    private Boolean primaryKey;
    private Boolean autoIncrement;

    private Boolean require;
    private Long maxLength;
    private String defaultValue;
    private Boolean maxValid;
    private Boolean needValid;

    private Boolean isNumber;
    private Boolean isLong;
    private Boolean isBoolean;
    private Boolean isDate;
    private Boolean isDateTime;

    private Boolean isEnum = false;
    private List<EnumValue> enumValues;


    public FieldData(ColumnEntity columnEntity) {
        this.columnName = columnEntity.getColumnName();

        this.camelName = StringUtils.convertCamel(this.columnName);
        this.pascalName = StringUtils.convertPascal(this.columnName);
        this.description = columnEntity.getColumnComment();
        this.title = this.description;
        this.type = JavaTypes.getType(columnEntity.getDataType());

        this.primaryKey = columnEntity.getColumnKey().equals("PRI");
        this.autoIncrement = columnEntity.getExtra().equals("auto_increment");

        this.require = columnEntity.getIsNullable().equals("NO");
        this.maxLength = org.springframework.util.StringUtils.hasLength(columnEntity.getMaxLength()) ? Long.parseLong(columnEntity.getMaxLength()) : 0L;
        this.maxValid = this.maxLength > 0 && this.maxLength <= 256;
        this.needValid = this.require || this.maxValid;

        this.isNumber = asList("Integer", "Float", "Long", "Double", "BigDecimal").contains(this.type);
        this.isLong = "Long".equals(this.type);
        this.isBoolean = this.type.equals("Boolean");
        this.isDate = this.type.equals("LocalDate");
        this.isDateTime = this.type.equals("LocalDateTime");

        this.defaultValue = getDefaultValue(columnEntity.getColumnDefault());

        if (this.isNumber && this.title.contains("[")) {
            this.isEnum = true;

            String enumValue = this.title.substring(this.title.indexOf("[") + 1, this.title.indexOf("]"));
            this.title = this.title.substring(0, this.title.indexOf("["));

            this.enumValues = Arrays.stream(enumValue.split(","))
                    .map(v -> new EnumValue(v.split(":")[0], v.split(":")[1]))
                    .collect(Collectors.toList());
        }
    }

    private String getDefaultValue(String columnDefault) {
        if (this.isNumber) {
            return columnDefault != null ? columnDefault : "0";
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
