package ${packageName}.${camelModule};

import com.cartisan.domain.AbstractEntity;
import com.cartisan.domain.AggregateRoot;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//<#list importTypes as type>
//import ${type};
//</#list>

import static java.util.stream.Collectors.toList;

@Entity
@Table(name = "${tableName}")
@Getter
public class ${pascalModule} extends AbstractEntity implements AggregateRoot {
    <#list fields as field>
    <#if field.primaryKey>
    @Id<#if field.autoIncrement>
    @GeneratedValue(strategy = GenerationType.IDENTITY)</#if>
    </#if>
    @Column(name = "${field.columnName}")
    private ${field.type} ${field.camelName};

    </#list>
    protected ${pascalModule}() {}

    public ${pascalModule}(<#list fields as field><#if field.id><#if !field.identity>${field.simpleType} ${field.camelName}<#if field_has_next>, </#if></#if><#else>${field.simpleType} ${field.camelName}<#if field_has_next>, </#if></#if></#list>) {
        <#list fields as field>
        <#if field.id><#if !field.identity>this.${field.camelName} = ${field.camelName};</#if><#else>this.${field.camelName} = ${field.camelName};</#if>
        </#list>
    }

    public void describe(<#list fields as field><#if !field.id>${field.simpleType} ${field.camelName}<#if field_has_next>, </#if></#if></#list>) {
        <#list fields as field><#if !field.id>
        this.${field.camelName} = ${field.camelName};
        </#if>
        </#list>
    }
}
