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

import static java.util.stream.Collectors.toList;

/**
 * @author ${author}
 */
@Entity
@Table(name = "${tableName}")
@Getter
public class ${pascalModule} extends AbstractEntity implements AggregateRoot {
    @Id
    <#if primaryKey.autoIncrement>@GeneratedValue(strategy = GenerationType.IDENTITY)</#if>
    @Column(name = "id")
    private Long id;

    <#list fields as field>
    @Column(name = "${field.columnName}")
    private ${field.type} ${field.camelName};

    </#list>
    protected ${pascalModule}() {}

    public ${pascalModule}(<#if !primaryKey.autoIncrement>Long id,</#if> <#list fields as field>${field.type} ${field.camelName}<#if field_has_next>, </#if></#list>) {
        <#if !primaryKey.autoIncrement>this.id = id;</#if>
        <#list fields as field>
        this.${field.camelName} = ${field.camelName};
        </#list>
    }

    public void describe(<#list fields as field>${field.type} ${field.camelName}<#if field_has_next>, </#if></#list>) {
        <#list fields as field>
        this.${field.camelName} = ${field.camelName};
        </#list>
    }
}
