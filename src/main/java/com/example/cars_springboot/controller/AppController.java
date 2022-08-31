package com.example.cars_springboot.controller;


import com.example.cars_springboot.dto.CarDTO;
import com.example.cars_springboot.dto.PersonWithCarsDTO;
import com.example.cars_springboot.dto.PersonWithoutCarsDTO;
import com.example.cars_springboot.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
public class AppController {

    @Autowired
    private PersonService personService;

    @PostMapping("/person")
    public void addNewEmployee(@RequestBody PersonWithoutCarsDTO personWithoutCarDTO) throws ParseException {
        personService.savePerson(personWithoutCarDTO);
    }

    @PostMapping("/car")
    public void addNewEmployee(@RequestBody CarDTO carWithOwner) {

    }

    @GetMapping("/personwithcars/{personid}")
    public PersonWithCarsDTO getEmployee(@PathVariable int personid) {

        return personService.getAllPeopleWithCars().get(personid);
    }

    @GetMapping("/peoplewithcars")
    public List<PersonWithCarsDTO> getEmployee() {
        return personService.getAllPeopleWithCars();
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
