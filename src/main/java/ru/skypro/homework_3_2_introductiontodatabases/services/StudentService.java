package ru.skypro.homework_3_2_introductiontodatabases.services;

import ru.skypro.homework_3_2_introductiontodatabases.model.Student;

import java.util.Collection;

public interface StudentService {
    Student add(Student student);

    Student remove(Long id);

    Student update(Student student);

    Student get(Long id);

    Collection<Student> getByAge(Integer age);

    Collection<Student> getAll();
}
