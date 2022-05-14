package com.horarios.horariosapp.controllers;

import com.horarios.horariosapp.Application;
import com.horarios.horariosapp.controllers.base.BaseController;
import com.horarios.horariosapp.data.Aula;
import com.horarios.horariosapp.repository.Dao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class RoomsViewController extends BaseController {

    @FXML
    private ListView currentRoomsListView;

    @FXML
    private TextField roomTextField;

    @FXML
    private TextField capacityTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentRoomsListView.setCellFactory(TextFieldListCell.forListView());
        currentRoomsListView.setEditable(false);

        Dao dao = new Dao();
        ArrayList<Aula> rooms = dao.getAllRooms();

        if (rooms != null) {
            for (Aula room : rooms) {
                currentRoomsListView.getItems().add("salon: " + room.getRoomNumber() + " capacidad: " + room.getRoomCapacity());
            }
        }
    }

    public void onBackButtonClick(ActionEvent actionEvent) throws Exception {
        Parent window3 = FXMLLoader.load(Objects.requireNonNull(Application.class.getResource("times_creator_view.fxml")));
        Scene newScene = new Scene(window3);
        Stage mainWindow = (Stage)  ((Node)actionEvent.getSource()).getScene().getWindow();

        mainWindow.setScene(newScene);
    }

    public void onAddRoomButtonClick(ActionEvent actionEvent) {
        String roomText = roomTextField.getText();
        String capacityText = capacityTextField.getText();

        if (!roomText.isEmpty() && !capacityText.isEmpty()) {
            if (isNumeric(capacityText)) {
                currentRoomsListView.setCellFactory(TextFieldListCell.forListView());
                currentRoomsListView.setEditable(false);
                currentRoomsListView.getItems().add("salon: " + roomText + " capacidad: " + capacityText);

                Aula room = new Aula();
                room.setRoomNumber(roomText);
                room.setCapacity(Integer.parseInt(capacityTextField.getText()));

                Dao dao = new Dao();
                String result = dao.insertRoom(room);

                System.out.println(result);

                roomTextField.setText("");
                capacityTextField.setText("");

                Stage mainWindow;
                mainWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                mainWindow.show();
            } else {
                showErrorAlertDialog("El campo capacidad debe ser numerico");
            }
        } else {
            showWarningAlertDialog("Debe completar todos los campos");
        }
    }
}
