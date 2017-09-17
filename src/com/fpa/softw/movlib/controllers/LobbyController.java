package com.fpa.softw.movlib.controllers;

import com.fpa.softw.movlib.main.LoggedInUser;
import com.fpa.softw.movlib.models.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class LobbyController {

    private User user;

    @FXML
    private Label lblName, lblUsername;

    @FXML
    private ListView lvMovies;


    @FXML
    private void initialize(){
        this.user = LoggedInUser.getLoggedInUser();

        this.lblName.setText(this.user.getName());
        this.lblUsername.setText(this.user.getUsername());


    }



}
