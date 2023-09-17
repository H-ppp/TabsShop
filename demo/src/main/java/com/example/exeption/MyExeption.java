package com.example.exeption;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MyExeption extends Exception{

    private int errorCode;
    private File file = new File("error.txt");
    public MyExeption(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
        logError();
    }

    public int getErrorCode() {
        return errorCode;
    }

    private void logError(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file.getName()))){
            bw.write("Error" + getMessage() + "Code" + getErrorCode() + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + e.getMessage());
        }
    }
}

