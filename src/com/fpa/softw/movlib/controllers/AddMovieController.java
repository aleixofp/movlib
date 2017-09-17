package com.fpa.softw.movlib.controllers;

import com.fpa.softw.movlib.dao.MovieDAO;
import com.fpa.softw.movlib.dao.UserDAO;
import com.fpa.softw.movlib.main.LoggedInUser;
import com.fpa.softw.movlib.main.Main;
import com.fpa.softw.movlib.models.Movie;
import com.fpa.softw.movlib.models.User;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.time.DateTimeException;
import java.util.Calendar;

public class AddMovieController {

    @FXML
    private CheckBox cbWatched, cbRecommends;

    @FXML
    private TextField txtMovieTitle, txtWatchedYear, txtUserRating;

    @FXML
    private void onWatchedClick(){
        this.cbRecommends.setVisible(!this.cbRecommends.isVisible());
        this.txtWatchedYear.setVisible(!this.txtWatchedYear.isVisible());
    }

    @FXML
    private void add(){

        String title = this.txtMovieTitle.getText();

        double userRating;
        try {
            userRating = Double.parseDouble(this.txtUserRating.getText());
        } catch (NumberFormatException e){
            userRating = 0;
        }

        int watchedYear = Integer.parseInt(this.txtWatchedYear.getText());
        boolean watched = this.cbWatched.isSelected();
        boolean recommends = this.cbRecommends.isSelected();
        int userId = LoggedInUser.getLoggedInUser().getId();


        if(title.trim().equals("") || userRating <= 0 || (watchedYear > 2017 || watchedYear <= 0)){
            new Alert(Alert.AlertType.WARNING, "Error when adding movie. Please verify the informations.").showAndWait();
            return;
        }

        MovieDAO movieDAO = new MovieDAO();

        if (movieDAO.addMovie(new Movie(title, userRating, watchedYear, watched, recommends, userId))){
            new Alert(Alert.AlertType.INFORMATION, "Movie added successfully!").showAndWait();
            Main.changeScene("lobby.fxml");
        }


    }


}
