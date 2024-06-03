package ru.stepup.course2.stepuptask5.service.ProdRegister.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.stepup.course2.stepuptask5.entity.TppRefProductRegisterType;
import ru.stepup.course2.stepuptask5.exceptions.NotFoundException;
import ru.stepup.course2.stepuptask5.interfaces.StepPRinterface;
import ru.stepup.course2.stepuptask5.repository.TppRefProductRegisterTypeRepo;
import ru.stepup.course2.stepuptask5.service.ProdRegister.dto.ProdRegister;

import java.util.List;

@Service
@Qualifier("PR_Step3")
public class Step3 implements StepPRinterface {
    @Autowired
    TppRefProductRegisterTypeRepo tppRefProductRegisterTypeRepo;

    @Override
    public void stepCheck(ProdRegister prodRegister) {
        List<TppRefProductRegisterType> typeList = tppRefProductRegisterTypeRepo.findByValue(prodRegister.getRegistryTypeCode());
        if (typeList.isEmpty()) {
            throw new NotFoundException("Код продукта " + prodRegister.getRegistryTypeCode() +
                    " не найдено в Каталоге продуктов tpp_ref_product_register_type " +
                    "для данного типа Регистра");
        }
    }
}
