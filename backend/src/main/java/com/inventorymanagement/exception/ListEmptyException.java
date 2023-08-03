package com.inventorymanagement.exception;

public class ListEmptyException extends RuntimeException {
    private static final long serialVersionUID = 1896300600500152638L;

    /**
     * Constructs exception with username, message and cause.
     */
    public ListEmptyException() {
        super();
    }

    /**
     * Constructs exception with username, message and cause.
     * @param message - Description of the error encountered.
     */
    public ListEmptyException(String message) {
        super(message);
    }

    /**
     * Constructs exception with username, message and cause.
     * @param message - Description of the error encountered.
     * @param cause - The Exception that caused this exception to be thrown.
     */
    public ListEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs exception with username, message and cause.
     * @param cause - The Exception that caused this exception to be thrown.
     */
    public ListEmptyException(Throwable cause) {
        super(cause);
    }
}

