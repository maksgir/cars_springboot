package com.maksgir.cars_springboot.dao;


import com.maksgir.cars_springboot.entity.Car;
import com.maksgir.cars_springboot.entity.Person;
import com.maksgir.cars_springboot.entity.Vendor;
import com.maksgir.cars_springboot.exception.PersonNotFoundException;
import com.maksgir.cars_springboot.exception.TooYoungDriverException;

public interface CommonDAO {

    void savePerson(Person person) throws Exception;
    Person getPersonById(long id) throws PersonNotFoundException;
    void saveCar(Car car, long id) throws PersonNotFoundException, TooYoungDriverException;
    void saveVendor(Vendor vendor);
    long getPeopleCount();
    long getCarsCount();
    long getVendorsCount();
    void clear();
}
