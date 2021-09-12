package com.example.test.controller;


import com.example.test.domain.Country;
import com.example.test.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/GetAll")
    public List<Country> findAll(){
        return countryService.getAll();
    }

    @GetMapping("/GetByName")
    public List<Country> findByName(String name){
        return countryService.getByName(name);
    }

    /*@GetMapping("/GetCountryNameAllCities")
    public List<String> findByNameAllCities(String name){
        return countryService.getAllCities(name);
    }*/

    @PostMapping("/addCountry")
    public void addCountry(@RequestBody Country country){
        countryService.addCountry(country);
    }

    @DeleteMapping("/deleteCountry")
    public void deleteCountry(int id){
        countryService.deleteCountry(id);
    }



}
