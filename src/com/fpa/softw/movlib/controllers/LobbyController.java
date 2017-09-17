package com.fpa.softw.movlib.controllers;

import com.fpa.softw.movlib.dao.MovieDAO;
import com.fpa.softw.movlib.main.LoggedInUser;
import com.fpa.softw.movlib.main.Main;
import com.fpa.softw.movlib.models.Movie;
import com.fpa.softw.movlib.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.List;

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

        MovieDAO movieDAO = new MovieDAO();

        List<Movie> userMovieList = movieDAO.listMovieByUserId(this.user.getId());

        for (Movie mv:
             userMovieList) {
            System.out.println(mv.getTitle());
        }

        ObservableList<Movie> movieObservableList = FXCollections.observableArrayList();
        movieObservableList.addAll(userMovieList);

        //noinspection unchecked
        this.lvMovies.setItems(movieObservableList);
    }

    @FXML
    private void addMovie(){
        Main.changeScene("add_movie.fxml");
    }



}
