package com.example.cars_springboot.controller;


import com.example.cars_springboot.dto.CarDTO;
import com.example.cars_springboot.dto.PersonWithCarsDTO;
import com.example.cars_springboot.dto.PersonWithoutCarsDTO;
import com.example.cars_springboot.exception.BadModelException;
import com.example.cars_springboot.exception.FutureBirthDateException;
import com.example.cars_springboot.exception.NoOwnerFoundException;
import com.example.cars_springboot.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
public class AppController {

    @Autowired
    private CommonService service;

    @PostMapping("/person")
    public void addNewPerson(@RequestBody PersonWithoutCarsDTO personWithoutCarDTO) throws ParseException, FutureBirthDateException {
        service.savePerson(personWithoutCarDTO);
    }

    @PostMapping("/car")
    public void addNewCar(@RequestBody CarDTO carWithOwner) throws NoOwnerFoundException, BadModelException {
        service.saveCar(carWithOwner);
    }

    @GetMapping("/personwithcars/{personid}")
    public PersonWithCarsDTO getPersonWithCars(@PathVariable int personid) {

        return service.getPersonById(personid);
    }

    @GetMapping("/peoplewithcars")
    public List<PersonWithCarsDTO> getPeople() {
        return service.getAllPeopleWithCars();
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(NoOwnerFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
