package ru.shift.budgetplanner.exception;

public class CategoryAlreadyExistException extends CategoryException{

    public CategoryAlreadyExistException() {
    }

    public CategoryAlreadyExistException(String message) {
        super(message);
    }

    public CategoryAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryAlreadyExistException(Throwable cause) {
        super(cause);
    }
}
