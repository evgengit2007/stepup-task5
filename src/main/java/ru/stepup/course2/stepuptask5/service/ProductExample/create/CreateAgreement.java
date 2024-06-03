package ru.stepup.course2.stepuptask5.service.ProductExample.create;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.stepup.course2.stepuptask5.entity.Agreement;
import ru.stepup.course2.stepuptask5.entity.TppProduct;
import ru.stepup.course2.stepuptask5.interfaces.CreateRecordsInt;
import ru.stepup.course2.stepuptask5.repository.AgreementRepo;
import ru.stepup.course2.stepuptask5.service.ProductExample.dto.InstanceArrangement;
import ru.stepup.course2.stepuptask5.service.ProductExample.dto.ProdExample;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
@Qualifier("Agreement")
public class CreateAgreement implements CreateRecordsInt {
    @Autowired
    AgreementRepo agreementRepo;

    List<Agreement> agreementList = new ArrayList<>();
    TppProduct tppProduct;

    @Override
    public <T, M> T addRecordTable(M model) {
        return null;
    }

    @Override
    public <T, M> List<T> addListRecordsTable(M model) {
        ProdExample prodExample = (ProdExample) model;
        List<InstanceArrangement> instLst = prodExample.getInstanceArrangement();
        for (InstanceArrangement lst : instLst) {
            Agreement agreement = new Agreement();
            agreement.setProductId(Math.toIntExact(tppProduct.getId()));
            agreement.setGeneral_agreement_id(lst.getGeneralAgreementId());
            agreement.setArrangement_type(lst.getArrangementType());
            if (!(lst.getShedulerJobId() == null))
                agreement.setSheduler_job_id(Long.valueOf(lst.getShedulerJobId()));
            agreement.setNumber(lst.getNumber());
            if (!(lst.getOpeningDate() == null))
                agreement.setOpening_date(lst.getOpeningDate().atStartOfDay());
            if (!(lst.getClosingDate() == null))
                agreement.setClosing_date(lst.getClosingDate().atStartOfDay());
            if (!(lst.getCancelDate() == null))
                agreement.setCancel_date(lst.getCancelDate().atStartOfDay());
            if (!(lst.getValidityDuration() == null))
                agreement.setValidity_duration(Long.valueOf(lst.getValidityDuration()));
            agreement.setCancelation_reason(lst.getCancellationReason());
            agreement.setStatus(lst.getStatus());
            if (!(lst.getInterestCalculationDate() == null))
                agreement.setInterest_calculation_date(lst.getInterestCalculationDate().atStartOfDay());
            agreement.setInterest_rate(lst.getInterestRate());
            agreement.setCoefficient(lst.getCoefficient());
            agreement.setCoefficient_action(lst.getCoefficientAction());
            agreement.setMinimum_interest_rate(lst.getMinimumInterestRate());
            agreement.setMinimum_interest_rate_coefficient(lst.getMaximalInterestRateCoefficient());
            agreement.setMinimum_interest_rate_coefficient_action(lst.getMinimumInterestRateCoefficientAction());
            agreement.setMaximum_interest_rate(lst.getMaximalInterestRate());
            agreement.setMaximum_interest_rate_coefficient(lst.getMaximalInterestRateCoefficient());
            agreement.setMaximum_interest_rate_coefficient_action(lst.getMaximalInterestRateCoefficientAction());
//            System.out.println(agreement);
            Agreement agreementSave = agreementRepo.save(agreement);
            agreementList.add(agreementSave);
        }
//        System.out.println("agreementList.size() = " + agreementList.size());
        return (List<T>) agreementList;
    }

    public List<Agreement> createRecsAgreement(ProdExample prodExample, TppProduct tppProduct) {
        this.tppProduct = tppProduct;
        agreementList = addListRecordsTable(prodExample);
        return agreementList;
    }

}
