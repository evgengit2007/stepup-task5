package ru.stepup.course2.stepuptask5.service.convertor;

import org.mapstruct.Mapper;
import ru.stepup.course2.stepuptask5.service.dto.AgreementDto;
import ru.stepup.course2.stepuptask5.entity.Agreement;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AgreementMapper {
    Agreement dtoToModel(AgreementDto agreementDto);
    AgreementDto modelToDto(Agreement agreement);
    List<AgreementDto> toListDto(List<Agreement> agreements);
}