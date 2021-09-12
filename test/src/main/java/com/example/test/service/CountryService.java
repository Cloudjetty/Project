package com.example.test.service;


import com.example.test.dao.CountryDAO;
import com.example.test.domain.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import java.util.List;

@RequiredArgsConstructor
@Service
public class CountryService {

    @Autowired
    private CountryDAO countryDAO;

    public List<Country> getAll(){

        return countryDAO.getAll();
    }

    public List<Country> getByName(String name){

        return countryDAO.getByName(name);
    }


    /*public List<String> getAllCities(String name){

        return countryDAO.getAllCities(name);
    }
*/
    public void addCountry(Country country){

        countryDAO.addCountry(country);
    }

    public void deleteCountry(int id){

        countryDAO.deleteCountry(id);
    }



}
