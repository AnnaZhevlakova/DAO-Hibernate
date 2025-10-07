package com.example.DAO.Hibernate.repository;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PersonRepository(NamedParameterJdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
     public String getPersonsByCity(String city){
        return city;
     }
}
