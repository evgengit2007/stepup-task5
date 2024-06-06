package ru.stepup.course2.stepuptask5;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import ru.stepup.course2.stepuptask5.entity.TppProductRegister;
import ru.stepup.course2.stepuptask5.exceptions.BadReqException;
import ru.stepup.course2.stepuptask5.exceptions.NotFoundException;
import ru.stepup.course2.stepuptask5.repository.TppProductRegisterRepo;
import ru.stepup.course2.stepuptask5.service.ProdRegister.create.CreateProdRegister;
import ru.stepup.course2.stepuptask5.service.ProdRegister.create.Step1;
import ru.stepup.course2.stepuptask5.service.ProdRegister.create.Step2;
import ru.stepup.course2.stepuptask5.service.ProdRegister.create.Step3;
import ru.stepup.course2.stepuptask5.service.ProdRegister.dto.ProdRegister;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest(classes = {Step1.class, Step2.class, Step3.class, TppProductRegisterRepo.class})
@SpringBootApplication(scanBasePackages = "ru.stepup.course2.stepuptask5")
class CreateRegisterTest {
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
        prodRegister.setInstanceId(100L);
        prodRegister.setRegistryTypeCode("03.012.002_47533_ComSoLd");
    }

    @Autowired
    Step1 step1;
    @Autowired
    Step2 step2;
    @Autowired
    Step3 step3;

    @Autowired
    CreateProdRegister createProdRegister;
    ProdRegister prodRegister = new ProdRegister();

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.name", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Test
    @DisplayName("test step1")
    void testStep1() {
        prodRegister.setInstanceId(null);
        Assertions.assertThrows(BadReqException.class, () -> step1.stepCheck(prodRegister));
        prodRegister.setInstanceId(10L);
        assertDoesNotThrow(() -> step1.stepCheck(prodRegister));
    }

    @Test
    @DisplayName("test step2")
    void testStep2() {
        Assertions.assertDoesNotThrow(() -> step2.stepCheck(prodRegister));
    }

    @Test
    @DisplayName("test step3")
    void testStep3() {
        Assertions.assertDoesNotThrow(() -> step3.stepCheck(prodRegister));
        prodRegister.setRegistryTypeCode("03.012.002_47533_QQQ");
        Assertions.assertThrows(NotFoundException.class, () -> step3.stepCheck(prodRegister));
    }

    @Test
    @DisplayName("test createProdRegister")
    void testCreateProdRegister() {
        TppProductRegister tppProductRegister = createProdRegister.addRecordTable(prodRegister);
        Assertions.assertNotNull(tppProductRegister);
        Assertions.assertThrows(BadReqException.class, () -> step2.stepCheck(prodRegister));
    }
}
