package ru.stepup.course2.stepuptask5.service.ProductExample.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.stepup.course2.stepuptask5.entity.Agreement;
import ru.stepup.course2.stepuptask5.entity.TppProduct;
import ru.stepup.course2.stepuptask5.entity.TppProductRegister;
import ru.stepup.course2.stepuptask5.interfaces.StepPEinterface;
import ru.stepup.course2.stepuptask5.repository.TppRefProductClassRepo;
import ru.stepup.course2.stepuptask5.repository.TppRefProductRegisterTypeRepo;
import ru.stepup.course2.stepuptask5.service.ProductExample.dto.ProdExample;

import java.util.List;

@Service
@Qualifier("PE_Step1_4")
public class Step1_4 implements StepPEinterface {
    @Autowired
    @Qualifier("TppProduct")
    CreateTppProduct createTppProduct;
    @Autowired
    TppRefProductClassRepo tppRefProductClassRepo;
    @Autowired
    TppRefProductRegisterTypeRepo tppRefProductRegisterTypeRepo;
    @Autowired
    CreateAgreement createAgreement;

    TppProduct tppProduct;
    List<TppProductRegister> productRegisters;
    List<Agreement> agreementList;

    private void createRecTable(ProdExample prodExample) {
        // создать tpp_product
        tppProduct = createTppProduct.addRecordTable(prodExample);
        // создать tpp_product_register
        productRegisters = createTppProduct.createRecsProdRegister(prodExample, tppProduct);
        // добавить Agreement
        agreementList = createAgreement.createRecsAgreement(prodExample, tppProduct);
    }

    @Override
    public <T> T stepCheck(ProdExample prodExample) {
        // создание записей
        createRecTable(prodExample);
        // создание ответа
        AnswerOk answerOk = new AnswerOk();
        answerOk.makeResponceData(tppProduct.getId(), productRegisters, agreementList);
        return (T) answerOk;
    }
}
