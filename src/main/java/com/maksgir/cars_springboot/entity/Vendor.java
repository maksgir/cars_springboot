package com.maksgir.cars_springboot.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vendors")
@Getter
@Setter
@NoArgsConstructor
public class Vendor {
    @Id
    @Column(name = "name")
    private String name;

    public Vendor(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "name='" + name + '\'' +
                '}';
    }
}
