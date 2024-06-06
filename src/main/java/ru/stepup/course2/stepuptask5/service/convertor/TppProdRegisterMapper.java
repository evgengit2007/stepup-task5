package ru.stepup.course2.stepuptask5.service.convertor;

import org.mapstruct.Mapper;
import ru.stepup.course2.stepuptask5.entity.TppProductRegister;
import ru.stepup.course2.stepuptask5.service.ProdRegister.dto.ProdRegister;

import java.util.List;

@Mapper(componentModel = "spring", uses = ProdRegister.class)
public interface TppProdRegisterMapper {
    TppProductRegister dtoToModel(ProdRegister prodRegister);

    ProdRegister modelToDto(TppProductRegister tppProductRegister);

    List<ProdRegister> toListDto(List<TppProductRegister> tppProductRegisters);
}
