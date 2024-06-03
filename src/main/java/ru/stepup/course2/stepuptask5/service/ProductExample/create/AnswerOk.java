package ru.stepup.course2.stepuptask5.service.ProductExample.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.stepup.course2.stepuptask5.entity.Agreement;
import ru.stepup.course2.stepuptask5.entity.TppProductRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnswerOk {
    String instanceId;
    List<String> registerIdList = new ArrayList<>();
    List<String> supplementaryAgreementIdList = new ArrayList<>();

    public void makeResponceData(Long tppProductId, List<TppProductRegister> tppProductRegisters, List<Agreement> agreements) {
        this.instanceId = String.valueOf(tppProductId);
        if (!(tppProductRegisters.isEmpty()))
            this.registerIdList = tppProductRegisters.stream().map(x -> x.getId().toString()).collect(Collectors.toList());
        if (!(agreements.isEmpty()))
            this.supplementaryAgreementIdList = agreements.stream().map(x -> x.getId().toString()).collect(Collectors.toList());
    }
}
