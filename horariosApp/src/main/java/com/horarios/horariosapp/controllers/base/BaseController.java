package com.horarios.horariosapp.controllers.base;

import com.horarios.horariosapp.repository.Dao;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

public abstract class BaseController implements Initializable {

    protected Dao dao = new Dao();
    protected void showErrorAlertDialog(String error) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setHeaderText(error);
        alert.show();
    }

    protected void showWarningAlertDialog(String warning) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType(Alert.AlertType.WARNING);
        alert.setHeaderText(warning);
        alert.show();
    }

    protected boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
