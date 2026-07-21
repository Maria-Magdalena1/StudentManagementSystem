package main.exception;

import main.exception.base.BadRequestException;

public class StudentAlreadyEnrolledException extends BadRequestException {
    public StudentAlreadyEnrolledException(String message) {
        super(message);
    }
}
