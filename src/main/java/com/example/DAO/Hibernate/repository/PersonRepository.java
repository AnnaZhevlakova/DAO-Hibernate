package com.example.DAO.Hibernate.repository;


import com.example.DAO.Hibernate.entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


import java.util.List;

@Scope("request")
@Repository
public class PersonRepository {
    @PersistenceContext
    private EntityManager entityManager;


    public List<Person> getPersonsByCity(String city) {
        try (var session = entityManager.unwrap(Session.class)) {
            var entities =
                    session.createSelectionQuery("where cityOfLiving like :city", Person.class)
                            .setParameter("city", city)
                            .getResultList();

            return entities;
        }
    }
}
