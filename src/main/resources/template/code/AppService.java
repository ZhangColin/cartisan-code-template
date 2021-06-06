package ${package}.${module};

import com.cartisan.constants.CodeMessage;
import com.cartisan.dtos.PageResult;
import com.cartisan.exceptions.CartisanException;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

<#if !isAutoincrement>
import com.cartisan.utils.SnowflakeIdWorker;
</#if>

import javax.transaction.Transactional;
import java.util.List;

import static com.cartisan.repositories.ConditionSpecifications.querySpecification;
import static com.cartisan.utils.AssertionUtil.requirePresent;
import static java.util.stream.Collectors.toList;

@Service
public class ${Module}AppService {
    private final ${Module}Repository repository;
    <#if !isAutoincrement>
    private final SnowflakeIdWorker idWorker;
    </#if>

    private final ${Module}Converter converter = ${Module}Converter.CONVERTER;

    public ${Module}AppService(${Module}Repository repository<#if !isAutoincrement>, SnowflakeIdWorker idWorker</#if>) {
        this.repository = repository;
        <#if !isAutoincrement>
        this.idWorker = idWorker;
        </#if>
    }

    public PageResult<${Module}Dto> search${Modules}(@NonNull ${Module}Query ${module}Query, @NonNull Pageable pageable) {
        final Page<${Module}> searchResult = repository.findAll(querySpecification(${module}Query),
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));

        return new PageResult<>(searchResult.getTotalElements(), searchResult.getTotalPages(),
                converter.convert(searchResult.getContent()));
    }

    public ${Module}Dto get${Module}(Long id) {
        return converter.convert(requirePresent(repository.findById(id)));
    }

    @Transactional(rollbackOn = Exception.class)
    public ${Module}Dto add${Module}(${Module}Param ${module}Param) {
        final ${Module} ${module} = new ${Module}(<#list fields as field><#if field.id><#if !field.identity>idWorker.nextId()<#if field_has_next>,
        </#if></#if><#else>${module}Param.get${field.upperName}()<#if field_has_next>,
        </#if></#if></#list>);

        return converter.convert(repository.save(${module}));
    }

    @Transactional(rollbackOn = Exception.class)
    public ${Module}Dto edit${Module}(Long id, ${Module}Param ${module}Param) {
        final ${Module} ${module} = requirePresent(repository.findById(id));

        ${module}.describe(<#list fields as field><#if !field.id>${module}Param.get${field.upperName}()<#if field_has_next>,
        </#if></#if></#list>);

        return converter.convert(repository.save(${module}));
    }

    @Transactional(rollbackOn = Exception.class)
    public void remove${Module}(long id) {
        repository.deleteById(id);
    }
}
