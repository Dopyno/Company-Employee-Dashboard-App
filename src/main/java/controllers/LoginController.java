package controllers;

import dao.Person;
import dao.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import views.Views;

import java.io.IOException;
import java.io.NotSerializableException;
import java.net.URL;
import java.util.ArrayList;

public class LoginController{
   @FXML
   public Button loginButton, backButton, cancelButton, signupButton, registerButton, clearButton;
   @FXML
   public TextField userField, userRegisterField, passField2, passField3, nameField, emailField, phoneField;
   @FXML
   public PasswordField passField;
   @FXML
   public Label messageLabel, validationLabel;

   private Views views;
   private Store data = null;

   public LoginController() throws Exception {
      views = Views.getInstance();
      data = Store.getInstance();
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

//   @FXML
   public void switchToLoginPage(ActionEvent event) throws IOException {
      views.switchPage(Views.VIEW_LOGIN_PAGE);
   }
   @FXML
   public void switchToRegisterPage() throws IOException {
      views.switchPage(Views.VIEW_PERSON_MODEL_PAGE);
   }

   @FXML
   public void createNewPersonModel() throws IOException{
      if (data.file.isFile()) {
         try {
            while (!userRegisterField.getText().isEmpty()) {
               if (!passField2.getText().equals(passField3.getText())) {
                  validationLabel.setText("Password not match!Please try again!");
                  this.clearForm();
               }else if(userRegisterField.getText().isEmpty() && passField2.getText().isEmpty()){
                  validationLabel.setText("Please create your own account");
               } else {
                  data.addPerson(userRegisterField.getText(), passField2.getText(), nameField.getText(), emailField.getText(), phoneField.getText());
                  System.out.println("Customer added successfully!");
                  validationLabel.setText("Details added successfully!");
                  this.clearForm();
               }
            }
            data.savePersonsToFile();

         } catch (NotSerializableException e)
         {
            System.out.println(e.getMessage());
            System.exit(0);
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
   public void loginToProgram() throws IOException, ClassNotFoundException{
      System.out.println("button selected");
      if (this.data.file.isFile()) {  // check if file.txt exist
         checkUserAndPassword(data.peopleList, userField, passField);
      }else if(!this.data.file.isFile()){   // check if file.txt  NOT exist
         messageLabel.setText("Please use a valid account!");
         this.checkUserAndPassword(data.peopleList, userField, passField);
      }else {
         messageLabel.setText("Please use a valid account!");
         System.out.println("File not found...!");
      }
   }



   @FXML
   public void checkUserAndPassword(ArrayList<Person> list, TextField user, PasswordField password)throws IOException{
      for (int i = 0; i < list.size(); i++){
         if(user.getText().toString().equals(list.get(i).getUserName()) && password.getText().toString().equals(list.get(i).getPassword())){
            messageLabel.setText("Login successfully!");
            this.data.setUserName(userField.getText());
            views.switchPage(Views.VIEW_MAIN_PAGE);
         }else if(user.getText().isEmpty() && password.getText().isEmpty()){
            messageLabel.setText("Please use a valid account!");
         }else {
            messageLabel.setText("Wrong Username and Password!Please try again!");
         }
      }
   }
}

