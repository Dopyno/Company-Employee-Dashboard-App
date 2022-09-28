package com.example.logindemoapp;
import PersonModel.PersonModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.*;

public class LoginController{
   @FXML
   public Button loginButton, backButton, cancelButton, signupButton, registerButton, clearButton;
   @FXML
   public TextField userField, userRegisterField, passField2, passField3, nameField, emailField, phoneField;
   @FXML
   public PasswordField passField;
   @FXML
   public Label messageLabel, validationLabel;

   private Stage stage;
   private Scene scene;
   private Parent root;
   private DataSingleton data = null;

   @FXML
   public void initialize(){
      data = new DataSingleton();
   }

   @FXML
   private void receiveData(DataSingleton parentData) {
      data = parentData;
   }


   @FXML
   public void setClearButton(ActionEvent event) {
      if (event.getSource() == clearButton) {
         this.clearForm();
      }
   }

   @FXML
   public void setCancelButton(ActionEvent event) {
      if (event.getSource() == cancelButton) {
         userField.setText("");
         passField.setText("");
      }
   }

   @FXML
   public void switchToLoginPage(ActionEvent event) throws IOException {
      // createNewPersonModel(ActionEvent event);
      Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
      stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
   }
   @FXML
   public void switchToRegisterPage(ActionEvent event) throws IOException {
      root = FXMLLoader.load(getClass().getResource("PersonModel.fxml"));
      stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
   }

   @FXML
   public void createNewPersonModel(ActionEvent event) throws IOException{
      if (data.file.isFile()) {
         try {
            while (!userRegisterField.getText().isEmpty()) {
               if (!passField2.getText().equals(passField3.getText())) {
                  validationLabel.setText("Password not match!Please try again!");
                  this.clearForm();
               }else if(userRegisterField.getText().isEmpty() && passField2.getText().isEmpty()){
                  validationLabel.setText("Please create your own account");
               }else {
                  data.peopleList.add(new PersonModel(userRegisterField.getText(), passField2.getText(), nameField.getText(), emailField.getText(), phoneField.getText()));
                  System.out.println("Customer added successfully!");
                  validationLabel.setText("Details added successfully!");
              this.clearForm();
               }
            }
            data.savePersonsToFile();

         } catch (NotSerializableException e) {
         }
      }else if(!data.file.isFile()){
         System.out.println(data.peopleList.toString());
         while (!userRegisterField.getText().isEmpty()) {
            if (!passField2.getText().equals(passField3.getText())) {
               validationLabel.setText("Password not match!Please try again!");
               this.clearForm();
            }else if(userRegisterField.getText().isEmpty() && passField2.getText().isEmpty()){
               validationLabel.setText("Please create your own account");
            } else {
               data.addPerson(userRegisterField.getText(), passField2.getText(), nameField.getText(), emailField.getText(), phoneField.getText());
               validationLabel.setText("Details added successfully!");
               this.clearForm();
            }
         }
         data.savePersonsToFile();
      }else {
         System.out.println("File not found...!");
      }
   }

   public void clearForm() {
      userRegisterField.setText("");
      passField2.setText("");
      passField3.setText("");
      nameField.setText("");
      emailField.setText("");
      phoneField.setText("");
   }
   @FXML
   public void loginToProgram(ActionEvent event) throws IOException, ClassNotFoundException{
      Main main = new Main();
      data.setUserName(userField.getText());
      System.out.println("button selected");
      if (data.file.isFile()) {  // check if file.txt exist
         checkUserAndPassword(main, event ,data.peopleList, userField, passField);
      }else if(!data.file.isFile()){   // check if file.txt  NOT exist
         messageLabel.setText("Please use a valid account!");
         this.checkUserAndPassword(main, event ,data.peopleList, userField, passField);
      }else {
         messageLabel.setText("Please use a valid account!");
         System.out.println("File not found...!");
      }
   }



   @FXML
   public void checkUserAndPassword(Main main, ActionEvent event, ArrayList<PersonModel> list, TextField user, PasswordField password)throws IOException{

      for (int i = 0; i < list.size(); i++){
         if(user.getText().toString().equals(list.get(i).getUserName()) && password.getText().toString().equals(list.get(i).getPassword())){
            messageLabel.setText("Login successfully!");
            //  main.changeScene("MainPage.fxml");
            main.switchToMainPage(event, user.getText().toString());
         }else if(user.getText().isEmpty() && password.getText().isEmpty()){
            messageLabel.setText("Please use a valid account!");
         }else {
            messageLabel.setText("Wrong Username and Password!Please try again!");
         }
      }
   }
}

