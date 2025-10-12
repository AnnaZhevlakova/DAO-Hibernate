package com.example.DAO.Hibernate.service;

import com.example.DAO.Hibernate.dto.PersonDto;
import com.example.DAO.Hibernate.dto.PersonIdDto;
import com.example.DAO.Hibernate.entity.Person;
import com.example.DAO.Hibernate.entity.PersonId;
import com.example.DAO.Hibernate.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Scope("request")
@Service
@Transactional
public class PersonService {

    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public List<PersonDto> getPersonsByCity(String city) {
        var entities = personRepository.findByCityOfLiving(city);
        var result = entities.stream()
                .map(x -> getPersonDtoByPerson(x))
                .toList();

        return result;
    }

    public List<PersonDto> getPersonByAgeLessThanOrderByAgeAsc(int age) {
        var entities = personRepository.findByAgeLessThanOrderByAgeAsc(age);
        var result = entities.stream()
                .map(x -> getPersonDtoByPerson(x))
                .toList();
        return result;
    }

    public List<PersonDto> getPersonByNameAndSurname(String name, String surname) {
        var entity = personRepository.findByNameAndSurname(name, surname);
        var result = entity.stream()
                .map(x -> getPersonDtoByPerson(x))
                .toList();
        return result;
    }


    public PersonIdDto addPerson(PersonDto personDto) {
        var personId = new PersonId(personDto.getName(), personDto.getSurname(), personDto.getAge());
        var person = new Person(personId, personDto.getPhoneNumber(), personDto.getCityOfLiving());
        personRepository.save(person);
        return new PersonIdDto(personDto.getName(), personDto.getSurname(), personDto.getAge());
    }

    public boolean updatePerson(PersonDto personDto) {
        var personId = new PersonId(personDto.getName(), personDto.getSurname(), personDto.getAge());
        var person = personRepository.findById(personId);
        if (person == null) {
            throw new EntityNotFoundException();
        }
        person.setCityOfLiving(personDto.getCityOfLiving());
        person.setPhoneNumber(personDto.getPhoneNumber());
        personRepository.saveAndFlush(person);
        return true;
    }

    public boolean deletePerson(PersonIdDto personIdDto) {
        var personId = new PersonId(personIdDto.getName(), personIdDto.getSurname(), personIdDto.getAge());
        personRepository.deleteById(personId);
        return true;
    }

    private PersonDto getPersonDtoByPerson(Person person) {
        var result = new PersonDto(
                person.getId().getName(),
                person.getId().getSurname(),
                person.getId().getAge(),
                person.getPhoneNumber(),
                person.getCityOfLiving());
        return result;

    }


}
