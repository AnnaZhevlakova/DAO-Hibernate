package com.example.DAO.Hibernate.service;


import com.example.DAO.Hibernate.dto.PersonDto;
import com.example.DAO.Hibernate.entity.Person;
import com.example.DAO.Hibernate.repository.PersonRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Scope("request")
@Service
public class PersonService {
    @PersistenceContext
    private EntityManager entityManager;

    private PersonRepository personRepository;
    public PersonService(PersonRepository personRepository){
       this.personRepository = personRepository;
    }


    public List<PersonDto> getPersonsByCity(String city) {
        try (var session = entityManager.unwrap(Session.class)) {
            var entities =
                    session.createSelectionQuery("where cityOfLiving like :city", Person.class)
                            .setParameter("city", city)
                            .getResultList();

            var result = entities.stream()
                    .map(x -> new PersonDto(
                            x.getId().getName(),
                            x.getId().getSurname(),
                            x.getId().getAge(),
                            x.getPhoneNumber(),
                            x.getCityOfLiving()))
                    .toList();

            return result;

        }
    }
}
