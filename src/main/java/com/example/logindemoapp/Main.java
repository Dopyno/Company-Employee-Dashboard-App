package com.example.logindemoapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;

import java.io.IOException;

public class Main extends Application {
    private static Stage stg;
    public Stage stage;
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
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        System.out.println(event);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public FXMLLoader loadResource(String name) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        name
                )
        );

        return loader;
    }

    public AnchorPane loadPane(FXMLLoader loader, String name) throws IOException {
        AnchorPane pane = loader.load(getClass().getResource(name));
        return pane;
    }

    public FXMLLoader setupPageSwitch(ActionEvent event, String page) throws IOException {
        FXMLLoader loader = this.loadResource(page);
        Stage pageStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        pageStage.setScene(
                new Scene(loader.load())
        );
        this.stage = pageStage;
        return loader;
    }

    public void switchToMainPage(ActionEvent event, String user) throws IOException {
        FXMLLoader loader = this.setupPageSwitch(event, "MainPage.fxml");
        System.out.println(loader);
        System.out.println(stage);

        DataSingleton data = new DataSingleton();
        data.setUserName(user);
        MyAccountPage controller = loader.getController();
        controller.initData(data);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}