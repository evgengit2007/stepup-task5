package ru.stepup.course2.stepuptask5.entity;

import jakarta.persistence.*;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_pool_id")
    private AccountPool accountPoolId;

    private String accountNumber;
    private Boolean bussy;

}
