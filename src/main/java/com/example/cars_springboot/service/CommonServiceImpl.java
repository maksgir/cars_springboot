package com.example.cars_springboot.service;

import com.example.cars_springboot.dao.CommonDAO;
import com.example.cars_springboot.dto.CarDTO;
import com.example.cars_springboot.dto.PersonWithCarsDTO;
import com.example.cars_springboot.dto.PersonWithoutCarsDTO;
import com.example.cars_springboot.entity.Car;
import com.example.cars_springboot.entity.Person;
import com.example.cars_springboot.exception.FutureBirthDateException;
import com.example.cars_springboot.exception.NoOwnerFoundException;
import com.example.cars_springboot.util.ObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonDAO dao;

    @Autowired
    private ObjectConverter converter;

    @Override
    public List<PersonWithCarsDTO> getAllPeopleWithCars() {
        List<Person> personList = dao.getAllPeopleWithCars();

        List<PersonWithCarsDTO> personWithCarsDTOS = new ArrayList<>();

        for (Person personEntity : personList) {
            personWithCarsDTOS.add(converter.convertPersonEntityToDTO(personEntity));
        }

        return personWithCarsDTOS;


    }

    @Override
    public void savePerson(PersonWithoutCarsDTO personWithoutCarsDTO) throws ParseException, FutureBirthDateException {

        Person personEntity = converter.convertPersonDTOtoEntity(personWithoutCarsDTO);

        dao.savePerson(personEntity);
    }

    @Override
    public PersonWithCarsDTO getPersonById(long id) {

        Person personEntity = dao.getPersonById(id);
        PersonWithCarsDTO personDTO = converter.convertPersonEntityToDTO(personEntity);

        return personDTO;
    }

    @Override
    public void saveCar(CarDTO carDTO) throws NoOwnerFoundException {
        Car carEntity = converter.convertCarDTOtoEntity(carDTO);
        dao.saveCar(carEntity, carDTO.getOwnerId());

    }
}
