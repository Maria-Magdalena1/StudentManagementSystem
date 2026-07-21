package main.web.dto;

public record StudentResponse(Long id,String firstName,String lastName,String email,
                              Integer facultyNumber) {
}
