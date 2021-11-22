package ${packageName}.${camelModule};

import com.cartisan.dto.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ${pascalModule}Converter extends Converter<${pascalModule}, ${pascalModule}Dto> {
    ${pascalModule}Converter CONVERTER = Mappers.getMapper(${pascalModule}Converter.class);
}
