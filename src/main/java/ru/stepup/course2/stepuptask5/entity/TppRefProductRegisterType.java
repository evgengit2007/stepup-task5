package ru.stepup.course2.stepuptask5.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class TppRefProductRegisterType {
    @Id
    @GeneratedValue
    private Long id;

    private String value;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "type")
    List<TppProductRegister> tppProductRegisterList = new ArrayList<>();



}
