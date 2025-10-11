package com.example.DAO.Hibernate.controller;

import com.example.DAO.Hibernate.dto.PersonDto;

import com.example.DAO.Hibernate.dto.PersonIdDto;
import com.example.DAO.Hibernate.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/by-age")
    public ResponseEntity<?> fetchCityByAge(int age) throws Exception {
        var result = personService.getByAgeLessThanOrderByIdAgeAsc(age);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createPerson(@Valid @RequestBody PersonDto personDto){
        var personId = personService.addPerson(personDto);
        return new ResponseEntity<>(personId,HttpStatus.CREATED);

    }

    @PutMapping
    public ResponseEntity<?> updatePerson(@Valid @RequestBody PersonDto personDto){
        var result = personService.updatePerson(personDto);
        return new ResponseEntity<>(result,HttpStatus.OK);

    }

    @DeleteMapping
    public ResponseEntity<?> deletePerson(@Valid @RequestBody PersonIdDto personIdDto){
        var result = personService.deletePerson(personIdDto);
        return new ResponseEntity<>(result,HttpStatus.OK);

    }



}
