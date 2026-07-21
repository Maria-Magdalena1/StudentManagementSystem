package main.exception;

import main.exception.base.BadRequestException;

public class InvalidGradeException extends BadRequestException {
    public InvalidGradeException(String message) {
        super(message);
    }
}
