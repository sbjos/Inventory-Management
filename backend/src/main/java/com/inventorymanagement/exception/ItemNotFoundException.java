package com.inventorymanagement.exception;

public class ItemNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1304380842144050132L;

    /**
     * Constructs exception with username, message and cause.
     */
    public ItemNotFoundException() {
        super();
    }

    /**
     * Constructs exception with username, message and cause.
     * @param message - Description of the error encountered.
     */
    public ItemNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs exception with username, message and cause.
     * @param message - Description of the error encountered.
     * @param cause - The Exception that caused this exception to be thrown.
     */
    public ItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs exception with username, message and cause.
     * @param cause - The Exception that caused this exception to be thrown.
     */
    public ItemNotFoundException(Throwable cause) {
        super(cause);
    }
}
