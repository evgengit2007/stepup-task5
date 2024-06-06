package ru.stepup.course2.stepuptask5.service.ProdRegister.create;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.stepup.course2.stepuptask5.exceptions.BadReqException;
import ru.stepup.course2.stepuptask5.interfaces.StepPRinterface;
import ru.stepup.course2.stepuptask5.service.ProdRegister.dto.ProdRegister;

@Service
@Qualifier("PR_Step1")
public class Step1 implements StepPRinterface {
    @Override
    public void stepCheck(ProdRegister prodRegister) {
        if (prodRegister.getInstanceId() == null) {
            throw new BadReqException("Идентификатор экземпляра продукта <InstanceId> не заполнен");
        }
    }
}
