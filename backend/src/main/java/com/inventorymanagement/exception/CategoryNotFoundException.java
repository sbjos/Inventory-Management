package com.inventorymanagement.exception;

public class CategoryNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -7293658646466013924L;

    /**
     * Constructs exception with username, message and cause.
     */
    public CategoryNotFoundException() {
        super();
    }

    /**
     * Constructs exception with username, message and cause.
     * @param message - Description of the error encountered.
     */
    public CategoryNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs exception with username, message and cause.
     * @param message - Description of the error encountered.
     * @param cause - The Exception that caused this exception to be thrown.
     */
    public CategoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs exception with username, message and cause.
     * @param cause - The Exception that caused this exception to be thrown.
     */
    public CategoryNotFoundException(Throwable cause) {
        super(cause);
    }
}
