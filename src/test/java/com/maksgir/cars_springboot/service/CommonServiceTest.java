package com.maksgir.cars_springboot.service;


import com.maksgir.cars_springboot.dao.CommonDAO;
import com.maksgir.cars_springboot.dto.CarDTO;
import com.maksgir.cars_springboot.dto.PersonWithoutCarsDTO;
import com.maksgir.cars_springboot.dto.Statistics;
import com.maksgir.cars_springboot.entity.Person;
import com.maksgir.cars_springboot.exception.*;
import com.maksgir.cars_springboot.util.CarValidator;
import com.maksgir.cars_springboot.util.DateConverter;
import com.maksgir.cars_springboot.util.ObjectConverter;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class CommonServiceTest {

    @TestConfiguration
    static class CommonServiceImplTestContextConfiguration {

        @Bean
        public CommonService commonService() {
            return new CommonServiceImpl();
        }

        @Bean
        public ObjectConverter objectConverter(){
            return new ObjectConverter();
        }

        @Bean
        public DateConverter dateConverter(){
            return new DateConverter();
        }

        @Bean
        public CarValidator carValidator(){
            return new CarValidator();
        }
    }

    @Autowired
    private CommonService service;

    @MockBean
    private CommonDAO dao;


    @Test(expected = BadEntityDataException.class)
    public void savePersonWithoutId() throws FutureBirthDateException,  BadEntityDataException {
        PersonWithoutCarsDTO person = new PersonWithoutCarsDTO();
        person.setName("igor");
        person.setBirthdate("15.04.2003");

        service.savePerson(person);
    }

    @Test(expected = BadEntityDataException.class)
    public void savePersonWithoutName() throws FutureBirthDateException,  BadEntityDataException {
        PersonWithoutCarsDTO person = new PersonWithoutCarsDTO();
        person.setId(1000);
        person.setBirthdate("15.04.2003");

        service.savePerson(person);
    }

    @Test(expected = BadEntityDataException.class)
    public void savePersonWithoutDate() throws FutureBirthDateException,  BadEntityDataException {
        PersonWithoutCarsDTO person = new PersonWithoutCarsDTO();
        person.setId(1000);
        person.setName("Ben");

        service.savePerson(person);
    }

    @Test(expected = IncorrectDateFormatException.class)
    public void savePersonWithInvalidDateFormat() throws FutureBirthDateException, BadEntityDataException {
        PersonWithoutCarsDTO person = new PersonWithoutCarsDTO();
        person.setId(1000);
        person.setName("Ben");
        person.setBirthdate("15/04/2003");

        service.savePerson(person);
    }

    @Test(expected = FutureBirthDateException.class)
    public void savePersonWithDateInFuture() throws FutureBirthDateException, BadEntityDataException {
        PersonWithoutCarsDTO person = new PersonWithoutCarsDTO();
        person.setId(1000);
        person.setName("Ben");
        person.setBirthdate("15.04.2200");

        service.savePerson(person);
    }

    @Test(expected = BadEntityDataException.class)
    public void saveAlreadyExistPerson() throws Exception {
        // define already existed person
        PersonWithoutCarsDTO person1 = new PersonWithoutCarsDTO();
        person1.setId(100);
        person1.setName("Ben");
        person1.setBirthdate("15.04.2003");

        // define new person with the same id
        PersonWithoutCarsDTO person2 = new PersonWithoutCarsDTO();
        person2.setId(100);
        person2.setName("Oleg");
        person2.setBirthdate("14.03.2004");

        if (person1.getId()== person2.getId()){
            doThrow(Exception.class).when(dao).savePerson(any(Person.class));
        }

        service.savePerson(person2);

    }


    @Test(expected = PersonNotFoundException.class)
    public void getPersonById_noSuchId() throws PersonNotFoundException {
        when(dao.getPersonById(anyLong())).thenThrow(PersonNotFoundException.class);

        service.getPersonById(1000);
    }

    @Test(expected = BadEntityDataException.class)
    public void saveCarWithoutId() throws BadEntityDataException, BadHorsePowerException, BadModelException, PersonNotFoundException, TooYoungDriverException {
        CarDTO car = new CarDTO();
        car.setModel("BMW-X5");
        car.setHorsepower(200);
        car.setOwnerId(10);


        service.saveCar(car);
    }

    @Test(expected = BadModelException.class)
    public void saveCarWithoutModel() throws BadEntityDataException, BadHorsePowerException, BadModelException, PersonNotFoundException, TooYoungDriverException {
        CarDTO car = new CarDTO();
        car.setId(100);
        car.setHorsepower(200);
        car.setOwnerId(10);


        service.saveCar(car);
    }


    @Test(expected = BadHorsePowerException.class)
    public void saveCarWithoutHorsepower() throws BadEntityDataException, BadHorsePowerException, BadModelException, PersonNotFoundException, TooYoungDriverException {
        CarDTO car = new CarDTO();
        car.setId(100);
        car.setModel("BMW-X5");
        car.setOwnerId(10);


        service.saveCar(car);
    }

    @Test(expected = BadEntityDataException.class)
    public void saveCarWithoutOwnerId() throws BadEntityDataException, BadHorsePowerException, BadModelException, PersonNotFoundException, TooYoungDriverException {
        CarDTO car = new CarDTO();
        car.setId(100);
        car.setModel("BMW-X5");
        car.setHorsepower(200);
        service.saveCar(car);
    }

    @Test(expected = BadModelException.class)
    public void saveCarInvalidModel_emptyVendor() throws BadEntityDataException, BadHorsePowerException, BadModelException, PersonNotFoundException, TooYoungDriverException {
        // empty vendor
        CarDTO car = new CarDTO();
        car.setId(100);
        car.setModel("-X5");
        car.setHorsepower(200);
        car.setOwnerId(10);
        service.saveCar(car);
    }

    @Test(expected = BadModelException.class)
    public void saveCarInvalidModel_emptyModel() throws BadEntityDataException, BadHorsePowerException, BadModelException, PersonNotFoundException, TooYoungDriverException {
        // empty vendor
        CarDTO car = new CarDTO();
        car.setId(100);
        car.setModel("BMW-");
        car.setHorsepower(200);
        car.setOwnerId(10);
        service.saveCar(car);
    }



    // should return true
    @Test
    public void getStatistics_Valid(){
        BDDMockito.given(dao.getPeopleCount()).willReturn(5L);
        BDDMockito.given(dao.getCarsCount()).willReturn(10L);
        BDDMockito.given(dao.getVendorsCount()).willReturn(4L);

        Statistics expected = new Statistics(5, 10, 4);

        Statistics tested = service.getStatistics();

        Assertions.assertEquals(expected, tested);
    }


    // should return false
    @Test
    public void getStatistics_Invalid(){
        BDDMockito.given(dao.getPeopleCount()).willReturn(10L);
        BDDMockito.given(dao.getCarsCount()).willReturn(10L);
        BDDMockito.given(dao.getVendorsCount()).willReturn(10L);

        Statistics expected = new Statistics(1, 10, 10);

        Statistics tested = service.getStatistics();

        Assertions.assertNotEquals(expected, tested);
    }
}
