package ru.skypro.homework_3_2_introductiontodatabases.services.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework_3_2_introductiontodatabases.exceptions.EntityNotFoundException;
import ru.skypro.homework_3_2_introductiontodatabases.model.Student;
import ru.skypro.homework_3_2_introductiontodatabases.repository.StudentRepository;
import ru.skypro.homework_3_2_introductiontodatabases.services.StudentService;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Student add(Student student) {
        return repository.save(student);
    }

    @Override
    public Student remove(Long id) {
        Student student = get(id);
        repository.deleteById(id);
        return student;
    }

    @Override
    public Student update(Student student) {
        Student existedStudent = get(student.getId());
        return repository.save(student);
    }

    @Override
    public Student get(Long id) {
        Optional<Student> student = repository.findById(id);

        if (student.isPresent()) {
            return student.get();
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Collection<Student> getByAge(Integer age) {
        if (age <= 10 || age >= 80) {
            throw new IllegalArgumentException("INCORRECT STUDENT AGE");
        }
        return getAll().stream()
                .filter(e -> e.getAge().equals(age))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Student> getAll() {
        return repository.findAll();
    }
}

