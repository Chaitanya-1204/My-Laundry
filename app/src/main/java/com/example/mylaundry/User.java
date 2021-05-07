package com.example.mylaundry;

public class User {
    private String fullName;

    // string variable for storing
    // employee contact number
    private String phoneNumber;

    // string variable for storing
    // employee address.
    private String email;

    // an empty constructor is
    // required when using
    // Firebase Realtime Database.
    public User() {

    }

    // created getter and setter methods
    // for all our variables.
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
