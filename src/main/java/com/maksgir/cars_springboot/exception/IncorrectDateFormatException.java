package com.maksgir.cars_springboot.exception;

public class IncorrectDateFormatException extends RuntimeException{
    public IncorrectDateFormatException(String message) {
        super(message);
    }
}
