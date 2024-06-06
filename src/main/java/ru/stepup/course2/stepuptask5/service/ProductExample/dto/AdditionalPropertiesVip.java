package ru.stepup.course2.stepuptask5.service.ProductExample.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
// массив дополнительных признаков для сегмента КИБ(VIP)

@Data
@NoArgsConstructor
public class AdditionalPropertiesVip {
    private String key;
    private String value;
    private String name;
}
