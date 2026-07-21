package main.exception;

import main.exception.base.BadRequestException;

public class FacultyNumberAlreadyExists extends BadRequestException {
    public FacultyNumberAlreadyExists(String message) {
        super(message);
    }
}
