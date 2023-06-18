package ru.skypro.homework_3_2_introductiontodatabases.services;

import ru.skypro.homework_3_2_introductiontodatabases.model.Faculty;

import java.util.Collection;

public interface FacultyService {
    Faculty add(Faculty faculty);

    Faculty remove(Long id);

    Faculty update(Faculty faculty);

    Faculty get(Long id);

    Collection<Faculty> getByColor(String color);

    Collection<Faculty> getAll();
}
