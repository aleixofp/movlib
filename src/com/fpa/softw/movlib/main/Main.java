package com.fpa.softw.movlib.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage pStage) throws Exception{

        primaryStage = pStage;
        primaryStage.setTitle("Movlib");

        changeScene("menu.fxml");

    }

    public static void changeScene(String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("../resources/" + fxmlFile));

            primaryStage.setScene(new Scene(root, 640, 480));
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void changeScene(String fxmlFile, int width, int height) {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("../resources/" + fxmlFile));

            primaryStage.setScene(new Scene(root, width, height));
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Stage getPrimaryStage(){
        return primaryStage;
    }

    public static void setPrimaryStage(Stage newPrimaryStage){
        primaryStage = newPrimaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
