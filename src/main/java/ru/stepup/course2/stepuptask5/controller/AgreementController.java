package ru.stepup.course2.stepuptask5.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.stepup.course2.stepuptask5.models.prod_register.ProdRegExample;

import javax.print.attribute.standard.Media;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class AgreementController {
    @Autowired
    ProdRegExample prodRegExample;
}
