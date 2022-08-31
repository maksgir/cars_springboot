package com.example.cars_springboot.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
public class Car {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "model")
    private String model;

    @Column(name = "horse_power")
    private int horsepower;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Person owner;

    public Car(String model, int horsePower) {
        this.model = model;
        this.horsepower = horsePower;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", horsePower=" + horsepower +
                ", owner=" + owner +
                '}';
    }
}
