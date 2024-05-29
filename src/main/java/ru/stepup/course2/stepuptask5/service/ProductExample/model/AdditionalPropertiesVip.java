package ru.stepup.course2.stepuptask5.service.ProductExample.model;

import lombok.Data;
import lombok.NoArgsConstructor;
// Массив дополнительных признаков для сегмента КИБ

@Data
@NoArgsConstructor
public class AdditionalPropertiesVip {
    private String key;
    private String value;
    private String name;
}
