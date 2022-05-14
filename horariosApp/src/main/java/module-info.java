module com.horarios.horariosapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires commons.collections4;
    requires mysql.connector.java;
    requires xmlbeans;
    requires java.sql;

    opens com.horarios.horariosapp to javafx.fxml;
    opens com.horarios.horariosapp.controllers to javafx.fxml ;

    exports com.horarios.horariosapp;
    exports com.horarios.horariosapp.controllers;
    opens com.horarios.horariosapp.controllers.base to javafx.fxml;
}