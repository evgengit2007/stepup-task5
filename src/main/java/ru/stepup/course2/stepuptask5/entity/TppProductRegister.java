package ru.stepup.course2.stepuptask5.entity;

import jakarta.persistence.*;

@Entity
public class TppProductRegister {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "type")
    private TppRefProductRegisterType registerType;

    @Column(name = "account")
    private Long account;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "state")
    private String state;

    @Column(name = "account_number")
    private String accountNumber;
}
