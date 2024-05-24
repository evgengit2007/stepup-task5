package ru.stepup.course2.stepuptask5.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "tpp_ref_product_register_type")
public class TppRefProductRegisterType {
    @Id
    @GeneratedValue
    private Long id;

    private String value;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "type")
    List<TppProductRegister> tppProductRegisterList = new ArrayList<>();

    private String register_type_name;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_class_code")
    private TppRefProductClass product_class_code;

    private LocalDateTime register_type_start_date;
    private LocalDateTime register_type_end_date;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "account_type")
    private TppRefAccountType account_type;

}
