package ru.stepup.course2.stepuptask5.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TppProduct {
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    List<Agreement> agreementList = new ArrayList<>();

    @Column(name = "product_code_id")
    private Long productCodeId;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "type")
    private String typeStr;

    @Column(name = "number_str")
    private String numberStr;

    @Column(name = "priority")
    private Long priority;

    @Column(name = "date_of_conclusion")
    private Timestamp date_of_conclusion;

    @Column(name = "start_date_time")
    private Timestamp startDateTime;

    @Column(name = "days")
    private Long days;

    @Column(name = "penalty_rate")
    private int penaltyRate;

    @Column(name = "nso")
    private int nso;

    @Column(name = "threshold_amount")
    private int thresholdAmount;

    @Column(name = "requisite_type")
    private String requisiteType;

    @Column(name = "interest_rate_type")
    private String interestRateType;

    @Column(name = "tax_rate")
    private int taxRate;

    @Column(name = "reasone_close")
    private String reasoneClose;

    @Column(name = "state")
    private String state;

}
