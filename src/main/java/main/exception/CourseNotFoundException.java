package main.exception;

import main.exception.base.ResourceNotFoundException;

public class CourseNotFoundException extends ResourceNotFoundException {
    public CourseNotFoundException(String message) {
        super(message);
    }
}
