package com.horarios.horariosapp.views;

import com.horarios.horariosapp.data.TimesResult;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

public class TimesByGroupResultListCell extends ListCell<TimesResult> {

    private TableView<TimesResult> table = new TableView<TimesResult>();
    final HBox hb = new HBox();

    @Override
    protected void updateItem(TimesResult timeResult, boolean empty) {
        super.updateItem(timeResult, empty);
        /*if (timeResult != null && !empty) { // <== test for null item and empty parameter
            TableColumn times = new TableColumn("Horas");
            times.setMinWidth(100);
            times.setCellValueFactory(new PropertyValueFactory<TimesResult, String>("firstName"));

            TableColumn monday = new TableColumn("Lunes");
            monday.setMinWidth(100);
            monday.setCellValueFactory(new PropertyValueFactory<TimesResult, String>("lastName"));

            TableColumn tuesday = new TableColumn("Lunes");
            monday.setMinWidth(100);
            monday.setCellValueFactory(new PropertyValueFactory<TimesResult, String>("lastName"));

            TableColumn wednesday = new TableColumn("Lunes");
            monday.setMinWidth(100);
            monday.setCellValueFactory(new PropertyValueFactory<TimesResult, String>("lastName"));

            TableColumn thursday = new TableColumn("Lunes");
            monday.setMinWidth(100);
            monday.setCellValueFactory(new PropertyValueFactory<TimesResult, String>("lastName"));

            TableColumn friday = new TableColumn("Lunes");
            monday.setMinWidth(100);
            monday.setCellValueFactory(new PropertyValueFactory<TimesResult, String>("lastName"));

            table.setItems(timeResult);
            table.getColumns().addAll(times, monday, tuesday, wednesday, thursday, friday);
            setGraphic(content);
        } else {
            setGraphic(null);
        }*/
    }
}
