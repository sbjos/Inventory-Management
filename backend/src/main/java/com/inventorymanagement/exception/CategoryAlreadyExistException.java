package com.inventorymanagement.exception;

public class CategoryAlreadyExistException extends RuntimeException {
    private static final long serialVersionUID = -5391369335526480509L;

    /**
     * Constructs exception with username, message and cause.
     */
    public CategoryAlreadyExistException() {
        super();
    }

    /**
     * Constructs exception with username, message and cause.
     * @param message - Description of the error encountered.
     */
    public CategoryAlreadyExistException(String message) {
        super(message);
    }

    /**
     * Constructs exception with username, message and cause.
     * @param message - Description of the error encountered.
     * @param cause - The Exception that caused this exception to be thrown.
     */
    public CategoryAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs exception with username, message and cause.
     * @param cause - The Exception that caused this exception to be thrown.
     */
    public CategoryAlreadyExistException(Throwable cause) {
        super(cause);
    }
}
