package ${package}.${module};

import com.cartisan.dto.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ${Module}Converter extends Converter<${Module}, ${Module}Dto> {
    ${Module}Converter CONVERTER = Mappers.getMapper(${Module}Converter.class);
}
