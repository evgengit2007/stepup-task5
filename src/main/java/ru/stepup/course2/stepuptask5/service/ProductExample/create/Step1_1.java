package ru.stepup.course2.stepuptask5.service.ProductExample.create;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.stepup.course2.stepuptask5.entity.TppProduct;
import ru.stepup.course2.stepuptask5.exceptions.BadReqException;
import ru.stepup.course2.stepuptask5.interfaces.StepPEinterface;
import ru.stepup.course2.stepuptask5.repository.TppProductRepo;
import ru.stepup.course2.stepuptask5.service.ProductExample.dto.ProdExample;


@Data
@Service
@Qualifier("PE_Step1_1")
public class Step1_1 implements StepPEinterface {

    @Autowired
    TppProductRepo tppProductRepo;


    @Override
    public <T> T stepCheck(ProdExample prodExample) {
        TppProduct tppProduct = tppProductRepo.findFirstByNumberStr(prodExample.getContractNumber());
        // если были найдены дубли
        if (!(tppProduct == null)) {
            throw new BadReqException("Параметр ContractNumber N договора " + prodExample.getContractNumber() + " уже существует для ЭП с ИД " + tppProduct.getId());
        }
        return null;
    }
}
