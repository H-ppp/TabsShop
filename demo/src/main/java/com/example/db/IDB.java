package com.example.db;

import com.example.exeption.MyExeption;
import com.example.model.Tabs;
import com.example.model.UserCredential;
import com.example.model.User;
import javafx.scene.control.Tab;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IDB {
    Optional<User> createUser(UserCredential userCredential)  throws MyExeption;

    void updateUser(User user) throws MyExeption;

    Optional<User> readUser(UserCredential userCredential) throws MyExeption;

    Optional<List<Tabs>> readUserTabs(User user) throws MyExeption;


}
