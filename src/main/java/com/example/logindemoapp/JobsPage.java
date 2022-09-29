package com.example.logindemoapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class JobsPage implements Initializable {
    @FXML
    private WebView webView;
    private WebEngine engine;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        engine = webView.getEngine();
        loadPage();
    }
    public void loadPage(){
        engine.load("https://www.totaljobs.com/jobs/class-1?q=class+1+&searchOrigin=Resultlist_top-search");
    }
}
