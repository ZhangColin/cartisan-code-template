package com.cartisan.code.domain;

import com.cartisan.code.utils.StringUtils;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

@Getter
public class EntityData {
    private String serviceName;
    private String packageName;

    private String tableName;

    private String camelModule;
    private String pascalModule;
    private String camelModules;
    private String pascalModules;

    private String moduleName;

    private List<FieldData> fields;
    private FieldData primaryKey;

    private Set<String> importTypes = new HashSet<>();

    private String author = "colin";


    public EntityData(String serviceName, String packageName, TableEntity tableEntity, List<FieldData> fields) {
        this.serviceName = serviceName;
        this.packageName = packageName;

        this.tableName = tableEntity.getTableName();

        this.camelModule = StringUtils.convertCamelModule(this.tableName);
        this.pascalModule = StringUtils.convertPascalModule(this.tableName);
        this.camelModules = StringUtils.convertCamelModules(this.tableName);
        this.pascalModules = StringUtils.convertPascalModules(this.tableName);

        this.moduleName = tableEntity.getTableComment();

        this.primaryKey = fields.stream().filter(FieldData::getPrimaryKey).findFirst().get();
        this.fields = fields.stream()
                .filter(field->!field.getPrimaryKey() && !asList("created", "updated", "active", "deleted").contains(field.getColumnName()))
                .collect(toList());
    }
}
