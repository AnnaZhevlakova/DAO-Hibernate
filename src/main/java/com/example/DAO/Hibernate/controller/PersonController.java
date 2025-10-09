package com.example.DAO.Hibernate.controller;

import com.example.DAO.Hibernate.service.PersonService;
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
    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;

    }

    @GetMapping("/by-city")
    public ResponseEntity<?> fetchCityByСlientName(String city) throws Exception {
        var result = personService.getPersonsByCity(city);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
