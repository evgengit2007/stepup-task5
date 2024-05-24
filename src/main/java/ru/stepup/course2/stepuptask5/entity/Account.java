package ru.stepup.course2.stepuptask5.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_pool_id")
    private AccountPool account_pool_id;

    private String account_number;
    private Boolean bussy;

}
