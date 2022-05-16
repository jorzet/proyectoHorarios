package com.horarios.horariosapp.controllers;

import com.horarios.horariosapp.Application;
import com.horarios.horariosapp.controllers.base.BaseController;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class GroupsViewController extends BaseController {
    @FXML
    private TextField capacityTextField;
    @FXML
    private TextField groupNameTextField;
    @FXML
    private ComboBox modulesComboBox;
    @FXML
    private ComboBox matutinoComboBox;
    @FXML
    private ListView currentGroupsListView;

    private ArrayList<Modulo> modules;

    ObservableList<String> observableMatutino = FXCollections.observableArrayList("Matutino", "Vespertino");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> observableModules = FXCollections.observableArrayList();

        Dao dao = new Dao(); // Carga de BD
        modules = dao.getAllModules();
        if (modules != null) {
            for (Modulo module : modules) {
                observableModules.add(module.getModuleCode());
            }
        }
        modulesComboBox.getItems().addAll(observableModules);
        matutinoComboBox.getItems().addAll(observableMatutino);

        ArrayList<Grupo> groups = dao.getAllGroups();
        if (groups != null) {
            for (int i = 0; i < modules.size(); i++) {
                Grupo group = groups.get(i);
                ArrayList<Integer> modulesId = dao.getAllModulesByGroupId(group.getGroupId());
                if (modulesId != null) {
                    StringBuffer ids = new StringBuffer();
                    ids.append("{");
                    for (Integer integer : modulesId) {
                        ids.append(integer).append(", ");
                    }
                    ids.append("}");
                    String turno = group.isMatutino() ? "Matutino" : "Vespertino";
                    currentGroupsListView.getItems().add("capacidad: " + group.getGroupSize() + " Turno: " + turno + " modulos: " + ids);
                }
            }
        }
    }

    public void onBackButtonClick(ActionEvent actionEvent) throws Exception{
        Parent window3 = FXMLLoader.load(Objects.requireNonNull(Application.class.getResource("times_creator_view.fxml")));
        Scene newScene = new Scene(window3);
        Stage mainWindow = (Stage)  ((Node)actionEvent.getSource()).getScene().getWindow();

        mainWindow.setScene(newScene);
    }

    public void onAddGroupButtonClick(ActionEvent actionEvent) {
        String capacityText = capacityTextField.getText();
        String groupName = groupNameTextField.getText();

        if (!groupName.isEmpty() && !capacityText.isEmpty() &&
                modulesComboBox.getSelectionModel().getSelectedItem() != null &&
                matutinoComboBox.getSelectionModel().getSelectedItem() != null) {
            if (isNumeric(capacityText)) {
                currentGroupsListView.getItems().add("capacidad: " + capacityTextField.getText() + " Turno: " + modulesComboBox.getSelectionModel().getSelectedItem() + " modulos: " + modulesComboBox.getSelectionModel().getSelectedIndex());

                Dao dao = new Dao();

                if (modules != null) {
                    int moduleIndex = modulesComboBox.getSelectionModel().getSelectedIndex();
                    Modulo module = modules.get(moduleIndex);
                    boolean matutino = modulesComboBox.getSelectionModel().getSelectedItem().equals("Matutino");
                    Grupo grupo = new Grupo();
                    grupo.setGroupName(groupNameTextField.getText());
                    grupo.setGroupSize(Integer.parseInt(capacityTextField.getText()));
                    grupo.setMatutino(matutino);
                    grupo.setModuleIds(new int[]{module.getModuleId()});

                    String result = dao.insertGroup(grupo);

                    modulesComboBox.getSelectionModel().clearSelection();
                    matutinoComboBox.getSelectionModel().clearSelection();
                    capacityTextField.setText("");
                    groupNameTextField.setText("");

                    System.out.println(result);

                }

                capacityTextField.setText("");

                Stage mainWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                mainWindow.show();
            } else {
                showErrorAlertDialog("El campo capacidad debe ser numerico");
            }
        } else {
            showWarningAlertDialog("Debe completar todos los campos");
        }
    }
}
