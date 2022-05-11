package com.horarios.horariosapp;

import com.horarios.horariosapp.data.*;
import com.horarios.horariosapp.repository.Dao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Label totalRoomsText;
    @FXML
    private Label totalTimesText;
    @FXML
    private Label totalTeachersText;
    @FXML
    private Label totalModulesText;
    @FXML
    private Label totalGoupsText;

    public void onEditRoomsButtonClick(ActionEvent actionEvent) throws Exception {
        handleOnCLick(actionEvent, "rooms_view.fxml");
    }

    public void onEditTimesButtonClick(ActionEvent actionEvent) throws Exception{
        handleOnCLick(actionEvent, "times_view.fxml");
    }

    public void onEditTeachersButtonClick(ActionEvent actionEvent) throws Exception{
        handleOnCLick(actionEvent, "teachers_view.fxml");
    }

    public void onEditModulesButtonClick(ActionEvent actionEvent) throws Exception {
        handleOnCLick(actionEvent, "modules_view.fxml");
    }

    public void onEditGroupsButtonClick(ActionEvent actionEvent) throws Exception {
        handleOnCLick(actionEvent, "groups_view.fxml");
    }

    public void onCreateTimesButtonClick(ActionEvent actionEvent) throws Exception {
        handleOnCLick(actionEvent, "times_result_view.fxml");
    }

    private void handleOnCLick(ActionEvent actionEvent, String resource) throws Exception {
        Parent window3;
        window3 = FXMLLoader.load(getClass().getResource(resource));

        Scene newScene;
        newScene = new Scene(window3);

        Stage mainWindow;
        mainWindow = (Stage)  ((Node)actionEvent.getSource()).getScene().getWindow();

        mainWindow.setScene(newScene);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Dao dao = new Dao();
        ArrayList<Aula> rooms = dao.getAllRooms();
        ArrayList<Horario> times = dao.getAllTimes();
        ArrayList<Profesor> teachers = dao.getAllTeachers();
        ArrayList<Modulo> modules = dao.getAllModules();
        ArrayList<Grupo> groups = dao.getAllGroups();

        if (rooms != null) {
            totalRoomsText.setText(String.valueOf(rooms.size()));
        }

        if (times != null) {
            totalTimesText.setText(String.valueOf(times.size()));
        }

        if (teachers != null) {
            totalTeachersText.setText(String.valueOf(teachers.size()));
        }

        if (modules != null) {
            totalModulesText.setText(String.valueOf(modules.size()));
        }

        if (groups != null) {
            totalGoupsText.setText(String.valueOf(groups.size()));
        }
    }
}