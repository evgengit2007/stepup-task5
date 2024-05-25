package ru.stepup.course2.stepuptask5.models.prod_register;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude
public class ProdRegExample {
    private Long instanceId;            // Идентификатор ЭП, к которому привязывается продуктовый регистр
    private String registryTypeCode;    // Тип создаваемого продуктового регистра
    private String accountType;         // Клиентский или внутрибанковский
    private String currencyCode;        // 3-х значный код валюты
    private String branchCode;          // Код филиала
    private String priorityCode;        // Всегда 00 для ПП РО ЮЛ
    private String mdmCode;             // МДМ код клиента (КЮЛ)
    private String clientCode;          // Только для ВИП (РЖД, ФПК). Обсуждается с клиентом (есть выбор).
    private String trainRegion;         // Только для ВИП (РЖД, ФПК)
    private String counter;             // Только для ВИП (РЖД, ФПК)
    private String salesCode;           // Код точки продаж
}
