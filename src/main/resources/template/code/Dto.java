package ${packageName}.${camelModule};

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

//<#list importTypes as type>
//import ${type};
//</#list>

@Data
public class ${pascalModule}Dto {
    <#list fields as field>
    @ApiModelProperty(value = "${field.description}")
    private ${field.type} ${field.camelName};

    </#list>
}
