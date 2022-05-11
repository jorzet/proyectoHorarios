package com.horarios.horariosapp.controllers;

import com.horarios.horariosapp.Application;
import com.horarios.horariosapp.data.Horario;
import com.horarios.horariosapp.repository.Dao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TimesViewController implements Initializable {

    @FXML
    private Spinner daySpinner;

    @FXML
    private Spinner hoursSpinner;

    @FXML
    private ListView currentTimesListView;

    ObservableList<String> days = FXCollections.observableArrayList(//
            "Lunes", "Martes", "Miercoles", "Jueves", //
            "Viernes");

    ObservableList<String> hours = FXCollections.observableArrayList(//
            "7:00 - 8:00", "8:00 - 9:00", "9:00 - 10:00", "10:00 - 11:00", //
            "11:00 - 12:00", "12:00 - 13:00", "13:00 - 14:00", "14:00 - 15:00",
            "15:00 - 16:00", "16:00 - 17:00", "17:00 - 18:00", "18:00 - 19:00");

    public void onBackButtonClick(ActionEvent actionEvent) throws Exception{
        Parent window3;
        window3 = FXMLLoader.load(Application.class.getResource("times_creator_view.fxml"));

        Scene newScene;
        newScene = new Scene(window3);

        Stage mainWindow;
        mainWindow = (Stage)  ((Node)actionEvent.getSource()).getScene().getWindow();

        mainWindow.setScene(newScene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<String> daysValueFactory =
                new SpinnerValueFactory.ListSpinnerValueFactory<String>(days);
        SpinnerValueFactory<String> hoursValueFactory =
                new SpinnerValueFactory.ListSpinnerValueFactory<String>(hours);

        // Default value
        daysValueFactory.setValue(days.get(0));
        hoursValueFactory.setValue(hours.get(0));

        daySpinner.setValueFactory(daysValueFactory);
        hoursSpinner.setValueFactory(hoursValueFactory);

        currentTimesListView.setCellFactory(TextFieldListCell.forListView());
        currentTimesListView.setEditable(false);

        Dao dao = new Dao();
        ArrayList<Horario> times = dao.getAllTimes();

        if (times != null) {
            for (Horario time : times) {
                currentTimesListView.getItems().add("Dia: " + time.getDay() + " hora: " + time.getTimeslot());
            }
        }
    }

    public void onAddButtonClick(ActionEvent actionEvent) {
        currentTimesListView.setCellFactory(TextFieldListCell.forListView());
        currentTimesListView.setEditable(false);
        currentTimesListView.getItems().add("Dia: " + daySpinner.getValue() + " hora: " + hoursSpinner.getValue());

        Horario time = new Horario();
        time.setDay(daySpinner.getValue().toString());
        time.setTimeslot(hoursSpinner.getValue().toString());

        Dao dao = new Dao();
        String result = dao.insertTime(time);

        System.out.println(result);

        Stage mainWindow;
        mainWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        mainWindow.show();
    }
}
