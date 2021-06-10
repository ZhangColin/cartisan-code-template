package ${package}.${module};

import com.cartisan.domains.AbstractEntity;
import com.cartisan.domains.AggregateRoot;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

<#list importTypes as type>
import ${type};
</#list>

import static java.util.stream.Collectors.toList;

@Entity
@Table(name = "${tableName}")
@Getter
@EqualsAndHashCode(callSuper = true)
public class ${Module} extends AbstractEntity implements AggregateRoot {
    <#list fields as field>
    <#if field.id>
    @Id<#if field.identity>
    @GeneratedValue(strategy = GenerationType.IDENTITY)</#if>
    </#if>
    @Column(name = "${field.column}")
    private ${field.simpleType} ${field.name};

    </#list>
    private ${Module}() {}

    public ${Module}(<#list fields as field><#if field.id><#if !field.identity>${field.simpleType} ${field.name}<#if field_has_next>, </#if></#if><#else>${field.simpleType} ${field.name}<#if field_has_next>, </#if></#if></#list>) {
        <#list fields as field>
        <#if field.id><#if !field.identity>this.${field.name} = ${field.name};</#if><#else>this.${field.name} = ${field.name};</#if>
        </#list>
    }

    public void describe(<#list fields as field><#if !field.id>${field.simpleType} ${field.name}<#if field_has_next>, </#if></#if></#list>) {
        <#list fields as field><#if !field.id>
        this.${field.name} = ${field.name};
        </#if>
        </#list>
    }
}
