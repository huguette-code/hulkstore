package com.example.exception;

public class ProductOutOfStockException extends Exception{
    private static final String MESSAGE = "Product Out Of Stock";

    public ProductOutOfStockException() {
        super(MESSAGE);
    }
}
