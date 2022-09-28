package dao;

import java.io.Serializable;

public class Person implements Serializable {
    private String userName;
    private String password;
    private String fullName;
    private String emailAddress;
    private String phoneNumber;

    public Person(String userName, String password, String fullName, String emailAddress, String phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String address) {
        this.emailAddress = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Full detail:\n\tUsername: \"" + userName + "\" \n\tName: " +
                fullName + " \n\tEmail: " +  emailAddress +" \n\tPhone:" +  phoneNumber;
    }
}
