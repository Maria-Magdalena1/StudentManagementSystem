package main.web.controller;

import jakarta.validation.Valid;
import main.service.ProfessorService;
import main.web.dto.ProfessorRequest;
import main.web.dto.ProfessorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professors")
public class ProfessorController {
    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping
    public ProfessorResponse create(@Valid @RequestBody ProfessorRequest request) {
        return professorService.create(request);
    }

    @GetMapping("/{id}")
    public ProfessorResponse getById(@PathVariable Long id) {
        return professorService.getById(id);
    }

    @GetMapping
    public List<ProfessorResponse> getAll() {
        return professorService.getAll();
    }

    @PutMapping("/{id}")
    public ProfessorResponse update(@PathVariable Long id,@Valid @RequestBody ProfessorRequest request) {
        return professorService.update(id, request);
    }

    @DeleteMapping
    public void delete(@PathVariable Long id) {
        professorService.delete(id);
    }
}
