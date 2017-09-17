package com.fpa.softw.movlib.controllers;

import com.fpa.softw.movlib.main.LoggedInUser;
import com.fpa.softw.movlib.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LobbyController {

    private User user;

    @FXML
    private Label lblName, lblUsername;

    @FXML
    private void initialize(){
        this.user = LoggedInUser.getLoggedInUser();

        System.out.println(this.user.getUsername());

        this.lblName.setText(this.user.getName());
        this.lblUsername.setText(this.user.getUsername());
    }



}
