package main.web.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import main.entity.EnrollmentStatus;

public record EnrollmentDTO(
        Long id,

        @NotNull(message = "Student id is required")
        Long studentId,

        String studentName,

        @NotNull(message = "Course id is required")
        Long courseId,

        String courseName,

        @NotNull(message = "Grade is required")
        @DecimalMin(value = "2.0", message = "Grade must be at least 2")
        @DecimalMax(value = "6.0", message = "Grade must be maximum 6")
        Double grade,
        EnrollmentStatus status
) {
}
