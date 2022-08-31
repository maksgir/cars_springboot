package com.example.cars_springboot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "people")
@Getter
@Setter
@NoArgsConstructor
public class Person {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private Date birthDate;

    @OneToMany(mappedBy = "owner")
    private List<Car> cars;

    public Person(String name, Date birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }


    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate;
    }
}
