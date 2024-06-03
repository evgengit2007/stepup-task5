package ru.stepup.course2.stepuptask5;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import ru.stepup.course2.stepuptask5.entity.TppProduct;
import ru.stepup.course2.stepuptask5.exceptions.BadReqException;
import ru.stepup.course2.stepuptask5.exceptions.NotFoundException;
import ru.stepup.course2.stepuptask5.repository.TppProductRegisterRepo;
import ru.stepup.course2.stepuptask5.repository.TppProductRepo;
import ru.stepup.course2.stepuptask5.service.ProdRegister.create.Step1;
import ru.stepup.course2.stepuptask5.service.ProdRegister.create.Step2;
import ru.stepup.course2.stepuptask5.service.ProdRegister.create.Step3;
import ru.stepup.course2.stepuptask5.service.ProductExample.create.*;
import ru.stepup.course2.stepuptask5.service.ProductExample.dto.InstanceArrangement;
import ru.stepup.course2.stepuptask5.service.ProductExample.dto.ProdExample;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = {StepPE1.class, Step2_1.class, Step2_2.class, Step2_3.class, TppProductRegisterRepo.class})
@SpringBootApplication(scanBasePackages = "ru.stepup.course2.stepuptask5")
public class CreateNotNullProdExample {
    // Создаем клон БД из schema.sql и проверяем на нем. После отработки тестов - удаляем
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:14.7-alpine")
            .withDatabaseName("service-db-1")
            .withUsername("username")
            .withPassword("password")
            .withInitScript("schema.sql");

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @BeforeEach
    void setDefaultParameter() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        prodExample.setInstanceId(1L);
        prodExample.setProductType("НСО");
        prodExample.setProductCode("03.012.002");
        prodExample.setRegisterType("03.012.002_47533_ComSoLd");
        prodExample.setMdmCode("15");
        prodExample.setContractNumber("555/FK");
        prodExample.setContractDate(LocalDate.parse("2024-06-03", dateTimeFormatter));
        prodExample.setPriority(Integer.valueOf("00"));
        prodExample.setContractId(666);
        prodExample.setBranchCode("0022");
        prodExample.setIsoCurrencyCode("800");
        prodExample.setUrgencyCode("00");

        List<InstanceArrangement> instanceArrangementList = new ArrayList<>();
        InstanceArrangement instanceArrangement = new InstanceArrangement();
        instanceArrangement.setNumber("666/RT");
        instanceArrangement.setOpeningDate(LocalDate.parse("2024-06-03", dateTimeFormatter));
        instanceArrangementList.add(instanceArrangement);

        instanceArrangement = new InstanceArrangement();
        instanceArrangement.setNumber("777/RT");
        instanceArrangement.setOpeningDate(LocalDate.parse("2024-05-09", dateTimeFormatter));
        instanceArrangementList.add(instanceArrangement);

        prodExample.setInstanceArrangement(instanceArrangementList);
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.name", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    StepPE1 stepPE1;
    @Autowired
    Step2_1 step2_1;
    @Autowired
    Step2_2 step2_2;
    @Autowired
    Step2_3 step2_3;

    @Autowired
    CreateTppProduct createTppProduct;

    @Autowired
    CreateAgreement createAgreement;

    @Autowired
    TppProductRepo tppProductRepo;

    ProdExample prodExample = new ProdExample();

    @Test
    @DisplayName("test stepPE1")
    void testStepPE1() {
        System.out.println("testStepPE1");
        prodExample.setPriority(null);
        Assertions.assertThrows(BadReqException.class, ()-> stepPE1.stepCheck(prodExample));
        prodExample.setContractNumber(null);
        Assertions.assertThrows(BadReqException.class, ()-> stepPE1.stepCheck(prodExample));
    }

    @Test
    @DisplayName("test step2_1")
    void testStep2_1() {
        System.out.println("testStep2_1");
        Assertions.assertThrows(NotFoundException.class, ()-> step2_1.stepCheck(prodExample));
        TppProduct tppProduct = createTppProduct.addRecordTable(prodExample);
        Assertions.assertDoesNotThrow(()-> step2_1.stepCheck(prodExample));
    }

    @Test
    @DisplayName("test step2_2")
    void testStep2_2() {
        System.out.println("testStep2_2");
        Assertions.assertDoesNotThrow(()-> step2_2.stepCheck(prodExample));
        // добавить TppProduct
        TppProduct tppProduct = createTppProduct.addRecordTable(prodExample);
        // добавить Agreement
        createAgreement.createRecsAgreement(prodExample, tppProduct);
        Assertions.assertThrows(BadReqException.class, ()-> step2_2.stepCheck(prodExample));
    }

    @Test
    @DisplayName("test step2_3")
    void testStep2_3() {
        System.out.println("testStep2_3");
        createTppProduct.addRecordTable(prodExample);
        Assertions.assertDoesNotThrow(()-> step2_3.stepCheck(prodExample));
    }

}
