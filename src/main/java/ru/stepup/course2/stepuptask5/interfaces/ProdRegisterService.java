package ru.stepup.course2.stepuptask5.interfaces;

import ru.stepup.course2.stepuptask5.service.ProdRegister.dto.ProdRegister;

import java.util.List;

public interface ProdRegisterService {
    List<ProdRegister> findAll();

    ProdRegister findById(Long id);

    ProdRegister save(ProdRegister prodRegister);

    void deleteById(Long id);
}
