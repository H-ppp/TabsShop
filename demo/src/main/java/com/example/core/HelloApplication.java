package com.example.core;

import com.example.db.DB;
import com.example.userAction.UserAction;
import com.example.userAction.UserActionImp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        UserAction userAction = new UserActionImp(
                new DB("jdbc:postgresql://localhost:8000/postgres", "postgres", "8000"));
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        HelloController helloController = new HelloController(userAction);


        fxmlLoader.setController(helloController);
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Tabs!");
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}