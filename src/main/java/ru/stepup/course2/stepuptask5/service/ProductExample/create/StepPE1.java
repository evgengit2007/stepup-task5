package ru.stepup.course2.stepuptask5.service.ProductExample.create;

import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.stepup.course2.stepuptask5.exceptions.BadReqException;
import ru.stepup.course2.stepuptask5.interfaces.StepPEinterface;
import ru.stepup.course2.stepuptask5.service.ProductExample.dto.InstanceArrangement;
import ru.stepup.course2.stepuptask5.service.ProductExample.dto.ProdExample;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Validated
@Qualifier("PE_Step1")
public class StepPE1 implements StepPEinterface {
    private List<String> errorList = new ArrayList<>();

    private <T> void resMessage(Set<ConstraintViolation<T>> violations) {
        if (!(violations.isEmpty())) {
            for (ConstraintViolation<T> v : violations) {
                if (v.getMessage().contains("blank") || v.getMessage().contains("null"))
                    errorList.add("Имя обязательного параметра " + v.getPropertyPath().toString() + " не заполнено");
            }
        }
    }

    private void validateDtoModel(@Valid InstanceArrangement instanceArrangement, Validator validator) {
        Set<ConstraintViolation<InstanceArrangement>> violations = validator.validate(instanceArrangement);
        resMessage(violations);
    }

    private void validateDtoModel(@Valid ProdExample prodExample, Validator validator) {
        Set<ConstraintViolation<ProdExample>> violations = validator.validate(prodExample);
        resMessage(violations);
    }

    @Override
    public <T> T stepCheck(ProdExample prodExample) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        validateDtoModel(prodExample, validator); // проверить валидацию полей ProdExample

        List<InstanceArrangement> instanceArrangements = prodExample.getInstanceArrangement();
        for (InstanceArrangement instArr : instanceArrangements) {
            validateDtoModel(instArr, validator); // проверить валидацию полей массива InstanceArrangement
        }
        // если были ошибки валидации
        if (!(errorList.isEmpty())) {
            throw new BadReqException(errorList.toString());
        }
        return null;
    }
}
