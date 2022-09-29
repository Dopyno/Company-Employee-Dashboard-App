package com.example.logindemoapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class CalculatorPage {
    DataSingleton data = DataSingleton.getInstance();
    LoginController login = new LoginController();
    private List<TimeSheets> list = new ArrayList<>();

    @FXML
    private TextField nameField, jobField, hoursField;
    @FXML
    private Button addWorkButton, calculateButton, commentButton, clearButton;
    @FXML
    private TextArea invoice, textLive;
    @FXML
    private Label messageLabel;
    int driverRate = 15, warehouseRate = 10;
    @FXML
    public void addToCalculator(ActionEvent event) {
        if (nameField.getText().isEmpty()) {
            messageLabel.setText("Please enter a valid record!");
        }else {
            try {
                int hours = Integer.parseInt(hoursField.getText());
                list.add(new TimeSheets(nameField.getText(), jobField.getText(), hours));
                messageLabel.setText("Your entry was added successfully!");
                textLive.appendText("You work for " + nameField.getText() + " as a " + jobField.getText() + " for " + hoursField.getText() + " hours\n");
                textLive.setWrapText(true);
                clearFields();
                //calculate();
            } catch (NumberFormatException e) {
                System.out.println(e);
                messageLabel.setText("Please enter a number in Worked Hour section!");
                clearFields();
            }
        }
    }

    @FXML
    public void calculate(ActionEvent event){
        int total = 0;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getJobType().toLowerCase().contains("driver")){
                total += incomeCalculator(driverRate, list.get(i).getWorkedHours());
            } else if (list.get(i).getJobType().toLowerCase().contains("warehouse")) {
                total += incomeCalculator(warehouseRate, list.get(i).getWorkedHours());
            }
        }
        for (int i = 0; i < login.peopleList.size(); i++) {
            if(login.peopleList.get(i).getUserName().equals(data.getUserName())){
                invoice.appendText(login.peopleList.get(i).getFullName().toString() + "\n");
            }
        }
        invoice.appendText("Your total income for this week is: £" + total + "\n");
        invoice.appendText("An copy of your invoice will be sent on your email address, after we will check if the hours correspond to our records.");
        invoice.setWrapText(true);
    }
    @FXML
    private void clearFields(){
        nameField.setText("");
        jobField.setText("");
        hoursField.setText("");
    }
    public int incomeCalculator(int hourRate, int totalHours){
        int total;
        total = hourRate * totalHours;
        return total;
    }
}
