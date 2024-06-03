package ru.stepup.course2.stepuptask5.service.ProdRegister.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.stepup.course2.stepuptask5.entity.TppProductRegister;
import ru.stepup.course2.stepuptask5.exceptions.BadReqException;
import ru.stepup.course2.stepuptask5.interfaces.StepPRinterface;
import ru.stepup.course2.stepuptask5.repository.TppProductRegisterRepo;
import ru.stepup.course2.stepuptask5.service.ProdRegister.dto.ProdRegister;

import java.util.List;

@Service
@Qualifier("PR_Step2")
public class Step2 implements StepPRinterface {
    @Autowired
    TppProductRegisterRepo tppProductRegisterRepo;

    private boolean findDouble(Long instance_id, String type) {
        List<TppProductRegister> tppProductRegisterList = tppProductRegisterRepo.findByproductId(instance_id);
        return tppProductRegisterList.stream().anyMatch(x -> x.getType().equals(type));
    }

    @Override
    public void stepCheck(ProdRegister prodRegister) {
        if (findDouble(prodRegister.getInstanceId(), prodRegister.getRegistryTypeCode())) {
            throw new BadReqException("Параметр registryTypeCode тип регистра " + prodRegister.getRegistryTypeCode() +
                    " уже существует для ЭП  с ИД " + prodRegister.getInstanceId());
        }
    }
}
