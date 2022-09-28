package com.example.logindemoapp;
import javafx.application.Application;
import javafx.stage.Stage;
import views.Views;

import java.io.IOException;

public class Main extends Application {
    private Views views = null;
    @Override
    public void start(Stage primaryStage) {
        try {
            views = Views.getInstance().setStage(primaryStage);
            views.switchPage(Views.VIEW_LOGIN_PAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}