package main.exception;

import main.exception.base.ResourceNotFoundException;

public class ProfessorNotFoundException extends ResourceNotFoundException {
    public ProfessorNotFoundException(String message) {
        super(message);
    }
}
