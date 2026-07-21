package main.service;

import main.entity.Course;
import main.entity.Enrollment;
import main.entity.EnrollmentStatus;
import main.entity.Student;
import main.exception.*;
import main.repository.CourseRepository;
import main.repository.EnrollmentRepository;
import main.repository.StudentRepository;
import main.web.dto.EnrollmentDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public EnrollmentDTO enrollStudent(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));

        enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId)
                .ifPresent(e -> {
                    throw new StudentAlreadyEnrolledException("Student is already enrolled.");
                });

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setStatus(EnrollmentStatus.ACTIVE);
        enrollment.setEnrolledAt(LocalDate.now());

        Enrollment saved = enrollmentRepository.save(enrollment);

        return mapToResponse(saved);
    }

    public void removeStudent(Long studentId, Long courseId) {
        Enrollment enrollment = enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found"));

        enrollmentRepository.delete(enrollment);
    }

    public List<EnrollmentDTO> getStudentEnrollments(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<EnrollmentDTO> getCourseEnrollments(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public EnrollmentDTO assignGrade(Long studentId, Long courseId, Double grade) {
        studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));

        courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));

        Enrollment enrollment = enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found"));

        if (grade==null || grade<2 ||grade>6) {
            throw new InvalidGradeException("Grade must be between 2 and 6");
        }

        enrollment.setGrade(grade);
        if (grade >= 3.0) {
            enrollment.setStatus(EnrollmentStatus.COMPLETED);
        }
        Enrollment updated = enrollmentRepository.save(enrollment);
        return mapToResponse(updated);
    }

    private EnrollmentDTO mapToResponse(Enrollment enrollment) {
        return new EnrollmentDTO(enrollment.getId(),
                enrollment.getStudent().getId(),
                enrollment.getStudent().getFirstName() + " " +
                        enrollment.getStudent().getLastName(),
                enrollment.getCourse().getId(),
                enrollment.getCourse().getName(),
                enrollment.getGrade(),
                enrollment.getStatus());
    }
}
