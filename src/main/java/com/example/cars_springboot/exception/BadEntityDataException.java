package com.example.cars_springboot.exception;

public class BadEntityDataException extends Exception{
    public BadEntityDataException(String message) {
        super(message);
    }
}
