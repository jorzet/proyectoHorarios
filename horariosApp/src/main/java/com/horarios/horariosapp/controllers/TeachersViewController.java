package com.horarios.horariosapp.controllers;

import com.horarios.horariosapp.Application;
import com.horarios.horariosapp.data.Profesor;
import com.horarios.horariosapp.repository.Dao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TeachersViewController implements Initializable {

    @FXML
    private TextField teacherNameTextField;
    @FXML
    private TextField teacherFirstLastNameTextField;
    @FXML
    private TextField teacherSecondLastNameTextField;

    @FXML
    private ListView currentTeacherListView;

    public void onBackButtonClick(ActionEvent actionEvent) throws Exception{
        Parent window3;
        window3 = FXMLLoader.load(Application.class.getResource("times_creator_view.fxml"));

        Scene newScene;
        newScene = new Scene(window3);

        Stage mainWindow;
        mainWindow = (Stage)  ((Node)actionEvent.getSource()).getScene().getWindow();

        mainWindow.setScene(newScene);
    }

    public void onAddTeacherButtonClick(ActionEvent actionEvent) {
        if (!teacherNameTextField.getText().isEmpty() &&
                !teacherFirstLastNameTextField.getText().isEmpty() &&
                !teacherSecondLastNameTextField.getText().isEmpty()) {

            currentTeacherListView.setCellFactory(TextFieldListCell.forListView());
            currentTeacherListView.setEditable(false);
            currentTeacherListView.getItems().add(teacherNameTextField.getText() + " " + teacherFirstLastNameTextField.getText() + " " + teacherSecondLastNameTextField.getText());

            Profesor teacher = new Profesor();
            teacher.setProfessorName(teacherNameTextField.getText());
            teacher.setProfesorFirstLastName(teacherFirstLastNameTextField.getText());
            teacher.setProfesorSecondtLastName(teacherSecondLastNameTextField.getText());

            Dao dao = new Dao();
            String result = dao.insertTeacher(teacher);

            System.out.println(result);

            teacherNameTextField.setText("");
            teacherFirstLastNameTextField.setText("");
            teacherSecondLastNameTextField.setText("");

            Stage mainWindow;
            mainWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            mainWindow.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Dao dao = new Dao();
        ArrayList<Profesor> teachers = dao.getAllTeachers();
        if (teachers != null) {
            currentTeacherListView.setCellFactory(TextFieldListCell.forListView());
            currentTeacherListView.setEditable(false);

            for (Profesor teacher : teachers) {
                currentTeacherListView.getItems().add(teacher.getProfessorName() + " " + teacher.getProfesorFirstLastName() + " " + teacher.getProfesorSecondtLastName());
            }
        }
    }
}
