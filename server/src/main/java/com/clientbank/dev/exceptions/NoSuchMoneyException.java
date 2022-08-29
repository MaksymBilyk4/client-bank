package com.clientbank.dev.exceptions;

public class NoSuchMoneyException extends RuntimeException{
    public NoSuchMoneyException(String message) {
        super(message);
    }
}
