package main.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CourseDTO(

        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
        @NotBlank(message = "Name is required")
        String name,

        @Size(max = 500, message = "Description cannot exceed 500 characters")
        String description,

        @NotNull(message = "Credits are required")
        @Positive(message = "Credits must be positive")
        Integer credits,

        @NotNull(message = "Professor is required")
        Long professorId) {
}
