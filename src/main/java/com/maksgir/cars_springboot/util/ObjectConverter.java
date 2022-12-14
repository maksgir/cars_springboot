package com.maksgir.cars_springboot.util;


import com.maksgir.cars_springboot.dto.CarDTO;
import com.maksgir.cars_springboot.dto.PersonWithCarsDTO;
import com.maksgir.cars_springboot.dto.PersonWithoutCarsDTO;
import com.maksgir.cars_springboot.entity.Car;
import com.maksgir.cars_springboot.entity.Person;
import com.maksgir.cars_springboot.exception.BadEntityDataException;
import com.maksgir.cars_springboot.exception.BadModelException;
import com.maksgir.cars_springboot.exception.FutureBirthDateException;
import com.maksgir.cars_springboot.exception.IncorrectDateFormatException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ObjectConverter {

    @Autowired
    private DateConverter dateConverter;


    public PersonWithCarsDTO convertPersonEntityToDTO(Person personEntity) {
        PersonWithCarsDTO personDTO = new PersonWithCarsDTO();

        BeanUtils.copyProperties(personEntity, personDTO);


        personDTO.setBirthdate(dateConverter.convertSQLDateToString(personEntity.getBirthDate()));
        List<CarDTO> carDTOList = new ArrayList<>();

        for (Car carEntity : personEntity.getCars()) {
            carDTOList.add(convertCarEntityToDTO(carEntity, personDTO.getId()));
        }

        personDTO.setCars(carDTOList);

        return personDTO;
    }

    public Person convertPersonDTOtoEntity(PersonWithoutCarsDTO personDTO) throws  FutureBirthDateException, IncorrectDateFormatException {
        Person personEntity = new Person();

        BeanUtils.copyProperties(personDTO, personEntity);
        Date birthDate;
        try {
            birthDate = dateConverter.convertStringDateToSQL(personDTO.getBirthdate());
        } catch (ParseException e){
            throw new IncorrectDateFormatException("Incorrect birthdate format. Should be dd.MM.yyyy");
        }

        if (birthDate.after(Date.valueOf(LocalDate.now()))) {
            throw new FutureBirthDateException("BirthDate is in the future");
        }

        personEntity.setBirthDate(birthDate);

        return personEntity;
    }

    public CarDTO convertCarEntityToDTO(Car car, long ownerId) {
        CarDTO carDTO = new CarDTO();
        BeanUtils.copyProperties(car, carDTO);

        carDTO.setOwnerId(ownerId);

        return carDTO;
    }

    public Car convertCarDTOtoEntity(CarDTO carDTO) throws BadModelException {
        Car carEntity = new Car();

        BeanUtils.copyProperties(carDTO, carEntity);


        return carEntity;
    }
}
