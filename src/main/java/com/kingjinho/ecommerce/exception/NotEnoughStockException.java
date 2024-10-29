package com.kingjinho.ecommerce.exception;

public class NotEnoughStockException extends RuntimeException {

    public NotEnoughStockException() {

    }

    public NotEnoughStockException(String message) {
        super(message);
    }

    public NotEnoughStockException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public NotEnoughStockException(Throwable throwable) {
        super(throwable);
    }

}
