package com.horarios.horariosapp.controllers;

import com.horarios.horariosapp.Application;
import com.horarios.horariosapp.controllers.base.BaseController;
import com.horarios.horariosapp.data.TimesResult;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class TimesByGroupViewController extends BaseController {

    @FXML
    private TableView<Result> timesResultTableView;
    private final ObservableList<TimesResult> groupsTimeResult;

    ObservableList<String> hours = FXCollections.observableArrayList(//
            "7:00 - 8:00", "8:00 - 9:00", "9:00 - 10:00", "10:00 - 11:00", //
            "11:00 - 12:00", "12:00 - 13:00", "13:00 - 14:00", "14:00 - 15:00",
            "15:00 - 16:00", "16:00 - 17:00", "17:00 - 18:00", "18:00 - 19:00");

    private Stage thisStage;

    public TimesByGroupViewController(ObservableList<TimesResult> groupsTimeResult) {
            // We received the first controller, now let's make it usable throughout this controller.
            this.groupsTimeResult = groupsTimeResult;

            // Create the new stage
            thisStage = new Stage();

            // Load the FXML file
            try {
                FXMLLoader loader = new FXMLLoader(Application.class.getResource("times_by_group_view.fxml"));

                // Set this class as the controller
                loader.setController(this);

                // Load the scene
                thisStage.setScene(new Scene(loader.load()));

                // Setup the window/stage
                thisStage.setTitle("Passing Controllers Example - Layout2");

            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void showStage() {
        thisStage.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<TimesResult> results = groupsTimeResult;
        ObservableList<Result> groupsTimeResult_ = FXCollections.observableArrayList();;
        if (results != null) {
            for (TimesResult timeResult : results) {
                Result result = new Result(timeResult.getClassNumber(), timeResult.getGroupNumber(), timeResult.getModuleName(), timeResult.getModuleCode(), timeResult.getRoomCode(), timeResult.getTeacherName(), timeResult.getTime(), timeResult.getDay());
                groupsTimeResult_.add(result);
                /*for (String hour : hours) {
                    if (timeResult.getTime().equals(hour)) {
                        groupsTimeResult_.add(result);
                    } else {
                        Result newTimesResult = new Result("","","","","","",hour,"");
                        groupsTimeResult_.add(newTimesResult);
                    }
                }*/
            }


            TableColumn times = new TableColumn<Result, String>("Horas");
            times.setMinWidth(100);
            times.setCellValueFactory(
                    new PropertyValueFactory<>("time")
            );
            times.setCellFactory((Callback<TableColumn<Result, SimpleStringProperty>, TableCell<Result, SimpleStringProperty>>)
                    timesResultStringTableColumn ->
                            new TableCell<>() {
                                @Override
                                protected void updateItem(SimpleStringProperty item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (!empty && item != null) {
                                        setText(item.get());
                                    } else {
                                        setText(null);
                                    }
                                }
                            }
            );

            TableColumn monday = new TableColumn<Result, String>("Lunes");
            monday.setMinWidth(100);
            monday.setCellValueFactory(
                    new PropertyValueFactory<>("day")
            );
            monday.setCellFactory((Callback<TableColumn<Result, SimpleStringProperty>, TableCell<Result, SimpleStringProperty>>)
                    timesResultStringTableColumn ->
                            new TableCell<>() {
                                @Override
                                protected void updateItem(SimpleStringProperty item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (!empty && item != null && item.get().contains("Lunes")) {
                                        setText(item.get());
                                    } else {
                                        setText(null);
                                    }
                                }
                            }
            );

            TableColumn tuesday = new TableColumn<Result, SimpleStringProperty>("Martes");
            monday.setMinWidth(100);
            tuesday.setCellValueFactory(
                    new PropertyValueFactory<>("day")
            );
            tuesday.setCellFactory((Callback<TableColumn<Result, SimpleStringProperty>, TableCell<Result, SimpleStringProperty>>)
                    timesResultStringTableColumn ->
                            new TableCell<>() {
                                @Override
                                protected void updateItem(SimpleStringProperty item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (!empty && item != null && item.get().contains("Martes")) {
                                        setText(item.get());
                                    } else {
                                        setText(null);
                                    }
                                }
                            }
            );

            TableColumn wednesday = new TableColumn<Result, SimpleStringProperty>("Miercoles");
            monday.setMinWidth(100);
            wednesday.setCellValueFactory(
                    new PropertyValueFactory<>("day")
            );
            wednesday.setCellFactory((Callback<TableColumn<Result, SimpleStringProperty>, TableCell<Result, SimpleStringProperty>>)
                    timesResultStringTableColumn ->
                            new TableCell<>() {
                                @Override
                                protected void updateItem(SimpleStringProperty item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (!empty && item != null && item.get().contains("Miercoles")) {
                                        setText(item.get());
                                    } else {
                                        setText(null);
                                    }
                                }
                            }
            );

            TableColumn thursday = new TableColumn<Result, SimpleStringProperty>("Jueves");
            monday.setMinWidth(100);
            thursday.setCellValueFactory(
                    new PropertyValueFactory<>("day")
            );
            thursday.setCellFactory((Callback<TableColumn<Result, SimpleStringProperty>, TableCell<Result, SimpleStringProperty>>)
                    timesResultStringTableColumn ->
                            new TableCell<>() {
                                @Override
                                protected void updateItem(SimpleStringProperty item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (!empty && item != null && item.get().contains("Jueves")) {
                                        setText(item.get());
                                    } else {
                                        setText(null);
                                    }
                                }
                            }
            );

            TableColumn friday = new TableColumn<>("Viernes");
            monday.setMinWidth(100);
            friday.setCellValueFactory(
                    new PropertyValueFactory<>("day")
            );
            friday.setCellFactory((Callback<TableColumn<Result, SimpleStringProperty>, TableCell<Result, SimpleStringProperty>>)
                    timesResultStringTableColumn ->
                            new TableCell<>() {
                                @Override
                                protected void updateItem(SimpleStringProperty item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (!empty && item != null && item.get().contains("Viernes")) {
                                        setText(item.get());
                                    } else {
                                        setText(null);
                                    }
                                }
                            }
            );

            timesResultTableView.setItems(groupsTimeResult_);
            timesResultTableView.getColumns().addAll(times, monday, tuesday, wednesday, thursday, friday);
        }
    }

    public void onBackButtonClick(ActionEvent actionEvent) throws Exception {
        Parent window3 = FXMLLoader.load(Objects.requireNonNull(Application.class.getResource("times_result_view.fxml")));
        Scene newScene = new Scene(window3);
        Stage mainWindow = (Stage)  ((Node)actionEvent.getSource()).getScene().getWindow();

        mainWindow.setScene(newScene);
    }

    public static class Result {

        private final SimpleStringProperty classNumber;
        private final SimpleStringProperty groupNumber;
        private final SimpleStringProperty moduleName;
        private final SimpleStringProperty moduleCode;
        private final SimpleStringProperty roomCode;
        private final SimpleStringProperty teacherName;
        private final SimpleStringProperty time;
        private final SimpleStringProperty day;

        private Result(String classNumber, String groupNumber, String moduleName, String moduleCode, String roomCode, String teacherName, String time, String day) {
            this.classNumber = new SimpleStringProperty(classNumber);
            this.groupNumber = new SimpleStringProperty(groupNumber);
            this.moduleName = new SimpleStringProperty(moduleName);
            this.moduleCode = new SimpleStringProperty(moduleCode);
            this.roomCode = new SimpleStringProperty(roomCode);
            this.teacherName = new SimpleStringProperty(teacherName);
            this.time = new SimpleStringProperty(time);
            this.day = new SimpleStringProperty(day + "\n" + teacherName + "\n"+moduleName);
        }

        public SimpleStringProperty  getClassNumber() {
            return classNumber;
        }

        public SimpleStringProperty getGroupNumber() {
            return groupNumber;
        }

        public SimpleStringProperty  getModuleName() {
            return moduleName;
        }

        public SimpleStringProperty getModuleCode() {
            return moduleCode;
        }

        public SimpleStringProperty getRoomCode() {
            return roomCode;
        }

        public SimpleStringProperty getTeacherName() {
            return teacherName;
        }

        public SimpleStringProperty getTime() {
            return time;
        }

        public SimpleStringProperty getDay() {
            return day;
        }
    }
}
