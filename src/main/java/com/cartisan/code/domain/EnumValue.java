package com.cartisan.code.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnumValue {
    private String code;
    private String description;
}
