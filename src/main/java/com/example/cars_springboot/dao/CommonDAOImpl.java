package com.example.cars_springboot.dao;

import com.example.cars_springboot.entity.Car;
import com.example.cars_springboot.entity.Person;
import com.example.cars_springboot.entity.Vendor;
import com.example.cars_springboot.exception.PersonNotFoundException;
import com.example.cars_springboot.exception.TooYoungDriverException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Date;
import java.time.LocalDate;

@Repository
public class CommonDAOImpl implements CommonDAO {

    @Autowired
    private EntityManager em;

    @Override
    public void savePerson(Person person) {
        em.persist(person);
    }

    @Override
    public Person getPersonById(long id) throws PersonNotFoundException {
        Person person = em.find(Person.class, id);
        if (person == null) {
            throw new PersonNotFoundException("Person with ID=" + id + " wasn't found");
        }
        return person;
    }

    @Override
    public void saveCar(Car car, long id) throws PersonNotFoundException, TooYoungDriverException {
        Person owner = getPersonById(id);

        if (owner.getBirthDate().after(Date.valueOf(LocalDate.now().minusYears(18)))) {
            throw new TooYoungDriverException("The owner must be 18 years old at least");
        }
        car.setOwner(owner);
        em.persist(car);
    }

    @Override
    public void saveVendor(Vendor vendor) {
        System.out.println(vendor);
        em.persist(vendor);
    }

    @Override
    public long getPeopleCount() {
        Query query = em.createQuery("SELECT COUNT(p) FROM Person p");
        return (Long) query.getSingleResult();
    }

    @Override
    public long getCarsCount() {
        Query query = em.createQuery("SELECT COUNT(c) FROM Car c");
        return (Long) query.getSingleResult();
    }

    @Override
    public long getVendorsCount() {
        Query query = em.createQuery("SELECT COUNT(v) FROM Vendor v");
        return (Long) query.getSingleResult();
    }

    @Override
    public void clear() {
        Query q1 = em.createQuery("DELETE FROM Person ");
        Query q2 = em.createQuery("DELETE FROM Car");
        Query q3 = em.createQuery("DELETE FROM Vendor ");

        q3.executeUpdate();
        q2.executeUpdate();
        q1.executeUpdate();

    }
}
