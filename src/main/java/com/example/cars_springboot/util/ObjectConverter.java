package com.example.cars_springboot.util;


import com.example.cars_springboot.dto.CarDTO;
import com.example.cars_springboot.dto.PersonWithCarsDTO;
import com.example.cars_springboot.dto.PersonWithoutCarsDTO;
import com.example.cars_springboot.entity.Car;
import com.example.cars_springboot.entity.Person;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
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

    public CarDTO convertCarEntityToDTO(Car car, long ownerId) {
        CarDTO carDTO = new CarDTO();
        BeanUtils.copyProperties(car, carDTO);

        carDTO.setOwnerId(ownerId);

        return carDTO;
    }

    public Person convertPersonDTOtoEntity(PersonWithoutCarsDTO personDTO) throws ParseException {
        Person personEntity = new Person();

        BeanUtils.copyProperties(personDTO, personEntity);

        personEntity.setBirthDate(dateConverter.convertStringDateToSQL(personDTO.getBirthdate()));

        return personEntity;
    }
}
