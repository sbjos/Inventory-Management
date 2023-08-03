package com.inventorymanagement.exception;

public class InventoryNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -8188503948805888569L;

    /**
     * Constructs exception with username, message and cause.
     */
    public InventoryNotFoundException() {
        super();
    }

    /**
     * Constructs exception with username, message and cause.
     * @param message - Description of the error encountered.
     */
    public InventoryNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs exception with username, message and cause.
     * @param message - Description of the error encountered.
     * @param cause - The Exception that caused this exception to be thrown.
     */
    public InventoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructs exception with username, message and cause.
     * @param cause - The Exception that caused this exception to be thrown.
     */
    public InventoryNotFoundException(Throwable cause) {
        super(cause);
    }
}
