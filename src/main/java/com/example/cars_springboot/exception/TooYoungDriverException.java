package com.example.cars_springboot.exception;

public class TooYoungDriverException extends Exception{
    public TooYoungDriverException(String message) {
        super(message);
    }
}
