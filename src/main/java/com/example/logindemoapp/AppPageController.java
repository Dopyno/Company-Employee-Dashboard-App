package com.example.logindemoapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppPageController implements Initializable {
    private DataSingleton data = null;
    private Main main = null;

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
        main.switchPage(event, "Login.fxml");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        main = new Main();
    }
    @FXML
    public void switchHomePage(ActionEvent event){
        if(event.getSource() == homeButton){
           homePane.setVisible(true);
        }
    }
    @FXML
    private void calculatorScreen(ActionEvent event) throws IOException {
        String page = "CalculatorPage.fxml";
        FXMLLoader loader = main.loadResource(page);
        AnchorPane view = main.loadPane(loader, page);
        System.out.println(loader);
        CalculatorPage controller = loader.getController();
        controller.initData(data);

//        main.stage.show();

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
        AnchorPane view = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        borderPane.setCenter(view);
    }

    public void initData(DataSingleton parentData){
        data = parentData;
    }

}
