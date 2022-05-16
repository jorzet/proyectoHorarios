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
    private ListView groupsModulesResultListView;


    private ArrayList<Grupo> groups;
    private ArrayList<Modulo> modules;

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

        ArrayList<GroupModule> groupModules = dao.getAllModulesAndGroups();
        for (GroupModule groupModule : groupModules) {
            StringBuffer ids = new StringBuffer();
            ids.append("{");
            ids.append(groupModule.getIdGroup());
            ids.append(",");
            ids.append(groupModule.getIdModule());
            ids.append(",");
            ids.append(groupModule.getIdTeacher());
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
                && modulesComboBox.getSelectionModel().getSelectedItem() != null) {
            if (modules != null && groups != null) {
                Modulo module = modules.get(modulesComboBox.getSelectionModel().getSelectedIndex());
                Grupo grupo = groups.get(groupsComboBox.getSelectionModel().getSelectedIndex());

                if (module != null && groups != null) {
                    int idTeacher = module.getRandomProfessorId();
                    dao.insertGroupsModuleTeacher(grupo.getGroupId(), module.getModuleId(), idTeacher);
                    StringBuffer ids = new StringBuffer();
                    ids.append("{");
                    ids.append(grupo.getGroupId());
                    ids.append(",");
                    ids.append(module.getModuleId());
                    ids.append(",");
                    ids.append(idTeacher);
                    ids.append("}");
                    groupsModulesResultListView.getItems().add("vinculo: "+ids);

                    modulesComboBox.getSelectionModel().clearSelection();
                    groupsComboBox.getSelectionModel().clearSelection();
                }
            } else {
                showErrorAlertDialog("Error");
            }
        } else {
            showWarningAlertDialog("Debe completar todos los campos");
        }
    }
}
