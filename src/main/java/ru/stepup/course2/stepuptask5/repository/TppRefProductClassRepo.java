package ru.stepup.course2.stepuptask5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stepup.course2.stepuptask5.entity.TppRefProductClass;

import java.util.List;

public interface TppRefProductClassRepo extends JpaRepository<TppRefProductClass, Long> {
    List<TppRefProductClass> findByValue(String value);

}
