package com.example.test.dao;

import com.example.test.domain.City;
import com.example.test.domain.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class PersonDAO {

    public static final String DRIVER = "org.h2.Driver";
    public static final String URL = "jdbc:h2:./test";
    public static final String USERNAME = "sa";
    public static final String PASSWORD = "";

    public static final String SELECT_ALL = "select * from person";
    public static final String SELECT_BY_NAME = "select * from person where name like ? ";
    //public static final String SELECT_ALL_CITIES_BY_Person_NAME = "select * from person where name like ? ";
    public static final String INSERT = "insert into person(name, surname, fathername, city,sex,year) " +
            "values(?,?,?,?,?,?)";
    public static final String SELECT_ALL_PERSONS_BY_CITY_NAME = "select a.name,a.surname,a.fathername,a.city,a.sex," +
            "a.year from person as a, city as b where a.city = b.name and b.name = ?;";
    public static final String DELETE_BY_ID = "delete from person where id = ?";


    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private PreparedStatement preparedStatement2 = null;
    private ResultSet resultSet = null;



    public PersonDAO() {

        loadDriver();
    }




    public List<Person> getAll() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Person> result = new ArrayList<>();

        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Person person = getPersonFromResultSet();
            result.add(person);
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


    public List<Person> getByName(String name) {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(SELECT_BY_NAME);

            preparedStatement.setString(1,name);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Person> result = new ArrayList<>();

        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Person person = getPersonFromResultSet();
            result.add(person);
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



    public void addPerson(Person person) {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT);

            //    for (int i = 0; i < 100; i++) {
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getSurname());
            preparedStatement.setString(3, person.getPatronymic());
            preparedStatement.setString(4, person.getCity());
            preparedStatement.setString(5, person.getSex());
            preparedStatement.setInt(6, person.getYear());

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


    public void deletePerson(int id) {
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



    public List<Person> getPeople(String name) {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(SELECT_ALL_PERSONS_BY_CITY_NAME);

            preparedStatement.setString(1,name);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Person> result = new ArrayList<>();

        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Person person = getPersonFromResultSet();
            result.add(person);
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








    private Person getPersonFromResultSet() {
        String name = null;
        String surname = null;
        String patronymic = null;
        int year = 0;
        String city = null;
        String sex = null;
        try {
            name = resultSet.getString("name");
            surname = resultSet.getString("surname");
            patronymic = resultSet.getString("fathername");
            year = resultSet.getInt("year");
            city = resultSet.getString("city");
            sex = resultSet.getString("sex");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Person(name,surname,patronymic,year,city,sex);
    }












    private void loadDriver() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



}
