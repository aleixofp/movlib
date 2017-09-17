package com.fpa.softw.movlib.controllers;

import com.fpa.softw.movlib.dao.UserDAO;
import com.fpa.softw.movlib.main.LoggedInUser;
import com.fpa.softw.movlib.main.Main;
import com.fpa.softw.movlib.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MenuController {

    @FXML
    private Button btnLogin, btnRegister;

    @FXML
    private TextField txtUsername, txtPassword;

    @FXML
    private void login() throws IOException {

        String username = this.txtUsername.getText();
        String password = this.txtPassword.getText();

        if(username.trim().equals("") || password.trim().equals("")){
            this.txtUsername.setPromptText("Invalid credentials");
            this.txtPassword.setPromptText("Invalid credentials");
            return;
        }

        UserDAO userDAO = new UserDAO();

        User user = userDAO.findUser(username);

        System.out.println(user);

        if (user == null){
            System.out.println("user is null");
            new Alert(Alert.AlertType.WARNING, "User does not exist!").showAndWait();
        } else {
            System.out.println("user is not null");
            if(user.getPassword().equals(password)){
                System.out.println("password matches");

                LoggedInUser.setProfile(user);

                Main.changeScene("lobby.fxml");
            } else {
                System.out.println("password does not match");
                new Alert(Alert.AlertType.WARNING, "Did you forget your password?").showAndWait();
            }

        }

    }

    @FXML
    private void register() throws IOException {
        Main.changeScene("register.fxml");
    }

}
