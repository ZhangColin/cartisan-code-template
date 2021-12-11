package ${packageName}.${camelModule};

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
* @author ${author}
*/
@Data
public class ${pascalModule}Param {
    <#list fields as field>
    @ApiModelProperty(value = "${field.title}"<#if field.require>, required = true</#if>)<#if field.require>
    <#if field.isNumber || field.isBoolean || field.isDate || field.isDateTime>@NotNull<#else>@NotBlank</#if>(message = "${field.title}不能为空")</#if><#if field.maxValid>
    @Length(max = ${field.maxLength}, message = "${field.description}最大长度不能超过${field.maxLength}")</#if>
    private ${field.type} ${field.camelName}<#if field.defaultValue!=""> = <#if field.isLong>${field.defaultValue}L<#elseif field.isNumber || field.isBoolean>${field.defaultValue}<#else>"${field.defaultValue}"</#if></#if>;

    </#list>
}
