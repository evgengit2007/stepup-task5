package ru.stepup.course2.stepuptask5.service.convertor;

import org.mapstruct.Mapper;
import ru.stepup.course2.stepuptask5.entity.TppProduct;
import ru.stepup.course2.stepuptask5.service.ProductExample.dto.ProdExample;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TppProductMapper {
    TppProduct dtoToModel(ProdExample prodExample);
    ProdExample modelToDto(TppProduct tppProduct);
    List<ProdExample> toListDto(List<TppProduct> tppProducts);

}
