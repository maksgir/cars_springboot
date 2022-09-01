package com.example.cars_springboot.exception;

public class PersonNotFoundException extends Exception{
    public PersonNotFoundException(String message) {
        super(message);
    }
}
