package ru.stepup.course2.stepuptask5.service.ProductExample.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
public class ProdExample {
    private Long instanceId;                       // Id продукта
    @NotBlank
    private String productType;                     // Тип ЭП
    @NotBlank
    private String productCode;                     // Код продукта в каталоге продуктов
    @NotBlank
    private String registerType;                    // Тип регистра
    @NotBlank
    private String mdmCode;                         // Код клиента(mdm)
    @NotBlank
    private String contractNumber;                  // Номер договора
    @NotNull
    private LocalDate contractDate;                 // Дата заключения договора обслуживания
    @NotNull
    private Integer priority;                       // Приоритет
    private Double interestRatePenalty;             // Штрафн. проц. ставка
    private Double minimalBalance;                  // Несниж. остаток
    private Double thresholdAmount;                 // Порог. сумма
    private String accountingDetails;               // Реквизиты выплаты
    private String rateType;                        // Выбор ставки
    private Double taxPercentageRate;               // Ставка налог.
    private Double technicalOverdraftLimitAmount;   // Лимит тех/овер.
    @NotNull
    private Integer contractId;                     // Id договора
    @NotBlank
    private String branchCode;                      // Код филиала
    @NotBlank
    private String isoCurrencyCode;                 // Код валюты
    @NotBlank
    private String urgencyCode;                     // Код срочн-ти договора
    private Integer referenceCode;                  // Код точки продаж
    private Map<String, List<AdditionalPropertiesVip>> additionalPropertiesVips; // массив доп. признаков
    private List<InstanceArrangement> instanceArrangement; // Массив доп. соглашений

    public ProdExample() {
    }
}
