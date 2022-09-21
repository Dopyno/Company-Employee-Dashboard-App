package com.example.logindemoapp;

import PersonModel.PersonModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MyAccountPage {
    @FXML
    private Label message;
    @FXML
    private TextArea textInfo;
    @FXML
    private Button accInfoButton, submitButton, clearButton, deleteButton;
    @FXML
    private TextField userField, passField2, passField3, nameField, phoneField, emailField;
    LoginController control = new LoginController();
//    @FXML
//    public void viewAccountInfo(){
//        control.loadContactListFromTxt();
//        textInfo.clear();
//        int index = control.peopleList.indexOf(control.peopleList.contains(control.userName));
//       String temp = String.valueOf(control.peopleList.get(index).toString());
//       textInfo.appendText(temp);
//        System.out.println(control.peopleList.get(index));
//    }
//    @FXML
//    public void deleteAccount() throws IOException {
//        control.loadContactListFromTxt();
//        control.peopleList.remove(control.index);
//        control.saveContactListToTxt();
//    }
    @FXML
    public void clearFields(){
        userField.setText("");
        passField2.setText("");
        passField3.setText("");
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
    }
//    @FXML
//    public void replaceDetailInfo()throws IOException{
//        control.loadContactListFromTxt();
//        while (!userField.getText().isEmpty()){
//            if(!passField2.getText().equals(passField3.getText())){
//                message.setText("Password not match!Please try again!");
//                clearFields();
//            } else if ((userField.getText().isEmpty() && passField2.getText().isEmpty() && passField3.getText().isEmpty()) && nameField.getText().isEmpty() && emailField.getText().isEmpty() && phoneField.getText().isEmpty()) {
//                message.setText("Nothing to be changed!");
//            }else{
//                control.peopleList.set(control.index, new PersonModel(userField.getText(), passField2.getText(), nameField.getText(), emailField.getText(), phoneField.getText()));
//                message.setText("Update successfully!");
//                clearFields();
//            }
//        }
//            control.saveContactListToTxt();
//    }

}
