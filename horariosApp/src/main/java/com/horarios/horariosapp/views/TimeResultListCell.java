package com.horarios.horariosapp.views;

import com.horarios.horariosapp.data.TimesResult;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

public class TimeResultListCell extends ListCell<TimesResult> {
    private HBox content;
    private Label classNumber;
    private Label moduleName;
    private Label groupNumber;
    private Label roomCode;
    private Label teacherName;
    private Label timesLot;

    public TimeResultListCell() {
        super();
        classNumber = new Label();
        moduleName = new Label();
        groupNumber = new Label();
        roomCode = new Label();
        teacherName = new Label();
        timesLot = new Label();
        content = new HBox(classNumber, moduleName, groupNumber, roomCode, teacherName, timesLot);
        content.setSpacing(10);
    }

    @Override
    protected void updateItem(TimesResult timeResult, boolean empty) {
        super.updateItem(timeResult, empty);
        if (timeResult != null && !empty) { // <== test for null item and empty parameter
            classNumber.setText("Clase " + timeResult.getClassNumber() + ":");
            moduleName.setText("Asignatura: " + timeResult.getModuleName());
            groupNumber.setText("Grupo: " + timeResult.getGroupNumber());
            roomCode.setText("Aula: " + timeResult.getRoomCode());
            teacherName.setText("Profesor: " + timeResult.getTeacherName());
            timesLot.setText("Horario: " + timeResult.getTime());
            setGraphic(content);
        } else {
            setGraphic(null);
        }
    }
}
