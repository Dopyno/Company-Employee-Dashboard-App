package com.example.logindemoapp;

import PersonModel.PersonModel;

public class DataSingleton {
    private static final DataSingleton instance = new DataSingleton();
    private PersonModel person;

    private DataSingleton() {
    }
    public static DataSingleton getInstance(){
        return instance;
    }
    public String getUserName(){
        return person.getUserName();
    }
  public void setUser(PersonModel user){
        this.person = user;
  }
}
