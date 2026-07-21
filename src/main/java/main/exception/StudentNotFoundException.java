package main.exception;

import main.exception.base.ResourceNotFoundException;

public class StudentNotFoundException extends ResourceNotFoundException {
    public StudentNotFoundException(String message) {
        super(message);
    }
}
