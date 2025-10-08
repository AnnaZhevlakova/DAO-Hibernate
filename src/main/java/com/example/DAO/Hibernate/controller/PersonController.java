package com.example.DAO.Hibernate.controller;

import com.example.DAO.Hibernate.repository.PersonRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Scope("request")
@RestController
@RequestMapping("/persons")
public class PersonController {
 private PersonRepository personRepository;

    public PersonController(PersonRepository personRepository){
        this.personRepository = personRepository;

    }

    @GetMapping("/by-city")
    public ResponseEntity<?> fetchCityByСlientName(String city) throws Exception {
        var result = personRepository.getPersonsByCity(city);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
