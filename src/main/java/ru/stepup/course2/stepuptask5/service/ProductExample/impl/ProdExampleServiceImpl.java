package ru.stepup.course2.stepuptask5.service.ProductExample.impl;

import ru.stepup.course2.stepuptask5.interfaces.ProdExampleService;
import ru.stepup.course2.stepuptask5.service.ProductExample.dto.ProdExample;

import java.util.List;

// реализация сервиса через mapping. Сейчас не используется, на будущее попробовать.
public class ProdExampleServiceImpl implements ProdExampleService {
    @Override
    public List<ProdExample> findAll() {
        return null;
    }

    @Override
    public ProdExample findById(Long id) {
        return null;
    }

    @Override
    public ProdExample save(ProdExample prodExample) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
