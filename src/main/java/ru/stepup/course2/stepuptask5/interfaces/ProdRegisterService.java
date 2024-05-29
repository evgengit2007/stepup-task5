package ru.stepup.course2.stepuptask5.interfaces;

import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.stepup.course2.stepuptask5.service.ProdRegister.dto.ProdRegister;

import java.util.List;

public interface ProdRegisterService {
    List<ProdRegister> findAll();
    ProdRegister findById(Long id);
    ProdRegister save(ProdRegister prodRegister);
    void deleteById(Long id);
}
