package com.example.logindemoapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    double x, y;
    private static Stage stg;
    private Stage stage;
    private Scene scene;
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException{
        try {
            stg = stage;
            stage.setResizable(false);
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root, 1000, 800);
            stage.setTitle("LoginDemoApp");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    public void changeScene(String fxml)throws IOException{
//        stg.setResizable(false);
//        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
//        stg.getScene().setRoot(pane);
//    }
    public void switchPage(ActionEvent event, String fxml) throws IOException {
        // createNewPersonModel(ActionEvent event);
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}