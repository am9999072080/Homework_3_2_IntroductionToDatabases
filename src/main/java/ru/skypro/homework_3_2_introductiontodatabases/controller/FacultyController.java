package ru.skypro.homework_3_2_introductiontodatabases.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework_3_2_introductiontodatabases.model.Faculty;
import ru.skypro.homework_3_2_introductiontodatabases.services.FacultyService;

import java.util.Collection;
@RestController
@RequestMapping("faculties")
public class FacultyController {
    private final FacultyService service;

    public FacultyController(FacultyService service) {
        this.service = service;
    }


    @PostMapping
    @Operation(summary = "Создание факультета")
    public ResponseEntity<Faculty> create(@RequestBody Faculty faculty) {
        Faculty addedFaculty = service.add(faculty);
        return ResponseEntity.ok(addedFaculty);
    }

    @PutMapping
    @Operation(summary = "Обновление факультета")
    @ApiResponse(responseCode = "404", description = "Запрос некорректный")
    public ResponseEntity<Faculty> update(@RequestBody Faculty faculty) {
        Faculty updatedFaculty = service.update(faculty);

//              if (updatedFaculty == null) {
//            return ResponseEntity.notFound().build();
//            return ResponseEntity.status(404).build();//Указывается код ошибки 404
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();//код ошибки 404
//            }

        return ResponseEntity.ok(updatedFaculty);
    }

    @DeleteMapping({"{id}"})
    @ApiResponse(responseCode = "404", description = "Запрос некорректный")
    @Operation(summary = "Удаление факультета")
    public ResponseEntity<Faculty> delete(@PathVariable Long id) {
        Faculty deletedFaculty = service.remove(id);

//              if (deletedFaculty == null) {
//            return ResponseEntity.notFound().build();
//            return ResponseEntity.status(404).build();//Указывается код ошибки 404
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();//код ошибки 404
//            }
        return ResponseEntity.ok(deletedFaculty);
    }

    @GetMapping({"{id}"})
    @ApiResponse(responseCode = "404", description = "Запрос некорректный")
    @Operation(summary = "Получение факультета по ID")
    public ResponseEntity<Faculty> get(@PathVariable Long id) {
        Faculty faculty = service.get(id);

//               if (faculty == null) {
//            return ResponseEntity.notFound().build();
//            return ResponseEntity.status(404).build();//Указывается код ошибки 404
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();//код ошибки 404
//            }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping({"all"})
    @Operation(summary = "Получение всех факультетов")
    public ResponseEntity<Collection<Faculty>> getAll() {
        Collection<Faculty> faculties = service.getAll();
        return ResponseEntity.ok(faculties);
    }

    @GetMapping("color")
    @Operation(summary = "Получение факультетов по цвету")
    public ResponseEntity<Collection<Faculty>> getByColor(@RequestParam String color) {
        Collection<Faculty> faculties = service.getByColor(color);
        return ResponseEntity.ok(faculties);
    }
}
