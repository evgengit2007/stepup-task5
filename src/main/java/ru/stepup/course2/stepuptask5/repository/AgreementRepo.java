package ru.stepup.course2.stepuptask5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stepup.course2.stepuptask5.entity.Agreement;

import java.util.List;

public interface AgreementRepo extends JpaRepository<Agreement, Long> {

    Agreement findFirstByNumber(String number);

    List<Agreement> findByProductId(Integer productId);
}
