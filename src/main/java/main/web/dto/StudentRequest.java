package main.web.dto;

import java.time.LocalDate;

public record StudentRequest(String firstName,
                             String lastName,
                             String email,
                             int facultyNumber,
                             LocalDate birthDate) {
}
