package ru.stepup.course2.stepuptask5.service.ProductExample.create;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.stepup.course2.stepuptask5.entity.TppProduct;
import ru.stepup.course2.stepuptask5.entity.TppProductRegister;
import ru.stepup.course2.stepuptask5.entity.TppRefProductClass;
import ru.stepup.course2.stepuptask5.entity.TppRefProductRegisterType;
import ru.stepup.course2.stepuptask5.interfaces.CreateRecordsInt;
import ru.stepup.course2.stepuptask5.repository.TppProductRepo;
import ru.stepup.course2.stepuptask5.repository.TppRefProductClassRepo;
import ru.stepup.course2.stepuptask5.repository.TppRefProductRegisterTypeRepo;
import ru.stepup.course2.stepuptask5.service.ProdRegister.create.CreateProdRegister;
import ru.stepup.course2.stepuptask5.service.ProdRegister.dto.ProdRegister;
import ru.stepup.course2.stepuptask5.service.ProductExample.dto.ProdExample;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
@Qualifier("TppProduct")
public class CreateTppProduct implements CreateRecordsInt {
    @Autowired
    TppProductRepo tppProductRepo;

    @Autowired
    TppRefProductClassRepo tppRefProductClassRepo;

    @Autowired
    TppRefProductRegisterTypeRepo tppRefProductRegisterTypeRepo;

    List<TppRefProductRegisterType> registerTypeLst = new ArrayList<>();
    private TppProduct tppProduct;

    @Autowired
    CreateProdRegister createProdRegister;

    TppProductRegister tppProductRegister;

    List<TppProductRegister> tppProductRegisters;

    @Override
    public <T, M> T addRecordTable(M model) {
        ProdExample prodExample = (ProdExample) model;
        TppProduct tppProduct = new TppProduct();
        List<TppRefProductClass> tppRefProductClasses = tppRefProductClassRepo.findByValue(prodExample.getProductCode());
        if (!(tppRefProductClasses == null)) {
            tppProduct.setProduct_code_id(tppRefProductClasses.get(0).getInternal_id());
        }
        tppProduct.setClient_id(Long.valueOf(prodExample.getMdmCode()));
        tppProduct.setType_str(prodExample.getProductType());
        tppProduct.setNumberStr(prodExample.getContractNumber());
        tppProduct.setPriority(Long.valueOf(prodExample.getPriority()));
        tppProduct.setDate_of_conclusion(prodExample.getContractDate().atStartOfDay());
        tppProduct.setPenalty_rate(prodExample.getInterestRatePenalty());
        tppProduct.setThreshold_amount(prodExample.getThresholdAmount());
        tppProduct.setTax_rate(prodExample.getTaxPercentageRate());
        TppProduct tppProductSave = tppProductRepo.save(tppProduct);
        return (T) tppProductSave;
    }

    private <T, M> List<T> getTypeLst(M model) {
        String prodClass = (String) model;
        List<TppRefProductRegisterType> registerTypeList = new ArrayList<>();
        List<TppRefProductClass> tppRefProductClasses = tppRefProductClassRepo.findByValue(prodClass);
        for (TppRefProductClass prodClassLst : tppRefProductClasses) {
            List<TppRefProductRegisterType> refProdRegTypeLst = prodClassLst.getTppRefProductRegisterTypeList();
            for (TppRefProductRegisterType lst : refProdRegTypeLst) {
                if (lst.getAccount_type().getValue().equals("Клиентский")) {
                    registerTypeList.add(lst);
                }
            }
        }
        return (List<T>) registerTypeList;
    }

    @Override
    public <T, M> List<T> addListRecordsTable(M model) {
        ProdExample prodExample = (ProdExample) model;
        List<TppProductRegister> prodLst = new ArrayList<>();
        registerTypeLst = getTypeLst(prodExample.getProductCode());
        for (TppRefProductRegisterType lst : registerTypeLst) {
            ProdRegister prodRegister = new ProdRegister();
            prodRegister.setInstanceId(tppProduct.getId());
            prodRegister.setRegistryTypeCode(lst.getValue());
            prodRegister.setCurrencyCode(prodExample.getIsoCurrencyCode());
            prodRegister.setBranchCode(prodExample.getBranchCode());
            prodRegister.setPriorityCode(prodExample.getUrgencyCode());
            prodRegister.setMdmCode(prodExample.getMdmCode());
            prodRegister.setSalesCode(String.valueOf(prodExample.getReferenceCode()));
//            System.out.println(prodRegister);
            tppProductRegister = createProdRegister.addRecordTable(prodRegister);
            prodLst.add(tppProductRegister);
        }
        System.out.println("prodLst.size() = " + prodLst.size());
        return (List<T>) prodLst;
    }

    public List<TppProductRegister> createRecsProdRegister(ProdExample prodExample, TppProduct tppProduct) {
        this.tppProduct = tppProduct;
        tppProductRegisters = addListRecordsTable(prodExample);
        return tppProductRegisters;
    }

}
