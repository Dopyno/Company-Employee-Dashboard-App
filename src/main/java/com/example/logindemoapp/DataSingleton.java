package com.example.logindemoapp;

import PersonModel.PersonModel;

import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;

public class DataSingleton {
    private static final DataSingleton instance = new DataSingleton();
    private String userName;

    private ListIterator listIterator = null;

    public ArrayList<PersonModel> peopleList = new ArrayList<>();

    public File file = new File("PersonModelList.txt");
    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;

    public DataSingleton() {
        this.loadPersonsFromFile();
        this.printPersonsToConsole();
    }

    public void addPerson(String userName, String password, String fullName, String emailAddress, String phoneNumber) {
        this.peopleList.add(new PersonModel(userName, password, fullName, emailAddress, phoneNumber));
        System.out.println("Added successfully!");
    }
    //public void DataSingleton getInstance(){
      //  return instance;
    //}
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }

    public void loadPersonsFromFile(){
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

    public void savePersonsToFile()throws IOException{
        oos = new ObjectOutputStream(new FileOutputStream(file));   // After Contact list was created by user is time to store data in that File.txt
        oos.writeObject(peopleList);                                  // ObjectOutputStream is created
        oos.close();
    }

    public void printPersonsToConsole(){
        System.out.println("---------------------------------------------------------");
        listIterator = peopleList.listIterator();   //just to test the list
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
        System.out.println("---------------------------------------------------------");
    }
}
