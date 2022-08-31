package com.example.cars_springboot.dao;


import com.example.cars_springboot.entity.Person;

import java.util.List;

public interface PersonDAO {
    List<Person> getAllPeopleWithCars();
    void savePerson(Person person);
}
