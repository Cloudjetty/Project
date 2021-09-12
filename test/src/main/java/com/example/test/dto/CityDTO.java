package com.example.test.dto;

public class CityDTO {

    private String name;
    private String countryName;
    private boolean isCapital;
    private int population;

    public CityDTO(String name, String countryName, boolean isCapital, int population) {
        this.name = name;
        this.countryName = countryName;
        this.isCapital = isCapital;
        this.population = population;
    }

    public CityDTO() {
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

    public boolean isCapital() {
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
