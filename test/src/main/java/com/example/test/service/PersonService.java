package com.example.test.service;

import com.example.test.dao.PersonDAO;
import com.example.test.domain.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    @Autowired
    private PersonDAO personDAO;

    public List<Person> getAll(){

        return personDAO.getAll();
    }

    public List<Person> getByName(String name){

        return personDAO.getByName(name);
    }

    public void addPerson(Person person){

        personDAO.addPerson(person);
    }


    public List<Person> getPersons(String name){

        return personDAO.getPeople(name);
    }



    public void deletePerson(int id){

        personDAO.deletePerson(id);
    }

}
