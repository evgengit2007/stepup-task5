package ru.stepup.course2.stepuptask5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stepup.course2.stepuptask5.entity.TppRefProductRegisterType;

import java.util.List;

public interface TppRefProductRegisterTypeRepo extends JpaRepository<TppRefProductRegisterType, Long> {
    List<TppRefProductRegisterType> findByValue(String value);

}
