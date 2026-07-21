package main.service;

import main.entity.Student;
import main.exception.EmailAlreadyExistsException;
import main.exception.FacultyNumberAlreadyExists;
import main.exception.StudentNotFoundException;
import main.repository.StudentRepository;
import main.web.dto.StudentRequest;
import main.web.dto.StudentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentResponse create(StudentRequest request) {

        if (studentRepository.findByEmail(request.email()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        if (studentRepository.findByFacultyNumber(request.facultyNumber()).isPresent()) {
            throw new FacultyNumberAlreadyExists("Faculty number already exists");
        }

        Student student=Student.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .facultyNumber(request.facultyNumber())
                .dateOfBirth(request.birthDate())
                .build();

        Student savedStudent=studentRepository.save(student);

        return mapToResponse(savedStudent);
    }

    public StudentResponse getById(Long id) {
        Student student=studentRepository.findById(id)
                .orElseThrow(()->new StudentNotFoundException("Student not found"));

        return mapToResponse(student);
    }

    public List<StudentResponse> getAll() {
        return studentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public StudentResponse update(Long id, StudentRequest studentRequest) {
        Student student=studentRepository.findById(id)
                .orElseThrow(()->new StudentNotFoundException("Student not found"));

        studentRepository.findByEmail(studentRequest.email()).ifPresent(existing->{
            if (!existing.getId().equals(id)) {
                throw new EmailAlreadyExistsException("Email already exists");
            }
        });

        studentRepository.findByFacultyNumber(studentRequest.facultyNumber())
                        .ifPresent(existing-> {
                            if (!existing.getId().equals(id)) {
                                throw new FacultyNumberAlreadyExists(("Faculty number already exists"));
                            }
                        });

        student.setFirstName(studentRequest.firstName());
        student.setLastName(studentRequest.lastName());
        student.setEmail(studentRequest.email());
        student.setFacultyNumber(studentRequest.facultyNumber());
        student.setDateOfBirth(studentRequest.birthDate());
        save(student);
        return mapToResponse(student);
    }

    public void delete(Long id) {
        Student student=studentRepository.findById(id)
                .orElseThrow(()->new StudentNotFoundException("Student not found"));
        studentRepository.delete(student);
    }

    public void save(Student student) {
        studentRepository.save(student);
    }

    private StudentResponse mapToResponse(Student student) {
        return new StudentResponse(student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getFacultyNumber());
    }
}
