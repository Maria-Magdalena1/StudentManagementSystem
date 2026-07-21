package main.web.dto;

public record CourseDTO(String name,
                        String description,
                        Integer credits,
                        Long professorId) {
}
