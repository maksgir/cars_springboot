package com.example.cars_springboot.exception;

public class BadPersonDataException extends Exception{
    public BadPersonDataException(String message) {
        super(message);
    }
}
