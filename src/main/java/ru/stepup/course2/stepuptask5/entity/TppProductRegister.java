package ru.stepup.course2.stepuptask5.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tpp_product_register")
public class TppProductRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "type")
    private TppRefProductRegisterType tppRefProductRegisterType;

    private Long account;
    private String currency_code;
    private String state;
    private String account_number;
}
