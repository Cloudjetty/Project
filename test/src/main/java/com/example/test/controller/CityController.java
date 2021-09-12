package com.example.test.controller;

import com.example.test.domain.City;
import com.example.test.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public List<City> findAll(){
        return cityService.getAll();
    }

    @GetMapping("/by-name")
    public List<City> findByName(String name){
        return cityService.getByName(name);
    }

    @PostMapping
    public void addCity(@RequestBody City city){

        cityService.addCity(city);
    }

    @GetMapping("/GetByCountryNameAllCities")
    public List<String> findByNameAllCities(String name){
        return cityService.findCities(name);
    }

    @GetMapping("/capital")
    public String findCapital(String name){
        return cityService.getCapital(name);
    }


    @DeleteMapping("/(id)")
    public void deleteCity(@PathVariable int id){
        cityService.deleteCity(id);
    }



}
