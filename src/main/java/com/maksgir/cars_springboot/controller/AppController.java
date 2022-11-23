package com.maksgir.cars_springboot.controller;


import com.maksgir.cars_springboot.dto.CarDTO;
import com.maksgir.cars_springboot.dto.PersonWithCarsDTO;
import com.maksgir.cars_springboot.dto.PersonWithoutCarsDTO;
import com.maksgir.cars_springboot.dto.Statistics;
import com.maksgir.cars_springboot.exception.*;
import com.maksgir.cars_springboot.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
public class AppController {

    @Autowired
    private CommonService service;

    @PostMapping("/person")
    public void addNewPerson(@RequestBody PersonWithoutCarsDTO personWithoutCarDTO) throws BadEntityDataException, FutureBirthDateException  {
        service.savePerson(personWithoutCarDTO);
    }

    @PostMapping("/car")
    public void addNewCar(@RequestBody CarDTO carWithOwner) throws PersonNotFoundException, BadModelException, BadHorsePowerException, TooYoungDriverException, BadEntityDataException {
        service.saveCar(carWithOwner);
    }

    @GetMapping("/personwithcars")
    public PersonWithCarsDTO getPersonWithCars(@RequestParam("personid") long personid) throws PersonNotFoundException {
        return service.getPersonById(personid);
    }

    @GetMapping("/statistics")
    public Statistics getStatistics() {
        return service.getStatistics();
    }

    @GetMapping("/clear")
    public ResponseEntity<String> clear() {
        service.clear();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(PersonNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }


}
