package ${package}.${module};

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

<#list importTypes as type>
import ${type};
</#list>

@Data
public class ${Module}Dto {
    <#list fields as field>
    @ApiModelProperty(value = "${field.desc}")
    private ${field.simpleType} ${field.name};

    </#list>
}
