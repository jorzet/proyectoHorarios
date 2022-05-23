package com.horarios.horariosapp.controllers;

import com.horarios.horariosapp.Application;
import com.horarios.horariosapp.controllers.base.BaseController;
import com.horarios.horariosapp.data.Grupo;
import com.horarios.horariosapp.data.TimesResult;
import com.horarios.horariosapp.views.TimeResultListCell;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TimesByGroupViewController extends BaseController {

    @FXML
    private TableView<Result> timesResultTableView;
    @FXML
    private Label timesByGroupLabel;
    private final ObservableList<TimesResult> groupsTimeResult;

    ObservableList<String> hours = FXCollections.observableArrayList(//
            "7:00 - 8:00", "8:00 - 9:00", "9:00 - 10:00", "10:00 - 11:00", //
            "11:00 - 12:00", "12:00 - 13:00", "13:00 - 14:00", "14:00 - 15:00",
            "15:00 - 16:00", "16:00 - 17:00", "17:00 - 18:00", "18:00 - 19:00");

    private Stage thisStage;
    private TimeResultListCell.SHOW_TYPE showType;

    private static final int columnMinWidth = 150;

    public TimesByGroupViewController(ObservableList<TimesResult> groupsTimeResult, TimeResultListCell.SHOW_TYPE showType) {
            // We received the first controller, now let's make it usable throughout this controller.
            this.groupsTimeResult = groupsTimeResult;
            this.showType = showType;

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

        if (showType.equals(TimeResultListCell.SHOW_TYPE.GROUP)) {
            timesByGroupLabel.setText("Horarios del grupo seleccionado");
        } else if (showType.equals(TimeResultListCell.SHOW_TYPE.TEACHER)) {
            timesByGroupLabel.setText("Horarios del profesor seleccionado");
        } else if (showType.equals(TimeResultListCell.SHOW_TYPE.MODULE)) {
            timesByGroupLabel.setText("Horarios del modulo seleccionado");
        }

        ObservableList<TimesResult> results = groupsTimeResult;

        if (results != null) {
            Map<String, List<TimesResult>> mapResult =
                    groupsTimeResult.stream().collect(Collectors.groupingBy(TimesResult::getTime));

            Iterator<List<TimesResult>> iterator = mapResult.values().iterator();
            ObservableList<Result> groupsTimeResult_ = FXCollections.observableArrayList();;

            while (iterator.hasNext()) {
                List<TimesResult> timesResults = iterator.next();

                StringBuffer resultString = new StringBuffer();
                StringBuffer time = new StringBuffer();

                for (TimesResult timeResult : timesResults) {
                    resultString.append(timeResult.getDay());
                    resultString.append("\n");
                    resultString.append(timeResult.getTeacherName());
                    resultString.append("\n");
                    resultString.append(timeResult.getModuleName());
                    resultString.append("\n");
                    resultString.append(timeResult.getRoomCode());
                    resultString.append("\n");
                    resultString.append(",");
                    time.append(timeResult.getTime());
                    time.append(",");
                }
                resultString.deleteCharAt(resultString.length()-1);
                time.deleteCharAt(time.length()-1);

                Result result = new Result(time.toString(), resultString.toString());
                groupsTimeResult_.add(result);
            }

            Collections.sort(groupsTimeResult_, new Comparator<Result>() {
                @Override
                public int compare(Result tr1, Result tr2) {
                    String time1_ = tr1.getTime().get();
                    String time2_ = tr2.getTime().get();
                    if (time1_.contains(","))
                        time1_ = time1_.split(",")[0];
                    if (time2_.contains(","))
                        time2_ = time2_.split(",")[0];
                    String[] str1 = time1_.split("-")[1].replace(" ", "").split(":");
                    String[] str2 = time2_.split("-")[1].replace(" ", "").split(":");
                    double time1 = Double.parseDouble(str1[0]) + Double.parseDouble(str1[1])/60;
                    double time2 = Double.parseDouble(str2[0]) + Double.parseDouble(str2[1])/60;
                    return (time1<time2 ? -1 : (time1==time2 ? 0 : 1));
                }
            });

            TableColumn times = new TableColumn<Result, String>("Horas");
            times.setMinWidth(columnMinWidth);
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
                                        String time = item.get();
                                        if (item.get().contains(","))
                                            time = item.get().split(",")[0];
                                        setText(time);
                                        TableRow<Result> currentRow = getTableRow();
                                        String[] str1 = time.split("-")[0].replace(" ", "").split(":");
                                        double doubleTime = Double.parseDouble(str1[0]) + Double.parseDouble(str1[1])/60;

                                        if(doubleTime > 14)
                                            currentRow.setStyle("-fx-background-color:lightcoral");
                                        else
                                            currentRow.setStyle("-fx-background-color:lightgreen");

                                    } else {
                                        setText(null);
                                    }
                                }
                            }
            );

            TableColumn monday = new TableColumn<Result, String>("Lunes");
            monday.setMinWidth(columnMinWidth);
            monday.setCellValueFactory(
                    new PropertyValueFactory<>("resultString")
            );
            monday.setCellFactory((Callback<TableColumn<Result, SimpleStringProperty>, TableCell<Result, SimpleStringProperty>>)
                    timesResultStringTableColumn ->
                            new TableCell<>() {
                                @Override
                                protected void updateItem(SimpleStringProperty item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (!empty && item != null && item.get().contains("Lunes")) {
                                        String[] mondayStrings = item.get().split(",");
                                        for (String mondayString: mondayStrings) {
                                            if (mondayString.contains("Lunes")) {
                                                setText(mondayString);
                                            }
                                        }
                                    } else {
                                        setText(null);
                                    }
                                }
                            }
            );

            TableColumn tuesday = new TableColumn<Result, SimpleStringProperty>("Martes");
            tuesday.setMinWidth(columnMinWidth);
            tuesday.setCellValueFactory(
                    new PropertyValueFactory<>("resultString")
            );
            tuesday.setCellFactory((Callback<TableColumn<Result, SimpleStringProperty>, TableCell<Result, SimpleStringProperty>>)
                    timesResultStringTableColumn ->
                            new TableCell<>() {
                                @Override
                                protected void updateItem(SimpleStringProperty item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (!empty && item != null && item.get().contains("Martes")) {
                                        String[] tuesdayStrings = item.get().split(",");
                                        for (String tuesdayString : tuesdayStrings) {
                                            if (tuesdayString.contains("Martes")) {
                                                setText(tuesdayString);
                                            }
                                        }
                                    } else {
                                        setText(null);
                                    }
                                }
                            }
            );

            TableColumn wednesday = new TableColumn<Result, SimpleStringProperty>("Miercoles");
            wednesday.setMinWidth(columnMinWidth);
            wednesday.setCellValueFactory(
                    new PropertyValueFactory<>("resultString")
            );
            wednesday.setCellFactory((Callback<TableColumn<Result, SimpleStringProperty>, TableCell<Result, SimpleStringProperty>>)
                    timesResultStringTableColumn ->
                            new TableCell<>() {
                                @Override
                                protected void updateItem(SimpleStringProperty item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (!empty && item != null && item.get().contains("Miercoles")) {
                                        String[] wednesdayStrings = item.get().split(",");
                                        for (String wednesdayString : wednesdayStrings) {
                                            if (wednesdayString.contains("Miercoles")) {
                                                setText(wednesdayString);
                                            }
                                        }
                                    } else {
                                        setText(null);
                                    }
                                }
                            }
            );

            TableColumn thursday = new TableColumn<Result, SimpleStringProperty>("Jueves");
            thursday.setMinWidth(columnMinWidth);
            thursday.setCellValueFactory(
                    new PropertyValueFactory<>("resultString")
            );
            thursday.setCellFactory((Callback<TableColumn<Result, SimpleStringProperty>, TableCell<Result, SimpleStringProperty>>)
                    timesResultStringTableColumn ->
                            new TableCell<>() {
                                @Override
                                protected void updateItem(SimpleStringProperty item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (!empty && item != null && item.get().contains("Jueves")) {
                                        String[] thursdayStrings = item.get().split(",");
                                        for (String thursdayString : thursdayStrings) {
                                            if (thursdayString.contains("Jueves")) {
                                                setText(thursdayString);
                                            }
                                        }
                                    } else {
                                        setText(null);
                                    }
                                }
                            }
            );

            TableColumn friday = new TableColumn<>("Viernes");
            friday.setMinWidth(columnMinWidth);
            friday.setCellValueFactory(
                    new PropertyValueFactory<>("resultString")
            );
            friday.setCellFactory((Callback<TableColumn<Result, SimpleStringProperty>, TableCell<Result, SimpleStringProperty>>)
                    timesResultStringTableColumn ->
                            new TableCell<>() {
                                @Override
                                protected void updateItem(SimpleStringProperty item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (!empty && item != null && item.get().contains("Viernes")) {
                                        String[] fridayStrings = item.get().split(",");
                                        for (String fridayString : fridayStrings) {
                                            if (fridayString.contains("Viernes")) {
                                                setText(fridayString);
                                            }
                                        }
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
        final Node source = (Node) actionEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public static class Result {

        private final SimpleStringProperty resultString;
        private final SimpleStringProperty time;

        private Result(String time, String resultString) {
            this.time = new SimpleStringProperty(time);
            this.resultString = new SimpleStringProperty(resultString);
        }

        public SimpleStringProperty getTime() {
            return time;
        }

        public SimpleStringProperty getResultString() {
            return resultString;
        }
    }
}
