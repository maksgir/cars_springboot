package com.maksgir.cars_springboot.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PersonWithoutCarsDTO {
    private long id;
    private String name;
    private String birthdate;
}
