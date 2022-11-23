package com.maksgir.cars_springboot.service;

import com.maksgir.cars_springboot.dto.CarDTO;
import com.maksgir.cars_springboot.dto.PersonWithCarsDTO;
import com.maksgir.cars_springboot.dto.PersonWithoutCarsDTO;
import com.maksgir.cars_springboot.dto.Statistics;
import com.maksgir.cars_springboot.exception.*;

import java.text.ParseException;

public interface CommonService {
    void savePerson(PersonWithoutCarsDTO personWithoutCarsDTO) throws BadEntityDataException, FutureBirthDateException ;
    PersonWithCarsDTO getPersonById(long id) throws PersonNotFoundException;
    void saveCar(CarDTO carDTO) throws PersonNotFoundException, BadModelException, BadHorsePowerException, TooYoungDriverException, BadEntityDataException;
    Statistics getStatistics();
    void clear();
}
