package ru.stepup.course2.stepuptask5.service.ProductExample.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.stepup.course2.stepuptask5.interfaces.StepPEinterface;
import ru.stepup.course2.stepuptask5.service.ProductExample.create.*;
import ru.stepup.course2.stepuptask5.service.ProductExample.dto.ProdExample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Создание Экземпляров Продуктов
@Data
@Service
public class MakeProdExample {

    ProdExample prodExample;

    private List<StepPEinterface> stepNotNullLst = new ArrayList<>();

    private List<StepPEinterface> stepNullLst = new ArrayList<>();

    @Autowired
    @Qualifier("TppProduct")
    CreateTppProduct createTppProduct;

    public MakeProdExample() {
    }

    @Autowired
    public MakeProdExample(
            @Qualifier("PE_Step1") StepPE1 stepPE1
            , @Qualifier("PE_Step1_1") Step1_1 step1_1
            , @Qualifier("PE_Step1_2") Step1_2 step1_2
            , @Qualifier("PE_Step1_3") Step1_3 step1_3
            , @Qualifier("PE_Step1_4") Step1_4 step1_4
            , @Qualifier("PE_Step2_1") Step2_1 step2_1
            , @Qualifier("PE_Step2_2") Step2_2 step2_2
            , @Qualifier("PE_Step2_3") Step2_3 step2_3) {
        stepNullLst = List.of(stepPE1, step1_1, step1_2, step1_3, step1_4);
        stepNotNullLst = List.of(stepPE1, step2_1, step2_2, step2_3);
    }

    private Object answerOk(AnswerOk answerOk) {
        Map<String, AnswerOk> mapAnswer = new HashMap<>();
        mapAnswer.put("data", answerOk);
        return mapAnswer;
    }

    public Object createTppProduct() {
        Object resultResp = null;
        if (prodExample.getInstanceId() == null) {
            for (StepPEinterface lst : stepNullLst) {
                resultResp = lst.stepCheck(prodExample);
            }
        } else {
            for (StepPEinterface lst : stepNotNullLst) {
                resultResp = lst.stepCheck(prodExample);
            }
        }
        if (!(resultResp == null)) {
            return answerOk((AnswerOk) resultResp);
        }
        return ResponseEntity.status((HttpStatus.INTERNAL_SERVER_ERROR))
                .body(Map.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ошибка обработки запроса "));
    }

}
