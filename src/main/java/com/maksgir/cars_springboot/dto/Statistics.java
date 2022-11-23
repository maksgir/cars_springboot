package com.maksgir.cars_springboot.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Statistics {
    private long personcount;
    private long carcount;
    private long uniquevendorcount;

    public Statistics(long personcoun, long carcount, long uniquevendorcount) {
        this.personcount = personcoun;
        this.carcount = carcount;
        this.uniquevendorcount = uniquevendorcount;
    }
}
