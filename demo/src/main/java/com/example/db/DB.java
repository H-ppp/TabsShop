package com.example.db;


import com.example.exeption.MyExeption;
import com.example.model.Tabs;
import com.example.model.UserCredential;
import com.example.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DB implements IDB {

    private String url;
    private String user;
    private String password;

    public DB(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }


    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }


    @Override
    public Optional<User> createUser(UserCredential userCredential) throws MyExeption {

        String insertUser = "INSERT INTO users (login, password) VALUES (?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement userStatement = connection.prepareStatement(insertUser)) {

            userStatement.setString(1, userCredential.getLogin());
            userStatement.setString(2, userCredential.getPassword());
            userStatement.executeUpdate();

        } catch (SQLException e) {
            throw new MyExeption("Подключение к базе не произошло", 101);
        }

        return readUser(userCredential);
    }


    @Override
    public Optional<User> readUser(UserCredential userCredential) throws MyExeption {

        String selectUser = "SELECT * FROM users WHERE login = ? AND password = ?";

        try (Connection connection = getConnection();
             PreparedStatement userStatement = connection.prepareStatement(selectUser)) {

            userStatement.setString(1, userCredential.getLogin());
            userStatement.setString(2, userCredential.getPassword());

            try (ResultSet resultSet = userStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(new User(resultSet.getInt("id"), resultSet.getString("login"), resultSet.getString("password")));

                } else {
                    throw new MyExeption("Пользователь не найден в базе", 202);
                }
            }

        } catch (SQLException e) {
            throw new MyExeption("Подключение к базе не произошло", 101);
        }
    }


    @Override
    public void updateUser(User user) throws MyExeption {

        String insertUserString = "INSERT INTO user_strings (user_id, string_value, cost) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement userStringStatement = connection.prepareStatement(insertUserString)) {

            user.getTabs().forEach(tabs1 -> {
                try {
                    userStringStatement.setInt(1, user.getId());
                    userStringStatement.setString(2, tabs1.getName());
                    userStringStatement.setFloat(3, tabs1.getCost());
                } catch (SQLException ignored) { //todo?
                }
            });

        } catch (SQLException e) {
            throw new MyExeption("Подключение к базе не произошло", 101);
        }
    }


    @Override
    public Optional<List<Tabs>> readUserTabs(User user) throws MyExeption{

        String selectUserString = "SELECT string_value, cost  FROM user_strings WHERE user_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement userStringStatement = connection.prepareStatement(selectUserString)) {

            userStringStatement.setInt(1, user.getId());
            try (ResultSet resultSet = userStringStatement.executeQuery()) {

                List<Tabs> tabs = new ArrayList<>();
                while (resultSet.next()) {
                    tabs.add(new Tabs(resultSet.getString("string_value"), resultSet.getFloat("cost")));
                }
                user.setTabs(tabs);
                return Optional.of(user.getTabs());
            }

        } catch (SQLException e) {
            throw new MyExeption("Подключение к базе не произошло", 101);
        }
    }
}
