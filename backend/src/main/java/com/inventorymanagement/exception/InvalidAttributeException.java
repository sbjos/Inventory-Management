package com.inventorymanagement.exception;

public class InvalidAttributeException extends RuntimeException {
    private static final long serialVersionUID = -1716343033216307521L;

    /**
     * Constructs exception with username, message and cause.
     */
    public InvalidAttributeException() {
        super();
    }

    /**
     * Constructs exception with username, message and cause.
     * @param message - Description of the error encountered.
     */
    public InvalidAttributeException(String message) {
        super(message);
    }

    /**
     * Constructs exception with username, message and cause.
     * @param message - Description of the error encountered.
     * @param cause - The Exception that caused this exception to be thrown.
     */
    public InvalidAttributeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs exception with username, message and cause.
     * @param cause - The Exception that caused this exception to be thrown.
     */
    public InvalidAttributeException(Throwable cause) {
        super(cause);
    }
}
