package com.cartisan.code.domain;

import lombok.Data;

@Data
public class ColumnEntity {
    private String columnName;
    private String columnDefault;
    private String isNullable;
    private String dataType;
    private String maxLength;
    private String columnComment;
    private String columnKey;
    private String extra;
}
