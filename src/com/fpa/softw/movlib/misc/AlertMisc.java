package com.fpa.softw.movlib.misc;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertMisc {

    private Alert alert;

    public void setupAndShowAlert(Alert.AlertType alertType, String message){

        if (this.alert == null) {
            this.alert = new Alert(alertType, message, ButtonType.OK);
        } else {
            this.alert.setAlertType(alertType);
            this.alert.setContentText(message);
        }

        this.alert.showAndWait();

    }

    public Optional<ButtonType> setupAndShowAlertWithButtons(Alert.AlertType alertType, String message, ButtonType... buttons){
        if (this.alert == null) {
            this.alert = new Alert(alertType, message, buttons);
        } else {
            this.alert.setAlertType(alertType);
            this.alert.setContentText(message);
            this.alert.getButtonTypes().addAll(buttons);
        }

        return this.alert.showAndWait();

    }


}
