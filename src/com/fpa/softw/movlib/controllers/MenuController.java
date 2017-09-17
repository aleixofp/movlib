package com.fpa.softw.movlib.controllers;

import com.fpa.softw.movlib.dao.UserDAO;
import com.fpa.softw.movlib.main.LoggedInUser;
import com.fpa.softw.movlib.main.Main;
import com.fpa.softw.movlib.misc.AlertMisc;
import com.fpa.softw.movlib.models.User;
import com.sun.deploy.security.CachedCertificatesHelper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;

import java.awt.*;
import java.io.*;
import java.util.Optional;
import java.util.Properties;

public class MenuController {

    @FXML
    private Button btnLogin, btnRegister;

    @FXML
    private TextField txtUsername, txtPassword;

    @FXML
    private Region regUsers;

    private AlertMisc alertMisc;

    private User user;
    private UserDAO userDAO;

    @FXML
    private void initialize(){
        this.alertMisc = new AlertMisc();
        this.userDAO = new UserDAO();
    }

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

        this.user = userDAO.findUser(username);

        if (this.user == null){
            this.alertMisc.setupAndShowAlert(Alert.AlertType.WARNING, "User does not exist!");
        } else {
            if(this.user.getPassword().equals(password)){

                LoggedInUser.setProfile(this.user);
                Main.changeScene("lobby.fxml");

            } else {
                this.alertMisc.setupAndShowAlert(Alert.AlertType.WARNING, "Did you forget your password?");
            }

        }

    }

    @FXML
    private void register() throws IOException {
        Main.changeScene("register.fxml");
    }

}
