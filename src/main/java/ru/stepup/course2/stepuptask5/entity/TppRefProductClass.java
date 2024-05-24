package ru.stepup.course2.stepuptask5.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "tpp_ref_product_class")
public class TppRefProductClass {
    @Id
    @GeneratedValue
    private Long id;

    private String value;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_class_code")
    List<TppRefProductRegisterType> tppRefProductRegisterTypeList = new ArrayList<>();

    private String gbi_code;
    private String gbi_name;
    private String product_row_code;
    private String product_row_name;
    private String subclass_code;
    private String subclass_name;
}
