package main.web.controller;

import main.service.EnrollmentService;
import main.web.dto.EnrollmentDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/student/{studentId}/course/{courseId}")
    public EnrollmentDTO enrollStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
        return enrollmentService.enrollStudent(studentId, courseId);
    }

    @DeleteMapping("/student/{studentId}/course/{courseId}")
    public void removeStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
        enrollmentService.removeStudent(studentId, courseId);
    }

    @GetMapping("/student/{studentId}")
    public List<EnrollmentDTO> getStudentEnrollments(@PathVariable Long studentId) {
        return enrollmentService.getStudentEnrollments(studentId);
    }

    @GetMapping("/course/{courseId}")
    public List<EnrollmentDTO> getCourseEnrollments(@PathVariable Long courseId) {
        return enrollmentService.getCourseEnrollments(courseId);
    }

    @PutMapping("/student/{studentId}/course/{courseId}/grade")
    public EnrollmentDTO assignGrade(@PathVariable Long studentId,
                                     @PathVariable Long courseId,
                                     @RequestParam Double grade) {
        return enrollmentService.assignGrade(studentId,courseId,grade);
    }
}
