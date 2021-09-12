package com.example.test.dto;

public class CountryDTO {
    private int id;
    private int population;
    private String continent;
    private String name;
    private double income;


    public CountryDTO(int id, int population, String continent, String name, double income) {
        this.id = id;
        this.population = population;
        this.continent = continent;
        this.name = name;
        this.income = income;
    }

    public CountryDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }
}
