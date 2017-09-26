package com.fpa.softw.movlib.controllers;

import com.fpa.softw.movlib.dao.MovieDAO;
import com.fpa.softw.movlib.dao.UserDAO;
import com.fpa.softw.movlib.main.LoggedInUser;
import com.fpa.softw.movlib.main.Main;
import com.fpa.softw.movlib.models.Movie;
import com.fpa.softw.movlib.models.User;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.time.DateTimeException;
import java.util.Calendar;

public class AddOrEditMovieController {

    private String sceneTypeId;
    private Movie selectedMovie;

    @FXML
    private AnchorPane parentPane;

    @FXML
    private Label lblSceneTitle;

    @FXML
    private CheckBox cbWatched, cbRecommends;

    @FXML
    private TextField txtMovieTitle, txtWatchedYear, txtUserRating;

    @FXML
    private Button btnAddUpdate;

    @FXML
    private void initialize(){
        System.out.println(this.sceneTypeId);
        if (sceneTypeId == null) return;



    }

    @FXML
    private void onWatchedClick(){
        this.cbRecommends.setVisible(!this.cbRecommends.isVisible());
        this.txtWatchedYear.setVisible(!this.txtWatchedYear.isVisible());
        if (this.cbRecommends.isVisible()){
            this.cbRecommends.setSelected(false);
            this.txtWatchedYear.setText("0");
        }
    }

    @FXML
    private void back(){
        Main.changeScene("lobby.fxml");
    }

    @FXML
    private void addUpdate(){

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
            new Alert(Alert.AlertType.WARNING, "Error when adding/updating movie. Please verify the informations.").showAndWait();
            return;
        }

        MovieDAO movieDAO = new MovieDAO();

        if(this.sceneTypeId.equals("EditMovie")){


            int movieId = movieDAO.findMovie(userId, this.selectedMovie.getTitle()).getId();
            movieDAO.updateMovie(new Movie(title, userRating, watchedYear, watched, recommends, userId), userId, movieId);

            new Alert(Alert.AlertType.INFORMATION, "Movie updated!").showAndWait();

        } else {

            if (movieDAO.addMovie(new Movie(title, userRating, watchedYear, watched, recommends, userId))) {
                new Alert(Alert.AlertType.INFORMATION, "Movie added successfully!").showAndWait();
                Main.changeScene("lobby.fxml");
            }
        }


    }

    public void retrieveSceneType(String id, Movie movie){
        System.out.println("Retrieve Scene type called... " + id +  " | " + movie);
        this.sceneTypeId = id;
        this.selectedMovie = movie;

        if (sceneTypeId.equals("EditMovie")){
            this.lblSceneTitle.setText("UPDATE YOUR MOVIE");
            this.txtMovieTitle.setText(this.selectedMovie.getTitle());
            this.txtUserRating.setText(String.valueOf(this.selectedMovie.getUserRating()));
            this.btnAddUpdate.setText("Update");

            this.cbWatched.setSelected(this.selectedMovie.isWatched());
            this.cbRecommends.setSelected(this.selectedMovie.isRecommends());

            if(this.cbRecommends.isSelected())
                this.txtWatchedYear.setText(String.valueOf(this.selectedMovie.getWatchedYear()));

            this.onWatchedClick();
        }
    }

    public AnchorPane getParentPane(){
        return this.parentPane;
    }

}
