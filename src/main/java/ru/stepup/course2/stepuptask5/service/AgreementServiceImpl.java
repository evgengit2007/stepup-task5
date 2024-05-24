package ru.stepup.course2.stepuptask5.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stepup.course2.stepuptask5.DTO.AgreementDto;
import ru.stepup.course2.stepuptask5.entity.Agreement;
import ru.stepup.course2.stepuptask5.map.AgreementMapper;
import ru.stepup.course2.stepuptask5.repository.AgreementRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AgreementServiceImpl implements AgreementService {
    private final AgreementRepository agreementRepository;
    private final AgreementMapper agreementMapper;

    @Override
    public List<AgreementDto> findAll() {
        return agreementMapper.toListDto(agreementRepository.findAll());
    }

    @Override
    public AgreementDto findById(Long id) {
        return Optional.of(getById(id).map(agreementMapper::modelToDto).get());
    }

    @Override
    @Transactional
    public AgreementDto save(AgreementDto agreement) {
        return agreementMapper.modelToDto(agreementRepository.save(
                agreementMapper.dtoToModel(agreement)
        ));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        var agr = getById(id);
        agreementRepository.delete(agr);
    }

    private Agreement getById(Long id) {
        return agreementRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(
                        "Agreement with id: " + id + " not found"));
    }
}
