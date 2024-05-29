package ru.stepup.course2.stepuptask5.service.ProdRegister.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stepup.course2.stepuptask5.entity.TppProductRegister;
import ru.stepup.course2.stepuptask5.repository.TppProductRegisterRepo;
import ru.stepup.course2.stepuptask5.interfaces.ProdRegisterService;
import ru.stepup.course2.stepuptask5.service.ProdRegister.dto.ProdRegister;
import ru.stepup.course2.stepuptask5.service.convertor.TppProdRegisterMapper;

import java.util.List;
import java.util.Optional;

@Data
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Qualifier("TppProductRegister")
public class ProdRegisterServiceImpl implements ProdRegisterService {
    private final TppProductRegisterRepo tppProductRegisterRepo;

    private final TppProdRegisterMapper tppProdRegisterMapper;

    @Override
    public List<ProdRegister> findAll() {
        return tppProdRegisterMapper.toListDto(tppProductRegisterRepo.findAll());
    }

    @Override
    public ProdRegister findById(Long id) {
        return Optional.of(getById(id)).map(tppProdRegisterMapper::modelToDto).get();
    }

    @Override
    @Transactional
    public ProdRegister save(ProdRegister prodRegister) {
        return tppProdRegisterMapper.modelToDto(tppProductRegisterRepo.save(
                tppProdRegisterMapper.dtoToModel(prodRegister)
        ));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        var agr = getById(id);
        tppProductRegisterRepo.delete(agr);
    }

    private TppProductRegister getById(Long id) {
        return tppProductRegisterRepo.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Records with id: " + id + " not found"));
    }

}
