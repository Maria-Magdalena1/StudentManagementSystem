package main.web.dto;

import lombok.Builder;

public record StudentResponse(Long id,String firstName,String lastName,String email,
                              int facultyNumber) {
}
