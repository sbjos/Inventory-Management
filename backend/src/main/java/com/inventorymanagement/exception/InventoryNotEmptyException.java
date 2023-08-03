package com.inventorymanagement.exception;

public class InventoryNotEmptyException extends RuntimeException {
    private static final long serialVersionUID = -2477783951033484664L;

    /**
     * Constructs exception with username, message and cause.
     */
    public InventoryNotEmptyException() {
        super();
    }

    /**
     * Constructs exception with username, message and cause.
     * @param message - Description of the error encountered.
     */
    public InventoryNotEmptyException(String message) {
        super(message);
    }

    /**
     * Constructs exception with username, message and cause.
     * @param message - Description of the error encountered.
     * @param cause - The Exception that caused this exception to be thrown.
     */
    public InventoryNotEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs exception with username, message and cause.
     * @param cause - The Exception that caused this exception to be thrown.
     */
    public InventoryNotEmptyException(Throwable cause) {
        super(cause);
    }
}
