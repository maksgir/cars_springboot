package com.example.cars_springboot.service;

import com.example.cars_springboot.dao.CommonDAO;
import com.example.cars_springboot.dto.CarDTO;
import com.example.cars_springboot.dto.PersonWithCarsDTO;
import com.example.cars_springboot.dto.PersonWithoutCarsDTO;
import com.example.cars_springboot.dto.Statistics;
import com.example.cars_springboot.entity.Car;
import com.example.cars_springboot.entity.Person;
import com.example.cars_springboot.entity.Vendor;
import com.example.cars_springboot.exception.*;
import com.example.cars_springboot.util.CarValidator;
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

    @Autowired
    private CarValidator carValidator;

    @Override
    public void savePerson(PersonWithoutCarsDTO personWithoutCarsDTO) throws ParseException, FutureBirthDateException {

        Person personEntity = converter.convertPersonDTOtoEntity(personWithoutCarsDTO);

        dao.savePerson(personEntity);
    }

    @Override
    public PersonWithCarsDTO getPersonById(long id) throws PersonNotFoundException {

        Person personEntity = dao.getPersonById(id);
        PersonWithCarsDTO personDTO = converter.convertPersonEntityToDTO(personEntity);

        return personDTO;
    }

    @Override
    public void saveCar(CarDTO carDTO) throws PersonNotFoundException, BadModelException, BadHorsePowerException, TooYoungDriverException {
        Car carEntity = converter.convertCarDTOtoEntity(carDTO);

        carValidator.validateCar(carDTO);

        dao.saveCar(carEntity, carDTO.getOwnerId());
        saveVendor(carDTO.getModel());

    }

    @Override
    public Statistics getStatistics() {
        Statistics statistics = new Statistics(dao.getPeopleCount(), dao.getCarsCount(), dao.getVendorsCount());
        return statistics;
    }

    @Override
    public void clear() {
        dao.clear();
    }

    private void saveVendor(String model) {
        String vendorName = model.split("-")[0];

        dao.saveVendor(new Vendor(vendorName));
    }
}
