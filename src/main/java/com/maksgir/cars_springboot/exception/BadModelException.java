package com.maksgir.cars_springboot.exception;

public class BadModelException extends Exception{
    public BadModelException(String message) {
        super(message);
    }
}
