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

    @Column(name = "product_id")
    private Long productId;

    private String type;
    private Long account;
    private String currency_code;
    private String state;

    @Column(name = "account_number")
    private String accountNumber;
}
