package ru.stepup.course2.stepuptask5.service.ProdRegister.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdRegister {
    private Long instanceId;            // Id продукта
    private String registryTypeCode;    // Тип регистра
    private String accountType;         // Тип счета
    private String currencyCode;        // Код валюты
    private String branchCode;          // Код филиала
    private String priorityCode;        // Код срочности
    private String mdmCode;             // Id Клиента
    private String clientCode;          // Код клиента
    private String trainRegion;         // Регион ЖД
    private String counter;             // Счетчик
    private String salesCode;           // Код точки продаж
}
