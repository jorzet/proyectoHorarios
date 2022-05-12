package com.horarios.horariosapp.views;

import com.horarios.horariosapp.data.TimesResult;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ModuleTreeCell extends TreeCell<TimesResult> {

    private final AnchorPane anchorTitlePane;
    private final VBox anchorChildPane;
    private final Label title;
    private final Label classLabel;
    private final Label groupLabel;
    private final Label teacherLabel;
    private final Label timeLabel;
    private final Label dayLabel;
    private final Label roomLabel;

    public ModuleTreeCell() {
        anchorTitlePane = new AnchorPane();
        title = new Label();
        anchorTitlePane.getChildren().addAll(title);
        anchorTitlePane.setStyle("-fx-border-color: gray");
        anchorTitlePane.setPadding(new Insets(5));

        anchorChildPane = new VBox();
        teacherLabel = new Label();
        classLabel = new Label();
        groupLabel = new Label();
        timeLabel = new Label();
        dayLabel = new Label();
        roomLabel = new Label();

        anchorChildPane.getChildren().addAll(dayLabel, timeLabel, teacherLabel, roomLabel, classLabel, groupLabel);
        anchorChildPane.setStyle("-fx-border-color: gray");
        anchorChildPane.setPadding(new Insets(5));
        //AnchorPane.setLeftAnchor(title,15.0);

    }

    @Override
    public void updateItem(TimesResult timesResult, boolean empty) {
        super.updateItem(timesResult, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else if (timesResult != null) {
            setText(null);
            if (timesResult.isTitle()) {
                title.setText(timesResult.getModuleName());
                setGraphic(anchorTitlePane);
            } else {
                dayLabel.setText("Dia: " + timesResult.getDay());
                timeLabel.setText("Hora: " + timesResult.getTime());
                classLabel.setText("Clase: " + timesResult.getClassNumber());
                teacherLabel.setText("Profesor: " + timesResult.getTeacherName());
                groupLabel.setText("Grupo: " + timesResult.getGroupNumber());
                roomLabel.setText("Aula: " + timesResult.getRoomCode());
                setGraphic(anchorChildPane);
            }
        }
    }
}