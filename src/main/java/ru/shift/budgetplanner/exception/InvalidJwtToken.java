package ru.shift.budgetplanner.exception;

public class InvalidJwtToken extends RuntimeException{

    public InvalidJwtToken() {
    }

    public InvalidJwtToken(String message) {
        super(message);
    }

    public InvalidJwtToken(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidJwtToken(Throwable cause) {
        super(cause);
    }
}
