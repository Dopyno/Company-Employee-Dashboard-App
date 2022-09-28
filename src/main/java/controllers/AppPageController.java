package controllers;

import dao.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import views.Views;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppPageController implements Initializable {
    private Views views;
    @FXML
    private Button homeButton;
    @FXML
    private AnchorPane homePane;

    public AppPageController() throws IOException {
        views = Views.getInstance();
    }

    @FXML
    public void logOut(ActionEvent event) throws IOException {
        views.switchPage(Views.VIEW_LOGIN_PAGE);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    @FXML
    public void switchHomePage(ActionEvent event){
        if(event.getSource() == homeButton){
           homePane.setVisible(true);
        }
    }
    @FXML
    private void calculatorScreen(ActionEvent event) throws IOException {
        views.switchPane(views.VIEW_CALCULATOR_PAGE);
   }
    @FXML
    private void myAccountScreen(ActionEvent event) throws IOException {
        views.switchPane(views.VIEW_MY_ACCOUNT_PAGE);
    }
    @FXML
    private void jobsScreen(ActionEvent event) throws IOException {
        views.switchPane(views.VIEW_JOB_PAGE);
    }
    @FXML
    private void homeScreen(ActionEvent event) throws IOException {
        views.switchPane(views.VIEW_HOME_PAGE);
    }

}
