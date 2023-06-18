package ru.skypro.homework_3_2_introductiontodatabases.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework_3_2_introductiontodatabases.model.Faculty;

public interface FacultyRepository extends JpaRepository <Faculty, Long> {
}
