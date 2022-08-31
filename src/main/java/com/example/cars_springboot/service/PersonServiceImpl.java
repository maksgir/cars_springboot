package com.example.cars_springboot.service;

import com.example.cars_springboot.dao.PersonDAO;
import com.example.cars_springboot.dto.PersonWithCarsDTO;
import com.example.cars_springboot.dto.PersonWithoutCarsDTO;
import com.example.cars_springboot.entity.Person;
import com.example.cars_springboot.util.ObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDAO personDAO;

    @Autowired
    private ObjectConverter converter;

    @Override
    public List<PersonWithCarsDTO> getAllPeopleWithCars() {
        List<Person> personList = personDAO.getAllPeopleWithCars();

        List<PersonWithCarsDTO> personWithCarsDTOS = new ArrayList<>();

        for (Person personEntity : personList) {
            personWithCarsDTOS.add(converter.convertPersonEntityToDTO(personEntity));
        }

        return personWithCarsDTOS;


    }

    @Override
    public void savePerson(PersonWithoutCarsDTO personWithoutCarsDTO) throws ParseException {
        Person personEntity = converter.convertPersonDTOtoEntity(personWithoutCarsDTO);

        personDAO.savePerson(personEntity);
    }
}
