package main.service;

import main.entity.Course;
import main.entity.Professor;
import main.exception.CourseNotFoundException;
import main.exception.ProfessorNotFoundException;
import main.repository.CourseRepository;
import main.repository.ProfessorRepository;
import main.web.dto.CourseDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final ProfessorRepository professorRepository;

    public CourseService(CourseRepository courseRepository, ProfessorRepository professorRepository) {
        this.courseRepository = courseRepository;
        this.professorRepository = professorRepository;
    }

    public CourseDTO create(CourseDTO dto) {
        Professor professor = professorRepository.findById(dto.professorId())
                .orElseThrow(() -> new ProfessorNotFoundException("Professor not found"));

        Course course = Course.builder()
                .name(dto.name())
                .description(dto.description())
                .credits(dto.credits())
                .createdAt(LocalDate.now())
                .professor(professor)
                .build();
        Course saved = courseRepository.save(course);

        return mapToDTO(saved);
    }

    public CourseDTO getById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));
        return mapToDTO(course);
    }

    public List<CourseDTO> getAll() {
        return courseRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public CourseDTO update(Long id, CourseDTO dto) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));

        course.setName(dto.name());
        course.setDescription(dto.description());
        course.setCredits(dto.credits());

        courseRepository.save(course);

        return mapToDTO(course);
    }

    public void delete(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));

        if (!course.getStudents().isEmpty()) {
            throw new RuntimeException("Course has enrolled students");
        }

        courseRepository.delete(course);
    }

    private CourseDTO mapToDTO(Course course) {
        return new CourseDTO(course.getName(),
                course.getDescription(),
                course.getCredits(),
                course.getProfessor().getId());
    }
}
