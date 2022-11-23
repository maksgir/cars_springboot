package com.maksgir.cars_springboot.service;

import com.maksgir.cars_springboot.dao.CommonDAO;
import com.maksgir.cars_springboot.dto.CarDTO;
import com.maksgir.cars_springboot.dto.PersonWithCarsDTO;
import com.maksgir.cars_springboot.dto.PersonWithoutCarsDTO;
import com.maksgir.cars_springboot.dto.Statistics;
import com.maksgir.cars_springboot.entity.Car;
import com.maksgir.cars_springboot.entity.Person;
import com.maksgir.cars_springboot.entity.Vendor;
import com.maksgir.cars_springboot.exception.*;
import com.maksgir.cars_springboot.util.CarValidator;
import com.maksgir.cars_springboot.util.ObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Locale;

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
    public void savePerson(PersonWithoutCarsDTO personWithoutCarsDTO) throws BadEntityDataException, FutureBirthDateException {

        if (personWithoutCarsDTO.getId()==0){
            throw new BadEntityDataException("Id is empty");
        } else if (personWithoutCarsDTO.getName()== null){
            throw new BadEntityDataException("Name is empty");
        } else if (personWithoutCarsDTO.getBirthdate()==null){
            throw new BadEntityDataException("Birth date is empty");
        }

        Person personEntity = converter.convertPersonDTOtoEntity(personWithoutCarsDTO);

        try{
            dao.savePerson(personEntity);
        } catch (Exception e){
            throw new BadEntityDataException("Problem with person saving");
        }

    }

    @Override
    public PersonWithCarsDTO getPersonById(long id) throws PersonNotFoundException {

        Person personEntity = dao.getPersonById(id);
        PersonWithCarsDTO personDTO = converter.convertPersonEntityToDTO(personEntity);

        return personDTO;
    }

    @Override
    public void saveCar(CarDTO carDTO) throws PersonNotFoundException, BadModelException, BadHorsePowerException, TooYoungDriverException, BadEntityDataException {
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
        String vendorName = model.split("-")[0].toUpperCase(Locale.ROOT);

        dao.saveVendor(new Vendor(vendorName));
    }

    public void setConverter(ObjectConverter converter) {
        this.converter = converter;
    }

    public void setCarValidator(CarValidator carValidator) {
        this.carValidator = carValidator;
    }
}
