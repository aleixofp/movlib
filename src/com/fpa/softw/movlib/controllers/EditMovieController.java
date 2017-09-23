package com.fpa.softw.movlib.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class EditMovieController {

    @FXML
    private TextField txtMovieTitle, txtUserRating, txtWatchedYear;

    @FXML
    private CheckBox cbWatched, cbRecommends;

    @FXML
    private void initialize(){
        /*

            FXMLLoader loader = new FXMLLoader(getClass().getResources("edit_movie.fxml"));

            EditMovieController emController = loader.getController();
            emController.populateFieldsWithSelectedMovie(movie);


         */
    }

    @FXML
    private void onWatchedClick(){
        this.cbRecommends.setVisible(!this.cbRecommends.isVisible());
        this.txtWatchedYear.setVisible(!this.txtWatchedYear.isVisible());
    }

}
