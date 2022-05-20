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

    private SHOW_TYPE showType;
    public enum SHOW_TYPE {
        GROUP,
        MODULE,
        TEACHER,
        ROOM
    }
    public TimeResultListCell(SHOW_TYPE showType) {
        super();
        classNumber = new Label();
        moduleName = new Label();
        groupNumber = new Label();
        roomCode = new Label();
        teacherName = new Label();
        timesLot = new Label();
        content = new HBox(classNumber, moduleName, groupNumber, teacherName, roomCode, timesLot);
        content.setSpacing(10);
        this.showType = showType;
    }

    @Override
    protected void updateItem(TimesResult timeResult, boolean empty) {
        super.updateItem(timeResult, empty);
        if (timeResult != null && !empty) { // <== test for null item and empty parameter
            if (showType == SHOW_TYPE.GROUP) {
                groupNumber.setText("Grupo: " + timeResult.getGroupNumber());
                content = new HBox(groupNumber);
            } else if (showType == SHOW_TYPE.TEACHER) {
                teacherName.setText("Profesor: " + timeResult.getTeacherName());
                content = new HBox(teacherName);
            } else if (showType == SHOW_TYPE.MODULE) {
                moduleName.setText("Asignatura: " + timeResult.getModuleName());
                content = new HBox(moduleName);
            } else if (showType == SHOW_TYPE.ROOM) {
                roomCode.setText("Aula: " + timeResult.getRoomCode());
                content = new HBox(roomCode);
            }
            setGraphic(content);
        } else {
            setGraphic(null);
        }
    }
}
