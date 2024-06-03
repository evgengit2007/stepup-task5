package ru.stepup.course2.stepuptask5.service.ProdRegister.create;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.stepup.course2.stepuptask5.eNumState.State;
import ru.stepup.course2.stepuptask5.entity.*;
import ru.stepup.course2.stepuptask5.interfaces.CreateRecordsInt;
import ru.stepup.course2.stepuptask5.repository.AccountPoolRepo;
import ru.stepup.course2.stepuptask5.repository.TppProductRegisterRepo;
import ru.stepup.course2.stepuptask5.repository.TppRefProductClassRepo;
import ru.stepup.course2.stepuptask5.repository.TppRefProductRegisterTypeRepo;
import ru.stepup.course2.stepuptask5.service.ProdRegister.dto.ProdRegister;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
@Qualifier("TppProductRegister")
public class CreateProdRegister implements CreateRecordsInt {
    @Autowired
    AccountPoolRepo accountPoolRepo;

    @Autowired
    TppRefProductClassRepo tppRefProductClassRepo;

    @Autowired
    TppProductRegisterRepo tppProductRegisterRepo;

    @Autowired
    TppRefProductRegisterTypeRepo tppRefProductRegisterTypeRepo;

    List<TppRefProductRegisterType> registerTypeLst = new ArrayList<>();
    private TppProduct tppProduct;

    @Override
    public <T, M> T addRecordTable(M model) {
        ProdRegister prodRegister = (ProdRegister) model;
        TppProductRegister tppProductRegister = new TppProductRegister();
        AccountPool accountPool = accountPoolRepo.findFirstByBranchCodeAndCurrencyCodeAndMdmCodeAndPriorityCodeAndRegistryTypeCode(
                prodRegister.getBranchCode(),
                prodRegister.getCurrencyCode(),
                prodRegister.getMdmCode(),
                prodRegister.getPriorityCode(),
                prodRegister.getRegistryTypeCode()
        );
        tppProductRegister.setProductId(prodRegister.getInstanceId());
        tppProductRegister.setType(prodRegister.getRegistryTypeCode());
        tppProductRegister.setCurrency_code(prodRegister.getCurrencyCode());
        tppProductRegister.setState(State.OPEN.getCode());
        tppProductRegister.setType(prodRegister.getRegistryTypeCode());
        if (!(accountPool == null)) {
            List<Account> accountList = accountPool.getAccountList();
            Account account = accountList.get(0);
            tppProductRegister.setAccount(account.getId());
            tppProductRegister.setAccountNumber(account.getAccount_number());
        }
        TppProductRegister tppProductRegisterSave = tppProductRegisterRepo.save(tppProductRegister);
        return (T) tppProductRegisterSave;
    }

    @Override
    public <T, M> List<T> addListRecordsTable(M model) {
        return null;
    }
}
