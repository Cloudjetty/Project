package com.example.test.dao;

import com.example.test.domain.City;
import com.example.test.domain.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CountryDAO {

    public static final String DRIVER = "org.h2.Driver";
    public static final String URL = "jdbc:h2:./test";
    public static final String USERNAME = "sa";
    public static final String PASSWORD = "";

    public static final String SELECT_ALL = "select * from country";
    public static final String SELECT_BY_NAME = "select * from country where name like ? ";
    public static final String SELECT_ALL_CITIES_BY_COUNTRY_NAME = "select a.name from city as a, country as b where " +
            "a.countryname = b.name and a.countryname = ? group by a.name; ";
    public static final String INSERT = "insert into country(population,continent,name,income) values(?,?,?,?)";
    public static final String DELETE_BY_ID = "delete from country where id = ?";


    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private PreparedStatement preparedStatement2 = null;
    private ResultSet resultSet = null;


    public CountryDAO() {
        loadDriver();
    }




    public List<Country> getAll() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Country> result = new ArrayList<>();

        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Country country = getCountryFromResultSet();
            result.add(country);
        }

        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    public List<Country> getByName(String name) {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
           preparedStatement = connection.prepareStatement(SELECT_BY_NAME);

           preparedStatement.setString(1,name);
           resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Country> result = new ArrayList<>();

        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Country country = getCountryFromResultSet();
            result.add(country);
        }

        try {
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

/*
    public List<String> getAllCities(String name) {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(SELECT_ALL_CITIES_BY_COUNTRY_NAME);

            preparedStatement.setString(1,name);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<String> result = new ArrayList<>();

        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            City city = getCityFromResultSet();
            result.add(city.getName());
        }

        try {
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

*/







    public void addCountry(Country country) {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT);

            //    for (int i = 0; i < 100; i++) {
            preparedStatement.setInt(1, country.getPopulation());
            preparedStatement.setString(2, country.getContinent());
            preparedStatement.setString(3, country.getName());
            preparedStatement.setDouble(4, country.getIncome());

            // throw new SQLException();
            preparedStatement.execute();



            // throw new SQLException();

//            preparedStatement.execute();
            //   }

            //      preparedStatement.executeBatch();
//
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void deleteCountry(int id) {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_BY_ID);

            //    for (int i = 0; i < 100; i++) {
            preparedStatement.setInt(1, id);

            // throw new SQLException();
            preparedStatement.execute();



            // throw new SQLException();

//            preparedStatement.execute();
            //   }

            //      preparedStatement.executeBatch();
//
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }





    private Country getCountryFromResultSet() {
        int id = 0;
        int population = 0;
        String continent = null;
        String name = null;
        double income = 0;
        try {
            id = resultSet.getInt("id");
            population = resultSet.getInt("population");
            continent = resultSet.getString("continent");
            name = resultSet.getString("name");
            income = resultSet.getDouble("income");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Country(id, population, continent, name,income);
    }


/*    private City getCityFromResultSet() {
        String name = null;
        String countryName = null;
        boolean isCapital = false;
        int population = 0;
        try {
            name = resultSet.getString("name");
            countryName = resultSet.getString("countryname");
            isCapital = resultSet.getBoolean("iscapital");
            population = resultSet.getInt("population");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new City(name,countryName,isCapital,population);
    }*/

    private void loadDriver() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
