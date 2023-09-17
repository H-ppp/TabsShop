package com.example.userAction;

import com.example.db.IDB;
import com.example.exeption.MyExeption;
import com.example.model.User;
import com.example.model.UserCredential;


import java.util.Optional;

public class UserActionImp implements UserAction {
    private User user;

    private IDB db;

    public UserActionImp(IDB db) {
        this.db = db;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public boolean readUser(UserCredential userCredential) throws MyExeption {
        Optional<User> dbUser = db.readUser(userCredential);
        if (dbUser.isPresent()) {
            user = dbUser.get();
            System.out.println("id - " + user.getId() + " login - " + user.getLogin() + " password - " + user.getPassword());
            return true;
        } else {
            System.out.println("пользователя нет в системе");
            return false;
        }
    }

    @Override
    public boolean createUser(UserCredential userCredential) throws MyExeption {
        Optional<User> dbUser = db.createUser(userCredential);
        if (dbUser.isPresent()) {
            user = dbUser.get();
            System.out.println("id - " + user.getId() + " login - " + user.getLogin() + " password - " + user.getPassword());
            return true;
        } else {
            System.out.println("неудачная попытка регистрации");
            return false;
        }


    }
}