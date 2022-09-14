package com.example.logindemoapp;
import PersonModel.PersonModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class LoginController {
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
   public ArrayList<PersonModel> peopleList = new ArrayList<>();
   public File file = new File("PersonModelList.txt");
   private ObjectOutputStream oos = null;
   private ObjectInputStream ois = null;
   private ListIterator listIterator = null;
   @FXML
   public void setClearButton(ActionEvent event) {
      if (event.getSource() == clearButton) {
         userRegisterField.setText("");
         passField2.setText("");
         passField3.setText("");
         nameField.setText("");
         emailField.setText("");
         phoneField.setText("");
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
      if (file.isFile()) {
         try {
            this.loadContactList();
            System.out.println(peopleList.toString());
            while (!userRegisterField.getText().isEmpty()) {
               if (!passField2.getText().equals(passField3.getText())) {
                  validationLabel.setText("Password not match!Please try again!");
                  userRegisterField.setText("");
                  passField2.setText("");
                  passField3.setText("");
                  nameField.setText("");
                  emailField.setText("");
                  phoneField.setText("");
               } else {
                  peopleList.add(new PersonModel(userRegisterField.getText(), passField2.getText(), nameField.getText(), emailField.getText(), phoneField.getText()));
                  System.out.println("Customer added successfully!");
                  validationLabel.setText("Details added successfully!");
                  int i = 1;
                  for (PersonModel person : peopleList) {     //test purpose
                     System.out.println(i + ". " + person);
                     i++;
                  }
                  userRegisterField.setText("");
                  passField2.setText("");
                  passField3.setText("");
                  nameField.setText("");
                  emailField.setText("");
                  phoneField.setText("");
               }
            }
            oos = new ObjectOutputStream(new FileOutputStream(file));   // After Contact list was created by user is time to store data in that File.txt
            oos.writeObject(peopleList);                                  // ObjectOutputStream is created
            oos.close();

         } catch (NotSerializableException e) {
         }
      } else {
         System.out.println("File not found...!");
      }
   }
   @FXML
   public void loginToProgram(ActionEvent event) throws IOException, ClassNotFoundException{
      Main main = new Main();
      System.out.println("button selected");
      if (file.isFile()) {
//         ois = new ObjectInputStream(new FileInputStream(file));  //Check if our file is existing(created) and load it. (ObjectInputStream)- to read data
//         peopleList = (ArrayList<PersonModel>)ois.readObject();   // pharse our Arraylist Contact
//         ois.close();
           this.loadContactList(); // load the list from out text file.
         System.out.println("---------------------------------------------------------");
         listIterator = peopleList.listIterator();   //just to test the list
         while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
         }
         System.out.println("---------------------------------------------------------");
         for (int i = 0; i < peopleList.size(); i++){
            if(userField.getText().toString().equals(peopleList.get(i).getUserName()) && passField.getText().toString().equals(peopleList.get(i).getPassword())){
               messageLabel.setText("Login successfully!");
             //  main.changeScene("MainAppPage.fxml");
               main.switchPage(event, "MainAppPage.fxml");
            }else if(userField.getText().isEmpty() && passField.getText().isEmpty()){
                messageLabel.setText("Please use a valid account!");
            }else {
               messageLabel.setText("Wrong Username and Password!Please try again!");
            }
         }
      } else {
         System.out.println("File not found...!");
      }
   }
   private void loadContactList(){
      try {
         ois = new ObjectInputStream(new FileInputStream(file));  //Check if our file is existing(created) and load it. (ObjectInputStream)- to read data
         peopleList = (ArrayList<PersonModel>)ois.readObject();   // pharse our Arraylist Contact
         ois.close();
      } catch (IOException e) {
         System.out.println(e);
      } catch (ClassNotFoundException e) {
        System.out.println(e);
      }
   }

}

