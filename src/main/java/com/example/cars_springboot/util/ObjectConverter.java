package com.example.cars_springboot.util;


import com.example.cars_springboot.dto.CarDTO;
import com.example.cars_springboot.dto.PersonWithCarsDTO;
import com.example.cars_springboot.dto.PersonWithoutCarsDTO;
import com.example.cars_springboot.entity.Car;
import com.example.cars_springboot.entity.Person;
import com.example.cars_springboot.exception.BadModelException;
import com.example.cars_springboot.exception.FutureBirthDateException;
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

        List<CarDTO> carDTOList = new ArrayList<>();

        for (Car carEntity : personEntity.getCars()) {
            carDTOList.add(convertCarEntityToDTO(carEntity, personDTO.getId()));
        }

        personDTO.setCars(carDTOList);

        return personDTO;
    }

    public Person convertPersonDTOtoEntity(PersonWithoutCarsDTO personDTO) throws ParseException, FutureBirthDateException {
        Person personEntity = new Person();

        BeanUtils.copyProperties(personDTO, personEntity);

        Date birthDate = dateConverter.convertStringDateToSQL(personDTO.getBirthdate());
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
