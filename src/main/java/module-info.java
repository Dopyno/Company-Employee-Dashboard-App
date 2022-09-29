module com.example.logindemoapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.logindemoapp to javafx.fxml;
    exports com.example.logindemoapp;
    exports PersonModel;
    opens PersonModel to javafx.fxml;
}