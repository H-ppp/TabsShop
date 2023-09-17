package com.example.userAction;

import com.example.exeption.MyExeption;
import com.example.model.UserCredential;

public interface UserAction {
    public boolean createUser(UserCredential userCredential) throws MyExeption;
    public boolean readUser(UserCredential userCredential) throws MyExeption;
}
