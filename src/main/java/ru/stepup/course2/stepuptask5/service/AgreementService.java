package ru.stepup.course2.stepuptask5.service;

import ru.stepup.course2.stepuptask5.DTO.AgreementDto;

import java.util.List;

public interface AgreementService {
    List<AgreementDto> findAll();
    AgreementDto findById(Long id);
    AgreementDto save(AgreementDto agreement);
    void deleteById(Long id);
}
