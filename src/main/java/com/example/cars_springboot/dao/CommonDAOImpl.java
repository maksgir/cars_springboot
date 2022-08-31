package com.example.cars_springboot.dao;

import com.example.cars_springboot.entity.Car;
import com.example.cars_springboot.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CommonDAOImpl implements CommonDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Person> getAllPeopleWithCars() {
        Query query = entityManager.createQuery("from Person");
        List<Person> allEmployees = query.getResultList();

        return allEmployees;
    }

    @Override
    public void savePerson(Person person) {
        entityManager.persist(person);
    }

    @Override
    public Person getPersonById(long id) {

        return entityManager.find(Person.class, id);
    }

    @Override
    public void saveCar(Car car, long id) {
        Person owner = entityManager.find(Person.class, id);
        car.setOwner(owner);
        entityManager.persist(car);
    }
}
