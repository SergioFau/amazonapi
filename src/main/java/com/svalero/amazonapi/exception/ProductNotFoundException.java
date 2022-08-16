package com.svalero.amazonapi.exception;

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(long productId) {
        super("Producto " + productId + " no encontrado");
    }

    public ProductNotFoundException() {
        super("Producto no encontrado");
    }
}
