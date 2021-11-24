package ${packageName}.${camelModule};

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ${author}
 */
@Data
public class ${pascalModule}Dto {
    @ApiModelProperty(value = "${primaryKey.description}")
    private Long id;

    <#list fields as field>
    @ApiModelProperty(value = "${field.description}")
    private ${field.type} ${field.camelName};

    </#list>
}
