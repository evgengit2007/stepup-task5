package ru.stepup.course2.stepuptask5.service.ProdRegister.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.stepup.course2.stepuptask5.entity.TppProductRegister;
import ru.stepup.course2.stepuptask5.interfaces.StepPRinterface;
import ru.stepup.course2.stepuptask5.service.ProdRegister.create.CreateProdRegister;
import ru.stepup.course2.stepuptask5.service.ProdRegister.create.Step1;
import ru.stepup.course2.stepuptask5.service.ProdRegister.create.Step2;
import ru.stepup.course2.stepuptask5.service.ProdRegister.create.Step3;
import ru.stepup.course2.stepuptask5.service.ProdRegister.dto.ProdRegister;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Service
public class MakeProdRegister {

    private List<StepPRinterface> stepLst;
    private ProdRegister prodRegister;

    @Autowired
    @Qualifier("TppProductRegister")
    CreateProdRegister createProdRegister;
//    ProdRegisterServiceImpl prodRegisterServiceImpl; // альтернативный вариант реализации через map сервис, не доделан

    @Autowired
    public MakeProdRegister(@Qualifier("PR_Step1") Step1 step1
            , @Qualifier("PR_Step2") Step2 step2
            , @Qualifier("PR_Step3") Step3 step3) {
        stepLst = List.of(step1, step2, step3);
    }

    private Object answerOk(Long accId) {
        Map<String, Map<String, String>> mapMap = new HashMap<>();
        mapMap.put("data", new HashMap<>() {{
            put("accountId", accId.toString());
        }});
        return mapMap;
    }

    public Object createRecordProdRegister() {
        for (StepPRinterface lst : stepLst) {
            lst.stepCheck(prodRegister);
        }
        TppProductRegister tppProductRegister = createProdRegister.addRecordTable(prodRegister);
        return answerOk(tppProductRegister.getId());
    }
}
