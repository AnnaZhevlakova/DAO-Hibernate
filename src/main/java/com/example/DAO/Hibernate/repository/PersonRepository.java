package com.example.DAO.Hibernate.repository;

import com.example.DAO.Hibernate.entity.Person;
import com.example.DAO.Hibernate.entity.PersonId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
      List<Person> findByCityOfLiving(String city);

     Person  findById(PersonId personId);
     void deleteById(PersonId personId);


      Optional<Person> findByIdNameAndIdSurname(String name, String surname);

}
