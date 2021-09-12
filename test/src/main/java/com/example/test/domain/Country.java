package com.example.test.domain;


import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@Builder
public class Country implements Serializable {


    private int id;
    private int population;
    private String continent;
    private String name;
    private double income;


    public Country(int id, int population, String continent, String name, double income) {
        this.id = id;
        this.population = population;
        this.continent = continent;
        this.name = name;
        this.income = income;
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

    public Country() {
    }

   // create table country(id int primary key auto_increment, population int not null, continent varchar(255) not null, name varchar(255) not null, income double not null);
    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", population=" + population +
                ", continent='" + continent + '\'' +
                ", name='" + name + '\'' +
                ", income=" + income +
                '}';
    }

}
