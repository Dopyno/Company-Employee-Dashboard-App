package com.example.logindemoapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppPage implements Initializable {
    @FXML
    private ImageView homeF, calculatorF, myAccF, jobsF;
    @FXML
    private Button logoutButton, homeButton, calculatorButton, myAccButton, jobsButton;
    @FXML
    private BorderPane homePane, calculatorPane, myAccPane, jobsPane;
    @FXML
    public void logOut(ActionEvent event) throws IOException {
        Main main = new Main();
       // main.changeScene("Login.fxml");
        main.switchPage(event, "Login.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void switchPage(ActionEvent event){
        if(event.getSource() == homeButton){
            viewHome();
        }else if (event.getSource() == calculatorButton) {
            viewCalculator();
        }else if (event.getSource() == myAccButton) {
            viewMyAccount();
        }else if (event.getSource() == jobsButton) {
            viewJobs();
        }
    }
    private void viewHome(){
        homePane.setVisible(true);
        calculatorPane.setVisible(false);
        myAccPane.setVisible(false);
        jobsPane.setVisible(false);
    }
    private void viewCalculator(){
        homePane.setVisible(false);
        calculatorPane.setVisible(true);
        myAccPane.setVisible(false);
        jobsPane.setVisible(false);
    }
    private void viewMyAccount(){
        homePane.setVisible(false);
        calculatorPane.setVisible(false);
        myAccPane.setVisible(true);
        jobsPane.setVisible(false);
    }
    private void viewJobs(){
        homePane.setVisible(false);
        calculatorPane.setVisible(false);
        myAccPane.setVisible(false);
        jobsPane.setVisible(true);
    }
}
