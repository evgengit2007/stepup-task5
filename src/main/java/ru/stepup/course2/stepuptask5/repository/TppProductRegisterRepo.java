package ru.stepup.course2.stepuptask5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stepup.course2.stepuptask5.entity.TppProductRegister;

import java.util.List;

public interface TppProductRegisterRepo extends JpaRepository<TppProductRegister, Long> {
    List<TppProductRegister> findByproductId(Long productId);
}
