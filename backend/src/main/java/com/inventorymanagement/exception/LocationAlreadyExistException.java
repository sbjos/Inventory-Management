package com.inventorymanagement.exception;

public class LocationAlreadyExistException extends RuntimeException {
    private static final long serialVersionUID = -2477783951033484664L;

    /**
     * Constructs exception with username, message and cause.
     */
    public LocationAlreadyExistException() {
        super();
    }

    /**
     * Constructs exception with username, message and cause.
     * @param message - Description of the error encountered.
     */
    public LocationAlreadyExistException(String message) {
        super(message);
    }

    /**
     * Constructs exception with username, message and cause.
     * @param message - Description of the error encountered.
     * @param cause - The Exception that caused this exception to be thrown.
     */
    public LocationAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs exception with username, message and cause.
     * @param cause - The Exception that caused this exception to be thrown.
     */
    public LocationAlreadyExistException(Throwable cause) {
        super(cause);
    }
}
