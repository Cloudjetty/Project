package com.example.test.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class City {


    private String name;
    private String countryName;
    private boolean isCapital;
    private int population;

    public City(String name, String countryName, boolean isCapital, int population) {
        this.name = name;
        this.countryName = countryName;
        this.isCapital = isCapital;
        this.population = population;
    }

    public City() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public boolean getIsCapital() {
        return isCapital;
    }

    public void setCapital(boolean capital) {
        isCapital = capital;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
