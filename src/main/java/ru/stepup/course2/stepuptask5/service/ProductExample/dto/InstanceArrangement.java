package ru.stepup.course2.stepuptask5.service.ProductExample.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

// массив Доп.Соглашений
@Data
public class InstanceArrangement {
    private String GeneralAgreementId;                      //ID доп.Ген.соглашения
    private String SupplementaryAgreementId;                //ID доп.соглашения
    private String arrangementType;                         //Тип соглашения
    private Integer shedulerJobId;                          //Идентификатор периодичности учета
    @NotNull
    private String Number;                                  //Номер ДС
    @NotNull
    private LocalDate openingDate;                          //Дата начала сделки
    private LocalDate closingDate;                          //Дата окончания сделки
    private LocalDate CancelDate;                           //Дата отзыва сделки
    private Integer validityDuration;                       //Срок действия сделки
    private String cancellationReason;                      //Причина расторжения
    private String Status;                                  //Состояние/статус
    private LocalDate interestCalculationDate;              //Начисление начинается с (дата)
    private Double interestRate;                            //Процент начисления на остаток
    private Double coefficient;                             //Коэффициент
    private String coefficientAction;                       //Действие коэффициента
    private Double minimumInterestRate;                     //Минимум по ставке
    private String minimumInterestRateCoefficient;          //Коэффициент по минимальной ставке
    private String minimumInterestRateCoefficientAction;    //Действие коэффициента по минимальной ставке
    private Double maximalInterestRate;                     //Максимум по ставке
    private Double maximalInterestRateCoefficient;          //Коэффициент по максимальной ставке
    private String maximalInterestRateCoefficientAction;    //Действие коэффициента по максимальной ставке

}
