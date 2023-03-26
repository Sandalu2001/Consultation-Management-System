package com.company;

import java.util.ArrayList;

public class Patient extends Person{
    private String patientId;
    private static ArrayList<Patient> patientArrayList = new ArrayList<>();


    public Patient(String name,String surname,String dateOfBirth,int mobileNumber,String patientId){
        super(name, surname, dateOfBirth, mobileNumber);
        this.patientId = patientId;

    }


    public static void AddPatient(Patient patient) {
        patientArrayList.add(patient);
    }

    public static void RemoveDoctor(Patient patient){
        patientArrayList.remove(patient);
    }

    public static ArrayList<Patient> getPatientArrayList() {
        return patientArrayList;
    }

    public static void setPatientArrayList(ArrayList<Patient> patientArrayList) {
        Patient.patientArrayList = patientArrayList;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}
