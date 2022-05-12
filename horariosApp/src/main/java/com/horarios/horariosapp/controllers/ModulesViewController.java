package com.horarios.horariosapp.controllers;

import com.horarios.horariosapp.Application;
import com.horarios.horariosapp.controllers.base.BaseController;
import com.horarios.horariosapp.data.Modulo;
import com.horarios.horariosapp.data.Profesor;
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
import java.util.ResourceBundle;

public class ModulesViewController extends BaseController {

    @FXML
    private CheckComboBox teacherComboBox;
    @FXML
    private TextField moduleCodeTextField;
    @FXML
    private TextField moduleNameTextField;
    @FXML
    private ListView currentModulesListView;

    private ArrayList<Profesor> teachers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Dao dao = new Dao();
        teachers = dao.getAllTeachers();

        if (teachers != null) {
            ObservableList<String> observableTeachers = FXCollections.observableArrayList();
            for (Profesor teacher : teachers) {
                observableTeachers.add(teacher.getProfessorName() + " " + teacher.getProfesorFirstLastName() + " " + teacher.getProfesorSecondtLastName());
            }
            teacherComboBox.getItems().addAll(observableTeachers);
        }

        ArrayList<Modulo> modules = dao.getAllModules();
        if (modules != null) {
            for (Modulo module : modules) {
                ArrayList<Integer> teachersId = dao.getAllTeachersIdByModuleId(module.getModuleId());
                StringBuilder ids = new StringBuilder();
                ids.append("{");
                for (Integer integer : teachersId) {
                    ids.append(integer).append(", ");
                }
                ids.append("}");
                currentModulesListView.getItems().add("codigo: " + module.getModuleCode() + " nombre: " + module.getModuleName() + " profesores: " + ids);
            }
        }
    }

    public void onBackButtonClick(ActionEvent actionEvent) throws Exception{
        Parent window3;
        window3 = FXMLLoader.load(Application.class.getResource("times_creator_view.fxml"));

        Scene newScene;
        newScene = new Scene(window3);

        Stage mainWindow;
        mainWindow = (Stage)  ((Node)actionEvent.getSource()).getScene().getWindow();

        mainWindow.setScene(newScene);
    }

    public void onAddModuleButtonClick(ActionEvent actionEvent) {
        if (!moduleCodeTextField.getText().isEmpty() && !moduleNameTextField.getText().isEmpty()
                && teacherComboBox.getCheckModel().getItemCount() > 0) {

            currentModulesListView.getItems().add("codigo: " + moduleCodeTextField.getText() + " nombre: " + moduleNameTextField.getText() + " profesores: " + teacherComboBox.getCheckModel().getCheckedIndices());

            Dao dao = new Dao();


            if (teachers != null) {
                for (int i = 0; i < teacherComboBox.getCheckModel().getCheckedIndices().size(); i++) {
                    Profesor teacher = teachers.get(teacherComboBox.getCheckModel().getCheckedIndices().indexOf(i));
                    Modulo module = new Modulo();
                    module.setModuleCode(moduleCodeTextField.getText());
                    module.setModule(moduleNameTextField.getText());
                    module.setProfessorIds(new int[]{teacher.getProfessorId()});
                    String result = dao.insertModule(module);
                    System.out.println(result);
                }
            }

            moduleCodeTextField.setText("");
            moduleNameTextField.setText("");

            Stage mainWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            mainWindow.show();
        }
    }
}
