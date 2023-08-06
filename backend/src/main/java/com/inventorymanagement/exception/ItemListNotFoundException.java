package com.inventorymanagement.exception;

public class ItemListNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -8188503948805888569L;

    /**
     * Constructs exception with username, message and cause.
     */
    public ItemListNotFoundException() {
        super();
    }

    /**
     * Constructs exception with username, message and cause.
     * @param message - Description of the error encountered.
     */
    public ItemListNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs exception with username, message and cause.
     * @param message - Description of the error encountered.
     * @param cause - The Exception that caused this exception to be thrown.
     */
    public ItemListNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructs exception with username, message and cause.
     * @param cause - The Exception that caused this exception to be thrown.
     */
    public ItemListNotFoundException(Throwable cause) {
        super(cause);
    }
}
