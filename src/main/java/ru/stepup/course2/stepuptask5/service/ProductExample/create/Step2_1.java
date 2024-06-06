package ru.stepup.course2.stepuptask5.service.ProductExample.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.stepup.course2.stepuptask5.entity.TppProduct;
import ru.stepup.course2.stepuptask5.exceptions.NotFoundException;
import ru.stepup.course2.stepuptask5.interfaces.StepPEinterface;
import ru.stepup.course2.stepuptask5.repository.TppProductRepo;
import ru.stepup.course2.stepuptask5.service.ProductExample.dto.ProdExample;

@Service
@Qualifier("PE_Step2_1")
public class Step2_1 implements StepPEinterface {
    @Autowired
    TppProductRepo tppProductRepo;

    TppProduct tppProduct;

    @Override
    public <T> T stepCheck(ProdExample prodExample) {
        tppProduct = tppProductRepo.findFirstById(prodExample.getInstanceId());
        if (tppProduct == null) {
            throw new NotFoundException("Экземпляр продукта с параметром instanceId " + prodExample.getInstanceId() + " не найден.");
        }
        return null;
    }
}
