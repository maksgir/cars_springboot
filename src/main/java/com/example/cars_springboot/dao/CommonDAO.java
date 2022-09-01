package com.example.cars_springboot.dao;


import com.example.cars_springboot.entity.Car;
import com.example.cars_springboot.entity.Person;
import com.example.cars_springboot.entity.Vendor;
import com.example.cars_springboot.exception.PersonNotFoundException;
import com.example.cars_springboot.exception.TooYoungDriverException;

import java.util.List;

public interface CommonDAO {

    void savePerson(Person person);
    Person getPersonById(long id) throws PersonNotFoundException;
    void saveCar(Car car, long id) throws PersonNotFoundException, TooYoungDriverException;
    void saveVendor(Vendor vendor);
    long getPeopleCount();
    long getCarsCount();
    long getVendorsCount();
    void clear();
}
