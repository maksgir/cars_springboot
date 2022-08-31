package com.example.cars_springboot.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PersonWithCarsDTO {
    private long id;
    private String name;
    private String birthDateString;
    private List<CarDTO> cars;
}
