package ru.stepup.course2.stepuptask5.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class Agreement {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private TppProduct productId;

    @Column(name = "general_agreement_id")
    private String generalAgreementId;

    @Column(name = "supplementary_agreement_id")
    private String supplementaryAgreementId;

    @Column(name = "arrangement_type")
    private String arrangementType;

    @Column(name = "sheduler_job_id")
    private Long shedulerJobId;

    @Column(name = "number")
    private String number;

    @Column(name = "opening_date")
    private Timestamp openingDate;

    @Column(name = "closing_date")
    private Timestamp closingDate;

    @Column(name = "cancel_date")
    private Timestamp cancelDate;

    @Column(name = "validity_duration")
    private Long validityDuration;

    @Column(name = "cancelation_reason")
    private String cancelationReason;

    @Column(name = "status")
    private String status;

    @Column(name = "interest_calculation_date")
    private Timestamp interestCalculationDate;

    @Column(name = "interest_rate")
    private Long interestRate;

    @Column(name = "coefficient")
    private Long coefficient;

    @Column(name = "coefficient_action")
    private String coefficientAction;

    @Column(name = "minimum_interest_rate")
    private Long minimumInterestRate;

    @Column(name = "minimum_interest_rate_coefficient")
    private Long minimumInterestRateCoefficient;

    @Column(name = "minimum_interest_rate_coefficient_action")
    private String minimumInterestRateCoefficientAction;

    @Column(name = "maximum_interest_rate")
    private Long maximumInterestRate;
}
