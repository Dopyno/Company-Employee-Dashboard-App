package com.example.logindemoapp;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class AppPage {
    private Button logoutButton;
    public void logOut(ActionEvent e) throws IOException {
        Main main = new Main();
        main.changeScene("Login.fxml");
    }
}
