package main.web.controller;

import jakarta.validation.Valid;
import main.service.CourseService;
import main.web.dto.CourseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public CourseDTO create (@Valid@RequestBody CourseDTO dto) {
        return courseService.create(dto);
    }

    @GetMapping("/{id}")
    public CourseDTO getById(@PathVariable Long id) {
        return courseService.getById(id);
    }

    @GetMapping
    public List<CourseDTO> getAll() {
        return courseService.getAll();
    }

    @PutMapping("/{id}")
    public CourseDTO update(@PathVariable Long id,@Valid @RequestBody CourseDTO dto) {
        return courseService.update(id, dto);
    }

    @DeleteMapping
    public void delete(@PathVariable Long id) {
        courseService.delete(id);
    }
}
