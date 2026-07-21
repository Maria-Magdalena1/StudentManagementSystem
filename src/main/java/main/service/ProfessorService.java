package main.service;

import main.entity.Professor;
import main.exception.ProfessorNotFoundException;
import main.repository.ProfessorRepository;
import main.web.dto.ProfessorRequest;
import main.web.dto.ProfessorResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public ProfessorResponse create(ProfessorRequest request) {
        Professor professor = Professor.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .department(request.department())
                .title(request.title())
                .build();

        Professor savedProfessor = professorRepository.save(professor);

        return mapToResponse(savedProfessor);
    }

    public ProfessorResponse getById(Long id) {


        Professor professor = professorRepository.findById(id)
                .orElseThrow(() ->
                        new ProfessorNotFoundException("Professor not found")
                );


        return mapToResponse(professor);
    }

    public List<ProfessorResponse> getAll() {

        return professorRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    public ProfessorResponse update(Long id, ProfessorRequest request) {

        Professor professor = professorRepository.findById(id)
                .orElseThrow(() ->
                        new ProfessorNotFoundException("Professor not found")
                );


        professor.setFirstName(request.firstName());
        professor.setLastName(request.lastName());
        professor.setEmail(request.email());
        professor.setDepartment(request.department());


        Professor updatedProfessor = professorRepository.save(professor);


        return mapToResponse(updatedProfessor);
    }

    public void delete(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() ->
                        new ProfessorNotFoundException("Professor not found")
                );


        if (!professor.getCourses().isEmpty()) {
            throw new RuntimeException("Professor has assigned courses");
        }

        professorRepository.delete(professor);
    }

    private ProfessorResponse mapToResponse(Professor professor) {
        return new ProfessorResponse(
                professor.getFirstName(),
                professor.getLastName(),
                professor.getEmail(),
                professor.getDepartment()
        );
    }

}
