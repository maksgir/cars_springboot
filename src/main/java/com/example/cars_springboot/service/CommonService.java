package com.example.cars_springboot.service;

import com.example.cars_springboot.dto.CarDTO;
import com.example.cars_springboot.dto.PersonWithCarsDTO;
import com.example.cars_springboot.dto.PersonWithoutCarsDTO;
import com.example.cars_springboot.exception.BadModelException;
import com.example.cars_springboot.exception.FutureBirthDateException;
import com.example.cars_springboot.exception.NoOwnerFoundException;

import java.text.ParseException;
import java.util.List;

public interface CommonService {
    List<PersonWithCarsDTO> getAllPeopleWithCars();
    void savePerson(PersonWithoutCarsDTO personWithoutCarsDTO) throws ParseException, FutureBirthDateException;
    PersonWithCarsDTO getPersonById(long id);
    void saveCar(CarDTO carDTO) throws NoOwnerFoundException, BadModelException;
}
