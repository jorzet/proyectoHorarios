package com.horarios.horariosapp.controllers;

import com.horarios.horariosapp.Application;
import com.horarios.horariosapp.controllers.base.BaseController;
import com.horarios.horariosapp.data.GroupModule;
import com.horarios.horariosapp.data.Grupo;
import com.horarios.horariosapp.data.Modulo;
import com.horarios.horariosapp.repository.Dao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class MatchTimesGroupViewController extends BaseController {

    @FXML
    private ComboBox modulesComboBox;
    @FXML
    private ComboBox groupsComboBox;
    @FXML
    private ComboBox timesComboBox;
    @FXML
    private ListView groupsModulesResultListView;


    private ArrayList<Grupo> groups;
    private ArrayList<Modulo> modules;

    private ObservableList<String> observableTimes = FXCollections.observableArrayList(
            "1","2","3");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        groups = dao.getAllGroups();
        modules = dao.getAllModules();

        ObservableList<String> observableModules = FXCollections.observableArrayList();
        ObservableList<String> observableGroups= FXCollections.observableArrayList();

        if (modules != null) {
            for (Modulo module : modules) {
                observableModules.add(module.getModuleCode());
            }
        }
        modulesComboBox.getItems().addAll(observableModules);

        if (groups != null) {
            for (Grupo group: groups) {
                observableGroups.add(group.getGroupName());
            }
        }
        groupsComboBox.getItems().addAll(observableGroups);

        timesComboBox.getItems().addAll(observableTimes);

        ArrayList<GroupModule> groupModules = dao.getAllModulesAndGroups();
        for (GroupModule groupModule : groupModules) {
            StringBuffer ids = new StringBuffer();
            ids.append("{");
            ids.append(groupModule.getIdGroup());
            ids.append(",");
            ids.append(groupModule.getIdModule());
            ids.append(",");
            ids.append(groupModule.getIdTeacher());
            ids.append(", ");
            ids.append(groupModule.getTimes());
            ids.append("}");
            groupsModulesResultListView.getItems().add("vinculo: "+ids);
        }

    }

    public void onBackButtonClick(ActionEvent actionEvent) throws Exception {
        Parent window3 = FXMLLoader.load(Objects.requireNonNull(Application.class.getResource("times_creator_view.fxml")));
        Scene newScene = new Scene(window3);
        Stage mainWindow = (Stage)  ((Node)actionEvent.getSource()).getScene().getWindow();

        mainWindow.setScene(newScene);
    }

    public void onAddGroupButtonClick(ActionEvent actionEvent) {
        if (groupsComboBox.getSelectionModel().getSelectedItem() != null
                && modulesComboBox.getSelectionModel().getSelectedItem() != null
                && timesComboBox.getSelectionModel().getSelectedItem() != null) {

            if (modules != null && groups != null) {
                Modulo module = modules.get(modulesComboBox.getSelectionModel().getSelectedIndex());
                Grupo grupo = groups.get(groupsComboBox.getSelectionModel().getSelectedIndex());
                Integer times = Integer.parseInt(observableTimes.get(timesComboBox.getSelectionModel().getSelectedIndex()));

                if (module != null && groups != null && times != null) {
                    int idTeacher = module.getRandomProfessorId();
                    if (idTeacher != -1) {
                        dao.insertGroupsModuleTeacher(grupo.getGroupId(), module.getModuleId(), idTeacher, times.intValue());
                        StringBuffer ids = new StringBuffer();
                        ids.append("{");
                        ids.append(grupo.getGroupId());
                        ids.append(",");
                        ids.append(module.getModuleId());
                        ids.append(",");
                        ids.append(idTeacher);
                        ids.append(",");
                        ids.append(times);
                        ids.append("}");
                        groupsModulesResultListView.getItems().add("vinculo: " + ids);

                        modulesComboBox.getSelectionModel().clearSelection();
                        groupsComboBox.getSelectionModel().clearSelection();
                        timesComboBox.getSelectionModel().clearSelection();
                    } else {
                        showWarningAlertDialog("El modulo seleccionado no tiene por lo menos un profesor asignado");
                    }
                }
            } else {
                showErrorAlertDialog("Error");
            }
        } else {
            showWarningAlertDialog("Debe completar todos los campos");
        }
    }
}
