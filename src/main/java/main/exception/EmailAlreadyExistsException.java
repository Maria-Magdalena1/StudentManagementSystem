package main.exception;

import main.exception.base.BadRequestException;

public class EmailAlreadyExistsException extends BadRequestException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
