package com.example.test.dao;


import com.example.test.domain.City;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CityDAO {


    public static final String DRIVER = "org.h2.Driver";
    public static final String URL = "jdbc:h2:./test";
    public static final String USERNAME = "sa";
    public static final String PASSWORD = "";

    public static final String SELECT_ALL = "select * from city";
    public static final String SELECT_BY_NAME = "select * from city where name like ? ";
    public static final String SELECT_ALL_CITIES_BY_COUNTRY_NAME = "select a.name from city as a, country as b where " +
            "a.countryName = b.name and a.countryName = ?; ";
    public static final String SELECT_COUNTRY_CAPITAL = "select distinct a.name from city as a, country as b where " +
            "a.countryName = b.name and a.countryName = ? and a.iscapital = true; ";
    public static final String INSERT = "insert into city(name, countryname, iscapital, population) " +
            "values(?,?,?,?)";
    public static final String DELETE_BY_ID = "delete from city where id = ?";

    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private PreparedStatement preparedStatement2 = null;
    private ResultSet resultSet = null;


    public CityDAO() {
        loadDriver();
    }





    public List<City> getAll() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<City> result = new ArrayList<>();

        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            City city = getCityFromResultSet();
            result.add(city);
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


    public List<City> getByName(String name) {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(SELECT_BY_NAME);

            preparedStatement.setString(1,name);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<City> result = new ArrayList<>();

        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            City city = getCityFromResultSet();
            result.add(city);
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



    public void addCity(City city) {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT);

            //    for (int i = 0; i < 100; i++) {
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountryName());
            preparedStatement.setBoolean(3, city.getIsCapital());
            preparedStatement.setInt(4, city.getPopulation());


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


    public void deleteCity(int id) {
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





    public String getCapital(String name) {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(SELECT_COUNTRY_CAPITAL);

            preparedStatement.setString(1,name);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String result = null;
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            City city = getCityFromResultSet();
            result = city.getName();
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







    private City getCityFromResultSet() {
        String name = null;
        String countryName = null;
        boolean isCapital = false;
        int population = 0;
        try {
            name = resultSet.getString("name");
            countryName = resultSet.getString("countryName");
            isCapital = resultSet.getBoolean("isCapital");
            population = resultSet.getInt("population");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new City(name,countryName,isCapital,population);
    }




    private void loadDriver() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




}
