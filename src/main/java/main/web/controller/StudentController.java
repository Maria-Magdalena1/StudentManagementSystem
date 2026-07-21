package main.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import main.service.StudentService;
import main.web.dto.StudentRequest;
import main.web.dto.StudentResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public StudentResponse create(@Valid @RequestBody StudentRequest studentRequest) {
        return studentService.create(studentRequest);
    }

    @GetMapping("/{id}")
    public StudentResponse getById(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @GetMapping
    public List<StudentResponse> getAll() {
        return studentService.getAll();
    }

    @PutMapping("/{id}")
    public StudentResponse update(@PathVariable Long id,@Valid @RequestBody StudentRequest request) {
        return studentService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        studentService.delete(id);
    }

}
