package com.horarios.horariosapp;

import com.horarios.horariosapp.controllers.base.BaseController;
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
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller extends BaseController {
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
    @FXML
    private Label totalModulesGroupsText;

    private boolean hasDataStorage = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Aula> rooms = dao.getAllRooms();
        ArrayList<Horario> times = dao.getAllTimes();
        ArrayList<Profesor> teachers = dao.getAllTeachers();
        ArrayList<Modulo> modules = dao.getAllModules();
        ArrayList<Grupo> groups = dao.getAllGroups();
        ArrayList<GroupModule> groupModules = dao.getAllModulesAndGroups();

        if (rooms != null) totalRoomsText.setText(String.valueOf(rooms.size()));
        if (times != null) totalTimesText.setText(String.valueOf(times.size()));
        if (teachers != null) totalTeachersText.setText(String.valueOf(teachers.size()));
        if (modules != null) totalModulesText.setText(String.valueOf(modules.size()));
        if (groups != null) totalGoupsText.setText(String.valueOf(groups.size()));
        if (groupModules != null) totalModulesGroupsText.setText(String.valueOf(groupModules.size()));

        if (groups != null && !groups.isEmpty() &&
                modules != null && !modules.isEmpty() &&
                teachers != null && !teachers.isEmpty() &&
                times != null &&!times.isEmpty() &&
                rooms != null && !rooms.isEmpty() &&
                groupModules != null && !groupModules.isEmpty()) {
            hasDataStorage = true;
        }
    }

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

    public void onEditModulesGroupsButtonClick(ActionEvent actionEvent) throws Exception{
        handleOnCLick(actionEvent, "match_times_group_view.fxml");
    }

    public void onCreateTimesButtonClick(ActionEvent actionEvent) throws Exception {
        if (hasDataStorage) {
            handleOnCLick(actionEvent, "times_result_view.fxml");
        } else {
            showWarningAlertDialog("Necesita haber datos almacenados para generar horarios");
        }
    }

    private void handleOnCLick(ActionEvent actionEvent, String resource) throws Exception {
        Parent window3 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(resource)));
        Scene newScene = new Scene(window3);
        Stage mainWindow = (Stage)  ((Node)actionEvent.getSource()).getScene().getWindow();

        mainWindow.setScene(newScene);
    }
}