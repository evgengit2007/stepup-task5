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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long internal_id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_class_code", referencedColumnName = "value")
    List<TppRefProductRegisterType> tppRefProductRegisterTypeList = new ArrayList<>();

    private String value;

    private String gbi_code;
    private String gbi_name;
    private String product_row_code;
    private String product_row_name;
    private String subclass_code;
    private String subclass_name;
}
