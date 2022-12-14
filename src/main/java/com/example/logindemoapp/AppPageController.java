package com.example.logindemoapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppPageController implements Initializable {
    @FXML
    private WebView webView;
    private WebEngine engine;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Button homeButton, myAccButton, calculatorButton, jobsButton;
    @FXML
    private Button logoutButton;
    @FXML
    private AnchorPane homePane;
    @FXML
    public void logOut(ActionEvent event) throws IOException {
        Main main = new Main();
        main.switchPage(event, "Login.fxml");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        engine = webView.getEngine();
        loadPage();
    }
    public void loadPage(){
        engine.load("https://www.hhrec.co.uk/");
    }

    @FXML
    private void calculatorScreen(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("CalculatorPage.fxml"));
        borderPane.setCenter(view);
    }
    @FXML
    private void myAccountScreen(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("MyAccountPage.fxml"));
        borderPane.setCenter(view);
    }
    @FXML
    private void jobsScreen(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("JobsPage.fxml"));
        borderPane.setCenter(view);
    }
    @FXML
    private void homeScreen(ActionEvent event) throws IOException {
       Main main = new Main();
       main.switchPage(event, "MainPage.fxml");
    }
}
