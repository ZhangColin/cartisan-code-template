package ${packageName}.${camelModule};

import com.cartisan.constant.CodeMessage;
import com.cartisan.dto.PageResult;
import com.cartisan.exception.CartisanException;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

<#if !primaryKey.autoIncrement>
import com.cartisan.util.SnowflakeIdWorker;
</#if>

import javax.transaction.Transactional;
import java.util.List;

import static com.cartisan.repository.ConditionSpecifications.querySpecification;
import static com.cartisan.util.AssertionUtil.requirePresent;
import static java.util.stream.Collectors.toList;

/**
* @author ${author}
*/
@Service
@Slf4j
public class ${pascalModule}AppService {
    private final ${pascalModule}Repository repository;
    <#if !primaryKey.autoIncrement>
    private final SnowflakeIdWorker idWorker;
    </#if>

    private final ${pascalModule}Converter converter = ${pascalModule}Converter.CONVERTER;

    public ${pascalModule}AppService(${pascalModule}Repository repository<#if !primaryKey.autoIncrement>, SnowflakeIdWorker idWorker</#if>) {
        this.repository = repository;
        <#if !primaryKey.autoIncrement>
        this.idWorker = idWorker;
        </#if>
    }

    public PageResult<${pascalModule}Dto> search${camelModules}(@NonNull ${pascalModule}Query ${camelModule}Query, @NonNull Pageable pageable) {
        final Page<${pascalModule}> searchResult = repository.findAll(querySpecification(${camelModule}Query),
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));

        return new PageResult<>(searchResult.getTotalElements(), searchResult.getTotalPages(),
                converter.convert(searchResult.getContent()));
    }

    public ${pascalModule}Dto get${pascalModule}(@NonNull Long id) {
        return converter.convert(requirePresent(repository.findById(id)));
    }

    @Transactional(rollbackOn = Exception.class)
    public ${pascalModule}Dto add${pascalModule}(@NonNull ${pascalModule}Param ${camelModule}Param) {
        final ${pascalModule} ${camelModule} = new ${pascalModule}(<#if !primaryKey.autoIncrement>
            idWorker.nextId(),</#if><#list fields as field>
            ${camelModule}Param.get${field.pascalName}()<#if field_has_next>, </#if></#list>);

        return converter.convert(repository.save(${camelModule}));
    }

    @Transactional(rollbackOn = Exception.class)
    public ${pascalModule}Dto edit${pascalModule}(@NonNull Long id, @NonNull ${pascalModule}Param ${camelModule}Param) {
        final ${pascalModule} ${camelModule} = requirePresent(repository.findById(id));

        ${camelModule}.describe(<#list fields as field>
            ${camelModule}Param.get${field.pascalName}()<#if field_has_next>, </#if></#list>);

        return converter.convert(repository.save(${camelModule}));
    }

    @Transactional(rollbackOn = Exception.class)
    public void remove${pascalModule}(@NonNull Long id) {
        repository.deleteById(id);
    }
}
