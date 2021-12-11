package ${packageName}.${camelModule};

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ${author}
 */
@Data
public class ${pascalModule}Dto {
    @ApiModelProperty(value = "${primaryKey.title}")
    private Long id;

    <#list fields as field>
    @ApiModelProperty(value = "${field.title}")
    private ${field.type} ${field.camelName};

    </#list>
}
