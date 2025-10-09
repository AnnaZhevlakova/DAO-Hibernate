package com.example.DAO.Hibernate.repository;


import com.example.DAO.Hibernate.entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

@Scope("request")
@Repository
public class PersonRepository {
    @PersistenceContext
    private EntityManager entityManager;


    public List<Person> findAllCustom() {
        return entityManager.createQuery("SELECT p FROM Person p", Person.class)
                .getResultList();
    }
}
