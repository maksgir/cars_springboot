package com.example.cars_springboot.dao;


import com.example.cars_springboot.entity.Car;
import com.example.cars_springboot.entity.Person;
import com.example.cars_springboot.exception.NoOwnerFoundException;

import java.util.List;

public interface CommonDAO {
    List<Person> getAllPeopleWithCars();
    void savePerson(Person person);
    Person getPersonById(long id);
    void saveCar(Car car, long id) throws NoOwnerFoundException;
}
