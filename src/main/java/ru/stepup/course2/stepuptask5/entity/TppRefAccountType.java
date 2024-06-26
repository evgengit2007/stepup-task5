package ru.stepup.course2.stepuptask5.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "tpp_ref_account_type")
public class TppRefAccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long internal_id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "account_type", referencedColumnName = "value")
    List<TppRefProductRegisterType> tppRefProductRegisterTypeList = new ArrayList<>();

    private String value;
}
