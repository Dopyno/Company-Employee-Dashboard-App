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
            this.loadContactListFromTxt();
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
               }else if(userRegisterField.getText().isEmpty() && passField2.getText().isEmpty()){
                  validationLabel.setText("Please create your own account");
               }else {
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
      }else if(!file.isFile()){
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
            }else if(userRegisterField.getText().isEmpty() && passField2.getText().isEmpty()){
               validationLabel.setText("Please create your own account");
            } else {
               peopleList.add(new PersonModel(userRegisterField.getText(), passField2.getText(), nameField.getText(), emailField.getText(), phoneField.getText()));
               System.out.println("Added successfully!");
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
      }else {
         System.out.println("File not found...!");
      }
   }

   DataSingleton data = DataSingleton.getInstance();
   @FXML
   public void loginToProgram(ActionEvent event) throws IOException, ClassNotFoundException{
      Main main = new Main();

      System.out.println("button selected");
      if (file.isFile()) {  // check if file.txt exist
//         ois = new ObjectInputStream(new FileInputStream(file));  //Check if our file is existing(created) and load it. (ObjectInputStream)- to read data
//         peopleList = (ArrayList<PersonModel>)ois.readObject();   // parse our Arraylist Contact
//         ois.close();
         this.loadContactListFromTxt(); // load the list from out text file.
         this.displayListInConsole();  // test purpose
         checkUserAndPassword(main, event ,peopleList, userField, passField);
      }else if(!file.isFile()){   // check if file.txt  NOT exist
         messageLabel.setText("Please use a valid account!");
         this.displayListInConsole();  // test purpose
         this.checkUserAndPassword(main, event ,peopleList, userField, passField);
      }else {
         messageLabel.setText("Please use a valid account!");
         System.out.println("File not found...!");
      }
   }
   public void loadContactListFromTxt(){
      try {
         ois = new ObjectInputStream(new FileInputStream(file));  //Check if our file is existing(created) and load it. (ObjectInputStream)- to read data
         peopleList = (ArrayList<PersonModel>)ois.readObject();   // parse our Arraylist Contact
         ois.close();
      } catch (IOException e) {
         System.out.println(e);
      } catch (ClassNotFoundException e) {
        System.out.println(e);
      }
   }
   public void saveContactListToTxt()throws IOException{
      oos = new ObjectOutputStream(new FileOutputStream(file));   // After Contact list was created by user is time to store data in that File.txt
      oos.writeObject(peopleList);                                  // ObjectOutputStream is created
      oos.close();
   }
   public void displayListInConsole(){
      System.out.println("---------------------------------------------------------");
      listIterator = peopleList.listIterator();   //just to test the list
      while (listIterator.hasNext()) {
         System.out.println(listIterator.next());
      }
      System.out.println("---------------------------------------------------------");
   }
   @FXML
   public void checkUserAndPassword(Main main, ActionEvent event, ArrayList<PersonModel> list, TextField user, PasswordField password)throws IOException{
      PersonModel parseUser = list.get(list.indexOf(user.getText().toString()));
      System.out.println(parseUser.toString());
      for (int i = 0; i < list.size(); i++){

         if(user.getText().toString().equals(list.get(i).getUserName()) && password.getText().toString().equals(list.get(i).getPassword())){
            messageLabel.setText("Login successfully!");
          //  data.setUser(user);
            //  main.changeScene("MainPage.fxml");
            main.switchPage(event, "MainPage.fxml");
         }else if(user.getText().isEmpty() && password.getText().isEmpty()){
            messageLabel.setText("Please use a valid account!");
         }else {
            messageLabel.setText("Wrong Username and Password!Please try again!");
         }
      }
   }


}

