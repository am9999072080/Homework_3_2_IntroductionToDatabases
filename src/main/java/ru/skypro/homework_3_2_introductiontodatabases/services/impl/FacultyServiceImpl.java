package ru.skypro.homework_3_2_introductiontodatabases.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.skypro.homework_3_2_introductiontodatabases.exceptions.EntityNotFoundException;
import ru.skypro.homework_3_2_introductiontodatabases.exceptions.IncorrectArgumentException;
import ru.skypro.homework_3_2_introductiontodatabases.model.Faculty;
import ru.skypro.homework_3_2_introductiontodatabases.repository.FacultyRepository;
import ru.skypro.homework_3_2_introductiontodatabases.services.FacultyService;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository repository;

    public FacultyServiceImpl(FacultyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Faculty add(Faculty faculty) {
        return repository.save(faculty);
    }

    @Override
    public Faculty remove(Long id) {
        Faculty faculty = get(id);
        repository.deleteById(id);
        return faculty;
    }

    @Override
    public Faculty update(Faculty faculty) {
        Faculty existedFaculty = get(faculty.getId());
        return repository.save(faculty);
    }

    @Override
    public Faculty get(Long id) {
        Optional<Faculty> faculty = repository.findById(id);

        if (faculty.isPresent()) {
            return faculty.get();
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Collection<Faculty> getByColor(String color) {
        if (!StringUtils.hasText(color)) {
            throw new IncorrectArgumentException("Некорректный цвет факультета");
        }
        return getAll().stream()
                .filter(f -> f.getColor().equals(color))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Faculty> getAll() {
        return repository.findAll();
    }
}

