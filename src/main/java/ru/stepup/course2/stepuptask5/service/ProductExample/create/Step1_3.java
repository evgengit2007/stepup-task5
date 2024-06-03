package ru.stepup.course2.stepuptask5.service.ProductExample.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.stepup.course2.stepuptask5.entity.TppRefProductClass;
import ru.stepup.course2.stepuptask5.exceptions.NotFoundException;
import ru.stepup.course2.stepuptask5.interfaces.StepPEinterface;
import ru.stepup.course2.stepuptask5.repository.TppRefProductClassRepo;
import ru.stepup.course2.stepuptask5.service.ProductExample.dto.ProdExample;

import java.util.List;

@Service
@Qualifier("PE_Step1_3")
public class Step1_3 implements StepPEinterface {
    @Autowired
    TppRefProductClassRepo tppRefProductClassRepo;

    @Override
    public <T> T stepCheck(ProdExample prodExample) {
        List<TppRefProductClass> tppRefProductClasses = tppRefProductClassRepo.findByValue(prodExample.getProductCode());
        if (tppRefProductClasses.isEmpty()) {
            throw new NotFoundException("Код продукта " + prodExample.getProductCode() + " не найдено в Каталоге продуктов tpp_ref_product_class");
        }
        return null;
    }
}
