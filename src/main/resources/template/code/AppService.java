package ${package}.${module};

import com.cartisan.constants.CodeMessage;
import com.cartisan.dtos.PageResult;
import com.cartisan.exceptions.CartisanException;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.cartisan.repositories.ConditionSpecifications.querySpecification;
import static com.cartisan.utils.AssertionUtil.requirePresent;
import static java.util.stream.Collectors.toList;

/**
 * @author colin
 */
@Service
public class ${Module}AppService {
    private final ${Module}Repository repository;

    private final ${Module}Converter converter = ${Module}Converter.CONVERTER;

    public ${Module}AppService(${Module}Repository repository) {
        this.repository = repository;
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
        final ${Module} ${module} = new ${Module}(roleParam.getName());
        role.describe(roleParam.getName(), roleParam.getDescription(), roleParam.getSort());

        return roleDetailConverter.convert(repository.save(role));
    }

    @Transactional(rollbackOn = Exception.class)
    public RoleDetailDto editRole(Long id, RoleParam roleParam) {
        final Role role = requirePresent(repository.findById(id));

        role.describe(roleParam.getName(), roleParam.getDescription(), roleParam.getSort());

        return roleDetailConverter.convert(repository.save(role));
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeRole(long id) {
        repository.deleteById(id);
    }
}
