package com.example.cars_springboot.service;

import com.example.cars_springboot.dto.PersonWithCarsDTO;
import com.example.cars_springboot.dto.PersonWithoutCarsDTO;

import java.text.ParseException;
import java.util.List;

public interface PersonService {
    List<PersonWithCarsDTO> getAllPeopleWithCars();
    void savePerson(PersonWithoutCarsDTO personWithoutCarsDTO) throws ParseException;
}
