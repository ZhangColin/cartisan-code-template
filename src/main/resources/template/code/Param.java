package ${package}.${module};

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

<#list importTypes as type>
import ${type};
</#list>

@Data
public class ${Module}Param {
    <#list fields as field><#if !field.id>
    @ApiModelProperty(value = "${field.desc}")
    private ${field.simpleType} ${field.name};

    </#if>
    </#list>
}
