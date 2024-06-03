package ru.stepup.course2.stepuptask5;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import ru.stepup.course2.stepuptask5.entity.Agreement;
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

@SpringBootTest(classes = {StepPE1.class, Step1_1.class, Step1_2.class, Step1_3.class, Step1_4.class, TppProductRegisterRepo.class})
@SpringBootApplication(scanBasePackages = "ru.stepup.course2.stepuptask5")
public class CreateNullProdExample {
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
        prodExample.setInstanceId(null);
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
    Step1_1 step1_1;
    @Autowired
    Step1_2 step1_2;
    @Autowired
    Step1_3 step1_3;
    @Autowired
    Step1_4 step1_4;

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
    @DisplayName("test step1_1")
    void testStep1_1() {
        System.out.println("testStep1_1");
        Assertions.assertDoesNotThrow(()-> step1_1.stepCheck(prodExample));
        createTppProduct.addRecordTable(prodExample);
        Assertions.assertThrows(BadReqException.class, ()-> step1_1.stepCheck(prodExample));
    }

    @Test
    @DisplayName("test step1_2")
    void testStep1_2() {
        System.out.println("testStep1_2");
        Assertions.assertDoesNotThrow(()-> step1_2.stepCheck(prodExample));
        // добавить TppProduct
        TppProduct tppProduct = createTppProduct.addRecordTable(prodExample);
        // добавить Agreement
        createAgreement.createRecsAgreement(prodExample, tppProduct);
        Assertions.assertThrows(BadReqException.class, ()-> step1_2.stepCheck(prodExample));
    }

    @Test
    @DisplayName("test step1_3")
    void testStep1_3() {
        System.out.println("testStep1_3");
        Assertions.assertDoesNotThrow(()-> step1_3.stepCheck(prodExample));
        prodExample.setProductCode("03.012.222"); // ставим значение, отсутствующее в БД
        Assertions.assertThrows(NotFoundException.class, ()-> step1_3.stepCheck(prodExample));
    }

    @Test
    @DisplayName("test step1_4")
    void testStep1_4() {
        System.out.println("testStep1_4");
        Assertions.assertDoesNotThrow(()-> step1_4.stepCheck(prodExample));
    }
}
