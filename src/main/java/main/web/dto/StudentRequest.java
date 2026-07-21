package main.web.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record StudentRequest(
        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

        @Email(message = "Invalid email")
        @NotBlank(message = "Email is required")
        String email,

        @NotNull(message = "Faculty number is required")
        @Positive(message = "Faculty number must be positive")
        @Min(value = 10000000,message = "Faculty number must be 8 digits")
        @Max(value = 99999999,message = "Faculty number must be 8 digits")
        Integer facultyNumber,

        @NotNull(message = "Birth date is required")
        LocalDate birthDate) {
}
