package ru.skypro.homework_3_2_introductiontodatabases.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework_3_2_introductiontodatabases.model.Student;
import ru.skypro.homework_3_2_introductiontodatabases.services.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("student")
@Tag(name = "Api: Для работы со студентами")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Создание студента")
    public ResponseEntity<Student> create(@RequestBody Student student) {
        Student addedStudent = service.add(student);
        return ResponseEntity.ok(addedStudent);
    }

    @PutMapping
    @Operation(summary = "Обновление студента")
    @ApiResponse(responseCode = "404", description = "Запрос некорректный")
    public ResponseEntity<Student> update(@RequestBody Student student) {
        Student updatedStudent = service.update(student);

//              if (updatedStudent == null) {
//            return ResponseEntity.notFound().build();
//            return ResponseEntity.status(404).build();//Указывается код ошибки 404
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();//код ошибки 404
//            }

        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping({"{id}"})
    @ApiResponse(responseCode = "404", description = "Запрос некорректный")
    @Operation(summary = "Удаление студента")
    public ResponseEntity<Student> delete(@PathVariable Long id) {
        Student deletedStudent = service.remove(id);

//              if (deletedStudent == null) {
//            return ResponseEntity.notFound().build();
//            return ResponseEntity.status(404).build();//Указывается код ошибки 404
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();//код ошибки 404
//            }
        return ResponseEntity.ok(deletedStudent);
    }

    @GetMapping({"{id}"})
    @ApiResponse(responseCode = "404", description = "Запрос некорректный")
    @Operation(summary = "Получение студента по ID")
    public ResponseEntity<Student> get(@PathVariable Long id) {
        Student student = service.get(id);

//               if (student == null) {
//            return ResponseEntity.notFound().build();
//            return ResponseEntity.status(404).build();//Указывается код ошибки 404
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();//код ошибки 404
//            }
        return ResponseEntity.ok(student);
    }

    @GetMapping({"all"})
    @Operation(summary = "Получение списка всех студентов")
    public ResponseEntity<Collection> getAll() {
        Collection<Student> students = service.getAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping("age")
    @Operation(summary = "Получение списка всех студентов по возрасту")
    public ResponseEntity<Collection> getByAge(@RequestParam Integer age) {
        Collection<Student> students = service.getByAge(age);
        return ResponseEntity.ok(students);
    }
}
