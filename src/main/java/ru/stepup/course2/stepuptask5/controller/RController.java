package ru.stepup.course2.stepuptask5.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stepup.course2.stepuptask5.interfaces.ProdRegisterService;
import ru.stepup.course2.stepuptask5.service.ProdRegister.dto.ProdRegister;
import ru.stepup.course2.stepuptask5.service.ProdRegister.service.MakeProdRegister;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class RController {
//    @Autowired
//    MakeProdRegister makeProdRegister;

    private final ProdRegisterService prodRegisterService;

    @GetMapping("/corporate-settlement-account/get")
    public List<ProdRegister> allBooks() {
        return prodRegisterService.findAll();
    }

    @GetMapping("/corporate-settlement-account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProdRegister> getBook(@PathVariable Long id) {
        return ResponseEntity.ok().body(prodRegisterService.findById(id));
    }

    @PostMapping("/corporate-settlement-account/create")
    public ResponseEntity<ProdRegister> createBook( @RequestBody ProdRegister prodRegister) throws URISyntaxException {
        ProdRegister result = prodRegisterService.save(prodRegister);
        return ResponseEntity.created(new URI("/corporate-settlement-account/prodRegisters/" + result.getInstanceId()))
                .body(result);
    }

    @PutMapping("/corporate-settlement-account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProdRegister> updateBook( @PathVariable Long id, @RequestBody ProdRegister book) {
        return ResponseEntity.ok().body(prodRegisterService.save(book));
    }

    @DeleteMapping("/corporate-settlement-account/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        prodRegisterService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
