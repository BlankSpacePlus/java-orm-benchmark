package com.blankspace.orm.dao;

import static com.blankspace.orm.util.OrmHelper.DATABASE_URL;
import static com.blankspace.orm.util.OrmHelper.DRIVER_NAME;
import static com.blankspace.orm.util.OrmHelper.USER_NAME;
import static com.blankspace.orm.util.OrmHelper.USER_PASSWORD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.blankspace.orm.pojo.Person;

public class PersonJdbcDao implements PersonDaoInterface {

    private volatile static PersonDaoInterface daoInstance;

    private PersonJdbcDao() {
    }

    public static PersonDaoInterface getInstance() {
        if (daoInstance == null) {
            synchronized (PersonJdbcDao.class) {
                if (daoInstance == null) {
                    daoInstance = new PersonJdbcDao();
                }
            }
        }
        return daoInstance;
    }

    @Override
    public void addNewPerson(Person person) {
        // 查找数据库驱动
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 编写SQL语句
        String sql = "insert into `person` values (?, ?, ?, ?, ?, ?)";
        // 连接数据库并进行操作
        try (Connection dbConnection = DriverManager.getConnection(DATABASE_URL, USER_NAME, USER_PASSWORD);
             PreparedStatement preparedStatement = dbConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, person.getId());
            preparedStatement.setString(2, person.getName());
            preparedStatement.setString(3, person.getPhone());
            preparedStatement.setString(4, person.getEmail());
            preparedStatement.setString(5, person.getCompany());
            preparedStatement.setString(6, person.getAddress());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePersonById(int personId) {
        // 查找数据库驱动
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 编写SQL语句
        String sql = "delete from `person` where id=?";
        // 连接数据库并进行操作
        try (Connection dbConnection = DriverManager.getConnection(DATABASE_URL, USER_NAME, USER_PASSWORD);
             PreparedStatement preparedStatement = dbConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, personId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateExistedPerson(Person person) {
        // 查找数据库驱动
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 编写SQL语句
        String sql = "update `person` set name=?, phone=?, email=?, company=?, address=? where id=?";
        // 连接数据库并进行操作
        try (Connection dbConnection = DriverManager.getConnection(DATABASE_URL, USER_NAME, USER_PASSWORD);
             PreparedStatement preparedStatement = dbConnection.prepareStatement(sql)) {
            int personId = person.getId();
            String personName = person.getName();
            String personPhone = person.getPhone();
            String personEmail = person.getEmail();
            String personCompany = person.getCompany();
            String personAddress = person.getAddress();
            preparedStatement.setString(1, personName);
            preparedStatement.setString(2, personPhone);
            preparedStatement.setString(3, personEmail);
            preparedStatement.setString(4, personCompany);
            preparedStatement.setString(5, personAddress);
            preparedStatement.setInt(6, personId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Person queryPersonById(int personId) {
        // 查找数据库驱动
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 编写SQL语句
        String sql = "select id, name, phone, email, company, address from `person` where id = ?";
        // 连接数据库并进行操作
        try (Connection dbConnection = DriverManager.getConnection(DATABASE_URL, USER_NAME, USER_PASSWORD);
             PreparedStatement preparedStatement = dbConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, personId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Person person = null;
            if (resultSet.next()) {
                person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setPhone(resultSet.getString("phone"));
                person.setEmail(resultSet.getString("email"));
                person.setCompany(resultSet.getString("company"));
                person.setAddress(resultSet.getString("address"));
            }
            return person;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
