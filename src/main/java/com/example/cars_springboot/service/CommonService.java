package com.example.cars_springboot.service;

import com.example.cars_springboot.dto.CarDTO;
import com.example.cars_springboot.dto.PersonWithCarsDTO;
import com.example.cars_springboot.dto.PersonWithoutCarsDTO;
import com.example.cars_springboot.dto.Statistics;
import com.example.cars_springboot.exception.*;

import java.text.ParseException;

public interface CommonService {
    void savePerson(PersonWithoutCarsDTO personWithoutCarsDTO) throws ParseException, FutureBirthDateException, BadEntityDataException;
    PersonWithCarsDTO getPersonById(long id) throws PersonNotFoundException;
    void saveCar(CarDTO carDTO) throws PersonNotFoundException, BadModelException, BadHorsePowerException, TooYoungDriverException, BadEntityDataException;
    Statistics getStatistics();
    void clear();
}
