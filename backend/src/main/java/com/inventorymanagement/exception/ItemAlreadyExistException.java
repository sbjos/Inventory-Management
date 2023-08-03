package com.inventorymanagement.exception;

public class ItemAlreadyExistException extends RuntimeException {
    private static final long serialVersionUID = 9146442550867896374L;

    /**
     * Constructs exception with username, message and cause.
     */
    public ItemAlreadyExistException() {
        super();
    }

    /**
     * Constructs exception with username, message and cause.
     * @param message - Description of the error encountered.
     */
    public ItemAlreadyExistException(String message) {
        super(message);
    }

    /**
     * Constructs exception with username, message and cause.
     * @param message - Description of the error encountered.
     * @param cause - The Exception that caused this exception to be thrown.
     */
    public ItemAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs exception with username, message and cause.
     * @param cause - The Exception that caused this exception to be thrown.
     */
    public ItemAlreadyExistException(Throwable cause) {
        super(cause);
    }
}
