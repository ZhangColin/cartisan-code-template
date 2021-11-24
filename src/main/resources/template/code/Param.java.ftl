package ${packageName}.${camelModule};

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* @author ${author}
*/
@Data
public class ${pascalModule}Param {
    <#list fields as field>
    @ApiModelProperty(value = "${field.description}")
    <#if field.require>@NotNull(message = "${field.description}不能为空")</#if>
    private ${field.type} ${field.camelName};

    </#list>
}
