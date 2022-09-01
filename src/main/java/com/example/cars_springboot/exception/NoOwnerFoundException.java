package com.example.cars_springboot.exception;

public class NoOwnerFoundException extends Exception{
    public NoOwnerFoundException(String message) {
        super(message);
    }
}
