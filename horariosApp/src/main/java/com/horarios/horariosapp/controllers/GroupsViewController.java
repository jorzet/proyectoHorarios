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
    private CheckComboBox modulesComboBox;
    @FXML
    private ListView currentGroupsListView;

    private ArrayList<Modulo> modules;

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

        ArrayList<Grupo> groups = dao.getAllGroups();
        if (groups != null) {
            for (int i = 0; i < modules.size(); i++) {
                Grupo group = groups.get(i);
                ArrayList<Integer> modulesId = dao.getAllModulesByGroupId(group.getGroupId());
                StringBuffer ids = new StringBuffer();
                ids.append("{");
                for (Integer integer : modulesId) {
                    ids.append(integer).append(", ");
                }
                ids.append("}");
                currentGroupsListView.getItems().add("capacidad: " + group.getGroupSize() + " modulos: " + ids);
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

        if (!capacityText.isEmpty() && modulesComboBox.getCheckModel().getItemCount() > 0) {
            if (isNumeric(capacityText)) {
                currentGroupsListView.getItems().add("capacidad: " + capacityTextField.getText() + " modulos: " + modulesComboBox.getCheckModel().getCheckedIndices());

                Dao dao = new Dao();

                if (modules != null) {
                    for (int i = 0; i < modulesComboBox.getCheckModel().getCheckedIndices().size(); i++) {
                        Modulo module = modules.get(modulesComboBox.getCheckModel().getCheckedIndices().indexOf(i));

                        Grupo grupo = new Grupo();
                        grupo.setGroupSize(Integer.parseInt(capacityTextField.getText()));
                        grupo.setModuleIds(new int[]{module.getModuleId()});

                        String result = dao.insertGroup(grupo);

                        System.out.println(result);
                    }
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
