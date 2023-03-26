package com.company;

import java.io.Serializable;

public class Person implements Serializable {
    private  String name;
    private  String surname;
    private  String dateOfBirth;
    private  int mobileNumber;


    public Person(String name,String surname,String dateOfBirth,int mobileNumber){
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;

    }

    public Person(){

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }


    @Override
    public String toString() {
        return
                "Name='" + name + '\'' +
                ", Surname='" + surname + '\'' +
                ", DateOfBirth='" + dateOfBirth + '\'' +
                ", MobileNumber=" + mobileNumber + '\'';
    }
}
