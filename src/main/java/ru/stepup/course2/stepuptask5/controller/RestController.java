package ru.stepup.course2.stepuptask5.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stepup.course2.stepuptask5.interfaces.ProdRegisterService;
import ru.stepup.course2.stepuptask5.service.ProdRegister.dto.ProdRegister;
import ru.stepup.course2.stepuptask5.service.ProdRegister.service.MakeProdRegister;
import ru.stepup.course2.stepuptask5.service.ProductExample.dto.ProdExample;
import ru.stepup.course2.stepuptask5.service.ProductExample.service.MakeProdExample;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestController {
    @Autowired
    MakeProdRegister makeProdRegister;

    @Autowired
    MakeProdExample makeProdExample;
    private final ProdRegisterService prodRegisterService;

    @GetMapping("/corporate-settlement-account/get")
    public List<ProdRegister> allProdRegister() {
        System.out.println("corporate-settlement-account/get");
        return prodRegisterService.findAll();
    }

    @GetMapping("/corporate-settlement-account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProdRegister> getProdRegister(@PathVariable Long id) {
        return ResponseEntity.ok().body(prodRegisterService.findById(id));
    }

    // Продуктовый регистр
    @SneakyThrows
    @PostMapping(value = "/corporate-settlement-account/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createProdRegister( @RequestBody ProdRegister prodRegister) {
        System.out.println("prodRegister = " + prodRegister);
        makeProdRegister.setProdRegister(prodRegister);
        Object obj = makeProdRegister.createRecordProdRegister();
        return new ResponseEntity<>(obj, new HttpHeaders(), HttpStatus.CREATED);
//        ProdRegister result = prodRegisterService.save(prodRegister);
//        System.out.println("result = " + result);
//        return ResponseEntity.created(new URI("/corporate-settlement-account/prodRegisters/" + result.getInstanceId()))
//                .body(result);
    }

    @PutMapping("/corporate-settlement-account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProdRegister> updateProdRegister( @PathVariable Long id, @RequestBody ProdRegister book) {
        return ResponseEntity.ok().body(prodRegisterService.save(book));
    }

    @DeleteMapping("/corporate-settlement-account/{id}")
    public ResponseEntity<?> deleteProdRegister(@PathVariable Long id) {
        prodRegisterService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Продуктовый экземпляр
    @SneakyThrows
    @PostMapping(value = "/corporate-settlement-instance/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTppProduct( @RequestBody ProdExample prodExample) {
        System.out.println("prodExample = " + prodExample);
        makeProdExample.setProdExample(prodExample);
        Object obj = makeProdExample.createTppProduct();
        return new ResponseEntity<>(obj, new HttpHeaders(), HttpStatus.CREATED);
    }
}
