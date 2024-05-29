package ru.stepup.course2.stepuptask5.service.ProdRegister.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.stepup.course2.stepuptask5.service.ProdRegister.dto.ProdRegister;
import ru.stepup.course2.stepuptask5.service.ProdRegister.impl.ProdRegisterServiceImpl;

@Data
@Service
public class MakeProdRegister {

    private ProdRegister prodRegister;

    @Autowired
    @Qualifier("TppProductRegister")
//    CreateProdRegister createProdRegister;
    ProdRegisterServiceImpl prodRegisterServiceImpl;



}
