package ru.stepup.course2.stepuptask5.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "agreement")
public class Agreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private TppProduct product_id;

    private String general_agreement_id;
    private String supplementary_agreement_id;
    private String arrangement_type;
    private Long sheduler_job_id;
    private String number;
    private LocalDateTime opening_date;
    private LocalDateTime closing_date;
    private LocalDateTime cancel_date;
    private Long validity_duration;
    private String cancelation_reason;
    private String status;
    private LocalDateTime interest_calculation_date;
    private Double interest_rate;
    private Double coefficient;
    private String coefficient_action;
    private Double minimum_interest_rate;
    private Double minimum_interest_rate_coefficient;
    private String minimum_interest_rate_coefficient_action;
    private Double maximum_interest_rate;
    private Double maximum_interest_rate_coefficient;
    private String maximum_interest_rate_coefficient_action;
}
