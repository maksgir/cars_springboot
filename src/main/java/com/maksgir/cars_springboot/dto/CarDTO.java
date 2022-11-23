package com.maksgir.cars_springboot.dto;


import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class CarDTO {
    private long id;
    private String model;
    private int horsepower;
    private long ownerId;
}
