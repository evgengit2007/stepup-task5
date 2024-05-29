package ru.stepup.course2.stepuptask5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stepup.course2.stepuptask5.entity.TppProduct;

public interface TppProductRepo extends JpaRepository<TppProduct, Long> {
}
