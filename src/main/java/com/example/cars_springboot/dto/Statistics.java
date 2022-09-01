package com.example.cars_springboot.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Statistics {
    private long personcoun;
    private long carcount;
    private long uniquevendorcount;

    public Statistics(long personcoun, long carcount, long uniquevendorcount) {
        this.personcoun = personcoun;
        this.carcount = carcount;
        this.uniquevendorcount = uniquevendorcount;
    }
}
