package com.example.logindemoapp;

import PersonModel.PersonModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MyAccountPage implements Initializable {
    private DataSingleton data = null;

    @FXML
    private Label messageInfo;
    @FXML
    private TextArea textInfo;
    @FXML
    private Button accInfoButton, submitInfoButton, clearInfoButton, deleteInfoButton;
    @FXML
    private TextField userFieldInfo, passField2Info, passField3Info, nameFieldInfo, phoneFieldInfo, emailFieldInfo;
    @FXML
    public void clearInfoFields(){
        userFieldInfo.setText("");
        passField2Info.setText("");
        passField3Info.setText("");
        nameFieldInfo.setText("");
        phoneFieldInfo.setText("");
        emailFieldInfo.setText("");
    }

    public void initData(DataSingleton parentData){
        data = parentData;
    }


    @FXML
    public void viewInfo(){

//            System.out.println(data.getUserName());
            for (PersonModel model : data.peopleList){
                if (data.getUserName().equals(model.getUserName())){
                    textInfo.setText(model.toString());
                    textInfo.setWrapText(true);
                    System.out.println(model);
                }
            }
    }
    @FXML
    public void initialize(){
        viewInfo();
    }
    @FXML
    public void submitAccountChanges(ActionEvent event)throws IOException {
        int index = 0;
        System.out.println(data.peopleList);

        if (event.getSource() == submitInfoButton){
            System.out.println(data.getUserName());
            for (int i = 0; i < data.peopleList.size(); i++){
                if(data.getUserName().equals(data.peopleList.get(i).getUserName())){
                    index = i;
                }
            }
            if (userFieldInfo.getText().isEmpty() && passField2Info.getText().isEmpty() && passField3Info.getText().isEmpty() && nameFieldInfo.getText().isEmpty() && emailFieldInfo.getText().isEmpty() && phoneFieldInfo.getText().isEmpty()) {
                messageInfo.setText("There is nothing to be changed!");
            } else if(!passField2Info.getText().equals(passField3Info.getText())){
                messageInfo.setText("Password not match");
                clearInfoFields();
            }else{
                data.peopleList.set(index, new PersonModel(userFieldInfo.getText(), passField2Info.getText(), nameFieldInfo.getText(), emailFieldInfo.getText(), phoneFieldInfo.getText()));
                messageInfo.setText("Changed successfully!");
                clearInfoFields();
            }

        }
        data.savePersonsToFile();  //load to txt
    }
    @FXML
    public void deleteAccount(ActionEvent event)throws IOException{
        if(event.getSource() == deleteInfoButton){
            for (int i = 0; i < data.peopleList.size(); i++){
                if(data.getUserName().equals(data.peopleList.get(i).getUserName())){
                    data.peopleList.remove(i);
                    System.out.println("Deleted account!");
                    messageInfo.setText("Your account has been deleted!");
                    Main main = new Main();
                    main.switchPage(event, "Login.fxml");
                }
            }

        }
        data.savePersonsToFile();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
