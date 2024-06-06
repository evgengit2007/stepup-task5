package ru.stepup.course2.stepuptask5.service.ProductExample.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.stepup.course2.stepuptask5.entity.Agreement;
import ru.stepup.course2.stepuptask5.exceptions.BadReqException;
import ru.stepup.course2.stepuptask5.interfaces.StepPEinterface;
import ru.stepup.course2.stepuptask5.repository.AgreementRepo;
import ru.stepup.course2.stepuptask5.service.ProductExample.dto.InstanceArrangement;
import ru.stepup.course2.stepuptask5.service.ProductExample.dto.ProdExample;

import java.util.List;

@Service
@Qualifier("PE_Step2_2")
public class Step2_2 implements StepPEinterface {
    @Autowired
    AgreementRepo agreementRepo;

    @Override
    public <T> T stepCheck(ProdExample prodExample) {
        List<InstanceArrangement> instanceArrangements = prodExample.getInstanceArrangement();
        for (InstanceArrangement lst : instanceArrangements) {
            Agreement agreement = agreementRepo.findFirstByNumber(lst.getNumber());
            if (!(agreement == null)) {
                throw new BadReqException("Параметр N Дополнительного соглашения (сделки) Number " + lst.getNumber() + " уже существует для ЭП с ИД " + agreement.getId());
            }
        }
        return null;
    }
}
