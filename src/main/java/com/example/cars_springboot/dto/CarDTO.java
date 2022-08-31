package com.example.cars_springboot.dto;


import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class CarDTO {
    private int id;
    private String model;
    private int horsePower;
    private long ownerId;
}
