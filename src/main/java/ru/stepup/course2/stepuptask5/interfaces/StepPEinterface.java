package ru.stepup.course2.stepuptask5.interfaces;

import ru.stepup.course2.stepuptask5.service.ProductExample.dto.ProdExample;

public interface StepPEinterface {
    <T> T stepCheck(ProdExample prodExample);
}
