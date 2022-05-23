module com.horarios.horarios {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.logging;

    opens com.horarios.main to javafx.fxml;
    exports com.horarios.main;
}