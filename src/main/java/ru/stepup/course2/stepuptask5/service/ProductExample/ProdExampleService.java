package ru.stepup.course2.stepuptask5.service.ProductExample;

import ru.stepup.course2.stepuptask5.service.ProdRegister.dto.ProdRegister;
import ru.stepup.course2.stepuptask5.service.ProductExample.dto.ProdExample;

import java.util.List;

public interface ProdExampleService {
    List<ProdExample> findAll();
    ProdExample findById(Long id);
    ProdExample save(ProdExample prodExample);
    void deleteById(Long id);

}
