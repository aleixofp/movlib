package com.fpa.softw.movlib.controllers;

import com.fpa.softw.movlib.dao.MovieDAO;
import com.fpa.softw.movlib.main.LoggedInUser;
import com.fpa.softw.movlib.main.Main;
import com.fpa.softw.movlib.misc.AlertMisc;
import com.fpa.softw.movlib.models.Movie;
import com.fpa.softw.movlib.models.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class LobbyController {

    @FXML
    private Label lblName, lblUsername;

    @FXML
    private ListView lvMovies;

    @FXML
    private Button btnAddMovie, btnEditMovie, btnRemoveMovie;

    private Movie selectedMovie;
    private MovieDAO movieDAO;
    private User user;

    private AlertMisc alertMisc;

    @FXML
    private void initialize(){

        this.alertMisc = new AlertMisc();

        this.movieDAO = new MovieDAO();

        this.user = LoggedInUser.getLoggedInUser();

        this.lblName.setText(this.user.getName());
        this.lblUsername.setText(this.user.getUsername());

        //noinspection unchecked
        this.lvMovies.setItems(this.getUpdatedMovieList());

        //noinspection unchecked
        this.lvMovies.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(btnEditMovie.isDisable() || btnRemoveMovie.isDisable()){
                btnEditMovie.setDisable(false);
                btnRemoveMovie.setDisable(false);

            }
            selectedMovie = (Movie)newValue;
        });
    }

    @FXML
    private void logoff(){
        LoggedInUser.setProfile(null);
        Main.changeScene("menu.fxml");
    }

    @FXML
    private void addOrEditMovie(Event event) throws IOException {
        Button clickedButton = (Button) event.getSource();
        System.out.println(clickedButton.getId().substring(3));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/add_or_edit_movie.fxml"));

        Parent parent = loader.load();
        AddOrEditMovieController addOrEditMovieController = loader.getController();

        addOrEditMovieController.retrieveSceneType(clickedButton.getId().substring(3), this.selectedMovie);

        Main.getPrimaryStage().setScene(new Scene(parent));
        Main.getPrimaryStage().show();
    }

    @FXML
    private void removeMovie(){
        Optional<ButtonType> choice = this.alertMisc.setupAndShowAlertWithButtons(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete '" + this.selectedMovie.getTitle() + '?', ButtonType.YES, ButtonType.NO);

        if (choice.isPresent()){

            if(choice.get().getButtonData().isDefaultButton()){
                this.movieDAO.removeMovie(this.selectedMovie);
                this.alertMisc.setupAndShowAlert(Alert.AlertType.INFORMATION, "Movie removed.");

                this.lvMovies.setItems(this.getUpdatedMovieList());
            }

        }

    }

    private ObservableList<Movie> getUpdatedMovieList(){
        ObservableList<Movie> movieList = FXCollections.observableArrayList();
        movieList.addAll(this.movieDAO.listMovieByUserId(this.user.getId()));
        return movieList;
    }



}
