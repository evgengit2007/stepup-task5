package ru.stepup.course2.stepuptask5.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "tpp_product")
public class TppProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "product_id")
    List<Agreement> agreementList = new ArrayList<>();

    private Long product_code_id;
    private Long client_id;

    @Column(name = "type")
    private String type_str;
    private String number_str;
    private Long priority;
    private LocalDateTime date_of_conclusion;
    private LocalDateTime start_date_time;
    private LocalDateTime end_date_time;
    private Long days;
    private Double penalty_rate;
    private Double nso;
    private Double threshold_amount;
    private String requisite_type;
    private String interest_rate_type;
    private Double tax_rate;
    private String reasone_close;
    private String state;
}
