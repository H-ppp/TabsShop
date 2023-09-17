package com.example.core;

import com.example.exeption.MyExeption;
import com.example.model.UserCredential;
import com.example.userAction.UserAction;
import com.example.userAction.UserActionImp;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class HelloController {

    private UserCredential userCredential = new UserCredential();

    private UserAction userAction;

    public HelloController(UserAction userActionImp) {
        this.userAction = userActionImp;
    }

    public HelloController() {

    }

    @FXML
    private Hyperlink si_forgotPassword;

    @FXML
    private Button si_logginButton;

    @FXML
    private AnchorPane si_loginForm;

    @FXML
    private PasswordField si_password;

    @FXML
    private TextField si_username;

    @FXML
    private Button side_createButton;

    @FXML
    private AnchorPane side_form;

    @FXML
    private Button side_alreadyHave;

    @FXML
    private PasswordField su_password;

    @FXML
    private Button su_signUpButton;

    @FXML
    private AnchorPane su_signUpForm;

    @FXML
    private TextField su_username;

    TranslateTransition slider = new TranslateTransition();


    public void loginButtonClicked(ActionEvent event) {
        if (event.getSource() == si_logginButton) {

            userCredential.setLogin(si_username.getText());
            userCredential.setPassword(si_password.getText());


            //todo обработка вывода, что пользователя такого нет или переход к новому окну
            try {
                userAction.readUser(userCredential);
            } catch (MyExeption e) {
                System.err.println(e.getMessage() + " " + e.getErrorCode());
            }

        }
    }

    public void signUpButtonClicked(ActionEvent event) {
        if(event.getSource() == su_signUpButton){

            userCredential.setLogin(su_username.getText());
            userCredential.setPassword(su_password.getText());


            try {
                userAction.createUser(userCredential);
            } catch (MyExeption e) {
                System.err.println(e.getMessage() + " " + e.getErrorCode());
            }
        }
    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == side_createButton) {
            slider.setNode(side_form);
            slider.setToX(300);
            slider.setDuration(Duration.seconds(.5));

            slider.setOnFinished((ActionEvent e) -> {
                side_alreadyHave.setVisible(true);
                side_createButton.setVisible(false);
            });

            slider.play();
        } else if (event.getSource() == side_alreadyHave) {
            slider.setNode(side_form);
            slider.setToX(0);
            slider.setDuration(Duration.seconds(.5));

            slider.setOnFinished((ActionEvent e) -> {
                side_alreadyHave.setVisible(false);
                side_createButton.setVisible(true);
            });

            slider.play();
        }
    }

    public void openMainScene() {

    }

}

