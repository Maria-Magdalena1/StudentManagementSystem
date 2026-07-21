package main.exception;

import main.exception.base.ResourceNotFoundException;

public class EnrollmentNotFoundException extends ResourceNotFoundException {
    public EnrollmentNotFoundException(String message) {
        super(message);
    }
}
