package ru.stepup.course2.stepuptask5.service.ProdRegister.create;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.stepup.course2.stepuptask5.interfaces.CreateRecordsInt;
import ru.stepup.course2.stepuptask5.repository.AccountPoolRepo;
import ru.stepup.course2.stepuptask5.repository.TppProductRegisterRepo;
import ru.stepup.course2.stepuptask5.repository.TppRefProductClassRepo;

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

    @Override
    public <T, M> T addRecordTable(M model) {
        return null;
    }

    @Override
    public <T, M> List<T> addListRecordsTable(M model) {
        return null;
    }
}
