package ${packageName}.${camelModule};

import com.cartisan.constant.CodeMessage;
import com.cartisan.dto.PageResult;
import com.cartisan.exception.CartisanException;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

<#if !isAutoincrement>
import com.cartisan.util.SnowflakeIdWorker;
</#if>

import javax.transaction.Transactional;
import java.util.List;

import static com.cartisan.repository.ConditionSpecifications.querySpecification;
import static com.cartisan.util.AssertionUtil.requirePresent;
import static java.util.stream.Collectors.toList;

@Service
public class ${pascalModule}AppService {
    private final ${pascalModule}Repository repository;
    <#if !isAutoincrement>
    private final SnowflakeIdWorker idWorker;
    </#if>

    private final ${pascalModule}Converter converter = ${pascalModule}Converter.CONVERTER;

    public ${pascalModule}AppService(${pascalModule}Repository repository<#if !isAutoincrement>, SnowflakeIdWorker idWorker</#if>) {
        this.repository = repository;
        <#if !isAutoincrement>
        this.idWorker = idWorker;
        </#if>
    }

    public PageResult<${pascalModule}Dto> search${Modules}(@NonNull ${pascalModule}Query ${camelModule}Query, @NonNull Pageable pageable) {
        final Page<${pascalModule}> searchResult = repository.findAll(querySpecification(${camelModule}Query),
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));

        return new PageResult<>(searchResult.getTotalElements(), searchResult.getTotalPages(),
                converter.convert(searchResult.getContent()));
    }

    public ${pascalModule}Dto get${pascalModule}(Long id) {
        return converter.convert(requirePresent(repository.findById(id)));
    }

    @Transactional(rollbackOn = Exception.class)
    public ${pascalModule}Dto add${pascalModule}(${pascalModule}Param ${camelModule}Param) {
        final ${pascalModule} ${camelModule} = new ${pascalModule}(<#list fields as field><#if field.id><#if !field.identity>idWorker.nextId()<#if field_has_next>,
        </#if></#if><#else>${camelModule}Param.get${field.upperName}()<#if field_has_next>,
        </#if></#if></#list>);

        return converter.convert(repository.save(${camelModule}));
    }

    @Transactional(rollbackOn = Exception.class)
    public ${pascalModule}Dto edit${pascalModule}(Long id, ${pascalModule}Param ${camelModule}Param) {
        final ${pascalModule} ${camelModule} = requirePresent(repository.findById(id));

        ${camelModule}.describe(<#list fields as field><#if !field.id>${camelModule}Param.get${field.upperName}()<#if field_has_next>,
        </#if></#if></#list>);

        return converter.convert(repository.save(${camelModule}));
    }

    @Transactional(rollbackOn = Exception.class)
    public void remove${pascalModule}(long id) {
        repository.deleteById(id);
    }
}
