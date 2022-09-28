module com.example.logindemoapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.logindemoapp to javafx.fxml;
    exports com.example.logindemoapp;
    exports dao;
    opens dao to javafx.fxml;
    exports views;
    opens views to javafx.fxml;
    exports controllers;
    opens controllers to javafx.fxml;
}