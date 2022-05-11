package com.horarios.horariosapp.controllers;

import com.horarios.horariosapp.Application;
import com.horarios.horariosapp.algorithm.AlgoritmoGenetico;
import com.horarios.horariosapp.algorithm.EvaHorario;
import com.horarios.horariosapp.algorithm.Poblacion;
import com.horarios.horariosapp.data.*;
import com.horarios.horariosapp.data.Class;
import com.horarios.horariosapp.repository.Dao;
import com.horarios.horariosapp.views.TimeResultListCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TimesResultViewController implements Initializable {

    @FXML
    private Label solutionLabel;
    @FXML
    private Label bestSolutionLabel;
    @FXML
    private Label crossLabel;
    @FXML
    private ListView timesResultListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        computeTimesResult();
    }

    private void computeTimesResult() {
        EvaHorario timetable = getTimesTable();
        AlgoritmoGenetico ga = new AlgoritmoGenetico(100, 0.01, 0.9, 2, 5);
        Poblacion population = ga.initPopulation(timetable);
        ga.evalPopulation(population, timetable);
        int generation = 1;
        while (ga.isTerminationConditionMet(generation, 1000) == false
                && ga.isTerminationConditionMet(population) == false) {
            System.out.println("G" + generation + " Mejor Individuo: " + population.getFittest(0).getFitness());
            population = ga.crossoverPopulation(population);
            population = ga.mutatePopulation(population, timetable);
            ga.evalPopulation(population, timetable);
            generation++;
        }
        timetable.createClasses(population.getFittest(0));
        System.out.println();
        System.out.println("Solución encontrada en: " + generation + " generaciones");
        System.out.println("Mejor solución final: " + population.getFittest(0).getFitness());
        System.out.println("Cruces: " + timetable.calcClashes());

        solutionLabel.setText("Solución encontrada en: " + generation + " generaciones");
        bestSolutionLabel.setText("Mejor solución final: " + population.getFittest(0).getFitness());
        crossLabel.setText("Cruces: " + timetable.calcClashes());

        System.out.println();
        Class classes[] = timetable.getClasses();
        ObservableList<TimesResult> timesResult = FXCollections.observableArrayList();
        int classIndex = 1;

        for (Class bestClass : classes) {
            TimesResult timeResult = new TimesResult();

            timeResult.setClassNumber(classIndex);
            timeResult.setModuleName(timetable.getModule(bestClass.getModuleId()).getModuleName());
            timeResult.setGroupNumber(timetable.getGroup(bestClass.getGroupId()).getGroupId());
            timeResult.setRoomCode(timetable.getRoom(bestClass.getRoomId()).getRoomNumber());
            timeResult.setTeacherName(timetable.getProfessor(bestClass.getProfessorId()).getProfessorName());
            timeResult.setTime(timetable.getTimeslot(bestClass.getTimeslotId()).getTimeslot());
            timesResult.add(timeResult);

            System.out.println("Clase " + timeResult.getClassNumber() + ":");
            System.out.println("Asignatura: " + timeResult.getModuleName());
            System.out.println("Grupo: " + timeResult.getGroupNumber());
            System.out.println("Aula: " + timeResult.getRoomCode());
            System.out.println("Profesor: " + timeResult.getTeacherName());
            System.out.println("Horario: " + timeResult.getTime());
            System.out.println("-----");

            classIndex++;
        }

        timesResultListView.setItems(timesResult);
        timesResultListView.setCellFactory((Callback<ListView<TimesResult>, ListCell<TimesResult>>) param -> new TimeResultListCell());
    }

    private EvaHorario getTimesTable() {
        EvaHorario timetable = new EvaHorario();
        Dao dao = new Dao();

        ArrayList<Aula> rooms = dao.getAllRooms();

        if (rooms != null) {
            for (Aula room : rooms) {
                timetable.addRoom(room.getRoomId(), room.getRoomNumber(), room.getRoomCapacity());
            }
        }

        ArrayList<Horario> times = dao.getAllTimes();

        if (times != null) {
            for (Horario time : times) {
                timetable.addTimeslot(time.getTimeslotId(), time.getDay() + " " + time.getTimeslot());
            }
        }

        ArrayList<Profesor> teachers = dao.getAllTeachers();

        if (teachers != null) {
            for (Profesor teacher : teachers) {
                timetable.addProfessor(teacher.getProfessorId(), teacher.getProfesorFirstLastName() + " " + teacher.getProfesorSecondtLastName() + " " + teacher.getProfessorName());
            }
        }

        ArrayList<Modulo> modules = dao.getAllModules();

        if (modules != null) {
            for (Modulo module : modules) {
                ArrayList<Integer> teachersId = dao.getAllTeachersIdByModuleId(module.getModuleId());
                int[] ids = new int[teachersId.size()];
                for (int j = 0; j < teachersId.size(); j++) {
                    ids[j] = teachersId.get(j);
                }
                timetable.addModule(module.getModuleId(), module.getModuleCode(), module.getModuleName(), ids);
            }
        }


        ArrayList<Grupo> groups = dao.getAllGroups();

        if (groups != null) {
            for (Grupo group : groups) {
                ArrayList<Integer> modulesId = dao.getAllModulesByGroupId(group.getGroupId());
                int[] ids = new int[modulesId.size()];
                for (int j = 0; j < modulesId.size(); j++) {
                    ids[j] = modulesId.get(j);
                }
                timetable.addGroup(group.getGroupId(), group.getGroupSize(), ids);
            }
        }

        return timetable;
    }

    public void onBackButtonClick(ActionEvent actionEvent) throws Exception{
        Parent window3 = FXMLLoader.load(Application.class.getResource("times_creator_view.fxml"));
        Scene newScene = new Scene(window3);
        Stage mainWindow = (Stage)  ((Node)actionEvent.getSource()).getScene().getWindow();

        mainWindow.setScene(newScene);
    }
}
