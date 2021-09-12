package com.example.test.controller;


import com.example.test.domain.Person;
import com.example.test.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/GetAll")
    public List<Person> findAll(){
        return personService.getAll();
    }

    @GetMapping("/GetByName")
    public List<Person> findByName(String name){
        return personService.getByName(name);
    }

    @PostMapping("/addPerson")
    public void addPerson(@RequestBody Person person){

        personService.addPerson(person);
    }

    @DeleteMapping("/deletePerson")
    public void deletePerson(int id){
        personService.deletePerson(id);
    }

    @GetMapping("/GetByCityNamePersons")
    public List<Person> findPeople(String name){
        return personService.getPersons(name);
    }


}
