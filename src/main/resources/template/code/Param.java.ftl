package ${packageName}.${camelModule};

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* @author ${author}
*/
@Data
public class ${pascalModule}Param {
    <#list fields as field>
    @ApiModelProperty(value = "${field.description}")<#if field.require>
    @NotNull(message = "${field.description}不能为空")</#if><#if field.maxValid>
    @Length(max=${field.maxLength}, message = "${field.description}最大长度不能超过${field.maxLength}")</#if>
    private ${field.type} ${field.camelName}<#if field.defaultValue!=""> = <#if field.isNumber || field.isBoolean>${field.defaultValue}<#else>"${field.defaultValue}"</#if></#if>;

    </#list>
}
