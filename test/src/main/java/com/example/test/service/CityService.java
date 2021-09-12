package com.example.test.service;


import com.example.test.dao.CityDAO;
import com.example.test.domain.City;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    @Autowired
    private CityDAO cityDAO;

    public List<City> getAll(){

        return cityDAO.getAll();
    }

    public List<String> findCities(String name){

        return cityDAO.getAllCities(name);
    }


    public List<City> getByName(String name){

        return cityDAO.getByName(name);
    }

    public String getCapital(String name){

        return cityDAO.getCapital(name);
    }


    public void addCity(City city){

        cityDAO.addCity(city);
    }

    public void deleteCity(int id){

        cityDAO.deleteCity(id);
    }



}
