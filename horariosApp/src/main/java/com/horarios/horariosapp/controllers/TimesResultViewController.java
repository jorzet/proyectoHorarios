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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class TimesResultViewController implements Initializable {

    @FXML
    private Label solutionLabel;
    @FXML
    private Label bestSolutionLabel;
    @FXML
    private Label crossLabel;
    /*@FXML
    private TreeView<TimesResult> timesResultListView;*/
    @FXML
    private ListView timesResultListView;
    private ObservableList<TimesResult> algorithmTimesResult = FXCollections.observableArrayList();
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
        Dao dao = new Dao();

        for (Class bestClass : classes) {
            TimesResult timeResult = new TimesResult();

            if (bestClass != null) {
                timeResult.setClassNumber(String.valueOf(classIndex));
                timeResult.setModuleName(timetable.getModule(bestClass.getModuleId()).getModuleName());
                timeResult.setModuleCode(timetable.getModule(bestClass.getModuleId()).getModuleCode());
                timeResult.setGroupNumber(String.valueOf(timetable.getGroup(bestClass.getGroupId()).getGroupId()));
                timeResult.setRoomCode(timetable.getRoom(bestClass.getRoomId()).getRoomNumber());
                timeResult.setTeacherName(timetable.getProfessor(bestClass.getProfessorId()).getProfessorName());
                timeResult.setTime(timetable.getTimeslot(bestClass.getTimeslotId()).getTimeslot());
                timeResult.setDay(timetable.getTimeslot(bestClass.getTimeslotId()).getDay());
                timesResult.add(timeResult);
                //dao.insertTimesGroup(timeResult);

                System.out.println("Clase " + timeResult.getClassNumber() + ":");
                System.out.println("Asignatura: " + timeResult.getModuleName());
                System.out.println("Grupo: " + timeResult.getGroupNumber());
                System.out.println("Aula: " + timeResult.getRoomCode());
                System.out.println("Profesor: " + timeResult.getTeacherName());
                System.out.println("Horario: " + timeResult.getTime());
                System.out.println("Dia: " + timeResult.getDay());
                System.out.println("-----");

                classIndex++;
            }
        }

        algorithmTimesResult = timesResult;
        Map<String, List<TimesResult>> mapResult =
                timesResult.stream().collect(Collectors.groupingBy(TimesResult::getGroupNumber));

        Iterator<List<TimesResult>> iterator = mapResult.values().iterator();
        ObservableList<TimesResult> groupsTimeResult = FXCollections.observableArrayList();
        while (iterator.hasNext()) {
            List<TimesResult> timesResults = iterator.next();
            groupsTimeResult.add(timesResults.get(0));
        }

        Collections.sort(groupsTimeResult, new Comparator<TimesResult>() {
            @Override
            public int compare(TimesResult tr1, TimesResult tr2) {
                double time1 = Integer.parseInt(tr1.getGroupNumber());
                double time2 = Integer.parseInt(tr2.getGroupNumber());
                return (time1<time2 ? -1 : (time1==time2 ? 0 : 1));
            }
        });

        timesResultListView.setItems(groupsTimeResult);
        timesResultListView.setCellFactory((Callback<ListView<TimesResult>, ListCell<TimesResult>>) param -> new TimeResultListCell(TimeResultListCell.SHOW_TYPE.GROUP));
        timesResultListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("clicked on " + timesResultListView.getSelectionModel().getSelectedItem());
                try {
                    String group = ((TimesResult) timesResultListView.getSelectionModel().getSelectedItem()).getGroupNumber();
                    ObservableList<TimesResult> groupTimeResult =
                            FXCollections.observableArrayList(mapResult.get(group));

                    showTimesByGroupView(groupTimeResult);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        /*TreeItem<TimesResult> rootNode = new TreeItem<>();
        rootNode.setExpanded(true);

        Iterator<List<TimesResult>> iterator = mapResult.values().iterator();
        while (iterator.hasNext()) {
            List<TimesResult> timesResults = iterator.next();
            TimesResult timesResultTitle = createTimeResultTitle(timesResults.get(0));
            TreeItem<TimesResult> groupNode = new TreeItem<>(timesResultTitle);
            for (TimesResult timesResult1: timesResults) {
                timesResult1.setIsTitle(false);
                groupNode.getChildren().addAll(new TreeItem<>(timesResult1));
            }
            rootNode.getChildren().addAll(groupNode);
        }

        timesResultListView.setRoot(rootNode);
        timesResultListView.setShowRoot(false);
        timesResultListView.setCellFactory(p -> new ModuleTreeCell());*/
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
                timetable.addTimeslot(time.getTimeslotId(), time.getTimeslot(), time.getDay() );
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
                ArrayList<Integer> teachersId = dao.getAllTeachersIdByModuleCode(module.getModuleCode());
                if (teachersId != null) {
                    int[] ids = new int[teachersId.size()];
                    for (int j = 0; j < teachersId.size(); j++) {
                        ids[j] = teachersId.get(j);
                    }
                    timetable.addModule(module.getModuleId(), module.getModuleCode(), module.getModuleName(), ids);
                }
            }
        }

        ArrayList<Grupo> groups = dao.getAllGroups();

        if (groups != null) {
            for (Grupo group : groups) {
                ArrayList<Match> matches = dao.getAllModulesByGroupId(group.getGroupId());
                if (matches != null) {
                    timetable.addGroup(group.getGroupId(), group.getGroupSize(), matches);
                }
            }
        }

        return timetable;
    }

    public void onBackButtonClick(ActionEvent actionEvent) throws Exception{
        Parent window3 = FXMLLoader.load(Objects.requireNonNull(Application.class.getResource("times_creator_view.fxml")));
        Scene newScene = new Scene(window3);
        Stage mainWindow = (Stage)  ((Node)actionEvent.getSource()).getScene().getWindow();

        mainWindow.setScene(newScene);
    }

    private TimesResult createTimeResultTitle(TimesResult timesResult) {
        TimesResult timesResult1 = new TimesResult();
        timesResult1.setModuleName(timesResult.getModuleName());
        timesResult1.setIsTitle(true);
        return timesResult1;
    }

    private void showTimesByGroupView(ObservableList<TimesResult> groupsTimeResult) {
        TimesByGroupViewController timesByGroupViewController = new TimesByGroupViewController(groupsTimeResult);
        timesByGroupViewController.showStage();
    }

    public void showByGroup(ActionEvent actionEvent) {
        Map<String, List<TimesResult>> mapResult = algorithmTimesResult
                        .stream()
                        .collect(Collectors.groupingBy(TimesResult::getGroupNumber));

        Iterator<List<TimesResult>> iterator = mapResult.values().iterator();
        ObservableList<TimesResult> groupsTimeResult = FXCollections.observableArrayList();
        while (iterator.hasNext()) {
            List<TimesResult> timesResults = iterator.next();
            groupsTimeResult.add(timesResults.get(0));
        }

        Collections.sort(groupsTimeResult, new Comparator<TimesResult>() {
            @Override
            public int compare(TimesResult tr1, TimesResult tr2) {
                double time1 = Integer.parseInt(tr1.getGroupNumber());
                double time2 = Integer.parseInt(tr2.getGroupNumber());
                return (time1<time2 ? -1 : (time1==time2 ? 0 : 1));
            }
        });

        timesResultListView.setItems(groupsTimeResult);
        timesResultListView.setCellFactory((Callback<ListView<TimesResult>, ListCell<TimesResult>>) param -> new TimeResultListCell(TimeResultListCell.SHOW_TYPE.GROUP));
        timesResultListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("clicked on " + timesResultListView.getSelectionModel().getSelectedItem());
                try {
                    String group = ((TimesResult) timesResultListView.getSelectionModel().getSelectedItem()).getGroupNumber();
                    ObservableList<TimesResult> groupTimeResult =
                            FXCollections.observableArrayList(mapResult.get(group));

                    showTimesByGroupView(groupTimeResult);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void showByTeacher(ActionEvent actionEvent) {
        Map<String, List<TimesResult>> mapResult =
                algorithmTimesResult.stream().collect(Collectors.groupingBy(TimesResult::getTeacherName));

        Iterator<List<TimesResult>> iterator = mapResult.values().iterator();
        ObservableList<TimesResult> teacherTimeResult = FXCollections.observableArrayList();
        while (iterator.hasNext()) {
            List<TimesResult> timesResults = iterator.next();
            teacherTimeResult.add(timesResults.get(0));
        }

        Collections.sort(teacherTimeResult, new Comparator<TimesResult>() {
            @Override
            public int compare(TimesResult tr1, TimesResult tr2) {
                double time1 = Integer.parseInt(tr1.getGroupNumber());
                double time2 = Integer.parseInt(tr2.getGroupNumber());
                return (time1<time2 ? -1 : (time1==time2 ? 0 : 1));
            }
        });

        timesResultListView.getItems().clear();
        timesResultListView.getSelectionModel().clearSelection();
        timesResultListView.setItems(teacherTimeResult);
        timesResultListView.setCellFactory(
                (Callback<ListView<TimesResult>, ListCell<TimesResult>>)
                        param -> new TimeResultListCell(TimeResultListCell.SHOW_TYPE.TEACHER)
        );
        timesResultListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("clicked on " + timesResultListView.getSelectionModel().getSelectedItem());
                try {
                    String teacherName = ((TimesResult) timesResultListView.getSelectionModel().getSelectedItem()).getTeacherName();
                    ObservableList<TimesResult> groupTimeResult =
                            FXCollections.observableArrayList(mapResult.get(teacherName));

                    showTimesByGroupView(groupTimeResult);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void showByModule(ActionEvent actionEvent) {
        Map<String, List<TimesResult>> mapResult =
                algorithmTimesResult.stream().collect(Collectors.groupingBy(TimesResult::getModuleName));

        Iterator<List<TimesResult>> iterator = mapResult.values().iterator();
        ObservableList<TimesResult> modulesTimeResult = FXCollections.observableArrayList();
        while (iterator.hasNext()) {
            List<TimesResult> timesResults = iterator.next();
            modulesTimeResult.add(timesResults.get(0));
        }

        Collections.sort(modulesTimeResult, new Comparator<TimesResult>() {
            @Override
            public int compare(TimesResult tr1, TimesResult tr2) {
                double time1 = Integer.parseInt(tr1.getGroupNumber());
                double time2 = Integer.parseInt(tr2.getGroupNumber());
                return (time1<time2 ? -1 : (time1==time2 ? 0 : 1));
            }
        });

        timesResultListView.getItems().clear();
        timesResultListView.getSelectionModel().clearSelection();
        timesResultListView.setItems(modulesTimeResult);
        timesResultListView.setCellFactory(
                (Callback<ListView<TimesResult>, ListCell<TimesResult>>)
                param -> new TimeResultListCell(TimeResultListCell.SHOW_TYPE.MODULE)
        );
        timesResultListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("clicked on " + timesResultListView.getSelectionModel().getSelectedItem());
                try {
                    String moduleName = ((TimesResult) timesResultListView.getSelectionModel().getSelectedItem()).getModuleName();
                    ObservableList<TimesResult> groupTimeResult =
                            FXCollections.observableArrayList(mapResult.get(moduleName));

                    showTimesByGroupView(groupTimeResult);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
