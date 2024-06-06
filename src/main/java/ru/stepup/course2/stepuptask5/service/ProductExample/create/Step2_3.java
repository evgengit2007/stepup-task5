package ru.stepup.course2.stepuptask5.service.ProductExample.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.stepup.course2.stepuptask5.entity.Agreement;
import ru.stepup.course2.stepuptask5.entity.TppProduct;
import ru.stepup.course2.stepuptask5.entity.TppProductRegister;
import ru.stepup.course2.stepuptask5.interfaces.StepPEinterface;
import ru.stepup.course2.stepuptask5.repository.TppProductRegisterRepo;
import ru.stepup.course2.stepuptask5.repository.TppProductRepo;
import ru.stepup.course2.stepuptask5.service.ProductExample.dto.ProdExample;

import java.util.List;

@Service
@Qualifier("PE_Step2_3")
public class Step2_3 implements StepPEinterface {
    @Autowired
    TppProductRepo tppProductRepo;
    @Autowired
    TppProductRegisterRepo tppProductRegisterRepo;

    @Autowired
    CreateAgreement createAgreement;

    TppProduct tppProduct;
    List<Agreement> agreementList;

    @Override
    public <T> T stepCheck(ProdExample prodExample) {
        tppProduct = tppProductRepo.findFirstById(prodExample.getInstanceId());
        agreementList = createAgreement.createRecsAgreement(prodExample, tppProduct);
        // получить список записей из tpp_product_register с product_id = tppProduct.id
        List<TppProductRegister> prodLst = tppProductRegisterRepo.findByproductId(tppProduct.getId());
        // создание ответа
        AnswerOk answerOk = new AnswerOk();
        answerOk.makeResponceData(tppProduct.getId(), prodLst, agreementList);
        return (T) answerOk;
    }
}
