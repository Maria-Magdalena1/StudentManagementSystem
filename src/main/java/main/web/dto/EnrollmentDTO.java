package main.web.dto;

import main.entity.EnrollmentStatus;

public record EnrollmentDTO(
        Long id,
        Long studentId,
        String studentName,
        Long courseId,
        String courseName,
        Double grade,
        EnrollmentStatus status
        ) {
}
