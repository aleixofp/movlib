package com.fpa.softw.movlib.controllers;

import com.fpa.softw.movlib.dao.UserDAO;
import com.fpa.softw.movlib.main.Main;
import com.fpa.softw.movlib.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class RegisterController {

    @FXML
    private TextField txtName, txtUsername, txtPassword, txtConfirmPassword;

    private Alert alert;

    @FXML
    private void initialize(){
        this.alert = new Alert(Alert.AlertType.ERROR);
    }

    @FXML
    private void registerUser(){

        String name = this.txtName.getText();
        String username = this.txtUsername.getText();
        String password = this.txtPassword.getText();
        String confirmPassword = this.txtConfirmPassword.getText();

        if (name.trim().length() > 0 && username.trim().length() > 0 && password.trim().length() >= 6 && confirmPassword.trim().length() >= 6){

            if(password.equals(confirmPassword)) {

                UserDAO userDAO = new UserDAO();

                if (userDAO.findUser(username) == null){

                    userDAO.addUser(new User(name, username, password));

                    this.alert.setAlertType(Alert.AlertType.INFORMATION);
                    this.alert.setContentText("Successful! " + name + ", welcome!");

                    Main.changeScene("menu.fxml");
                } else {
                    this.alert.setContentText("Username " + username + " already exists!");
                }



            } else {
                this.alert.setContentText("Passwords need to match!");

            }

        } else {
            this.alert.setContentText("Invalid Credentials!");
        }

        this.alert.showAndWait();

    }


}
