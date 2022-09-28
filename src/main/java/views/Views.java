package views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Views {
    private static Views INSTANCE;
    public Stage stage;
    public Scene scene;
    private int width = 1000;
    private int height = 800;
    private Parent parent = null;

    @FXML
    public BorderPane borderPane;

    public static final String VIEW_LOGIN_PAGE = "Views/fxml/Login.fxml";
    public static final String VIEW_HOME_PAGE = "Views/fxml/HomePage.fxml";
    public static final String VIEW_JOB_PAGE = "Views/fxml/JobsPage.fxml";
    public static final String VIEW_CALCULATOR_PAGE = "Views/fxml/CalculatorPage.fxml";
    public static final String VIEW_MAIN_PAGE = "Views/fxml/MainPage.fxml";
    public static final String VIEW_MY_ACCOUNT_PAGE = "Views/fxml/MyAccountPage.fxml";
    public static final String VIEW_PERSON_MODEL_PAGE = "Views/fxml/PersonModel.fxml";
    public Views setStage(Stage stage) {
        this.stage = stage;
        return this;
    }

    public static Views getInstance() throws IOException {
        if(INSTANCE == null){
            INSTANCE = new Views();
        }

        return INSTANCE;
    }

    public void switchPage(String fxml) throws IOException {
        this.parent = FXMLLoader.load(getClass().getResource(fxml));
        this.scene = new Scene(this.parent, this.width, this.height);
        this.stage.setScene(scene);
        this.stage.setResizable(false);
        this.stage.setTitle("LoginDemoApp");
        this.stage.show();
    }

    public void switchPane(String pane) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource(pane));
        borderPane.setCenter(view);
    }

}
