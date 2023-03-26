package com.company;
import java.io.Serializable;
import java.util.ArrayList;

public class Doctor extends Person implements Serializable{
    private int medicalLicenceNumber;  //Doctor's licence no
    private String specification; //Doctor's specification
    private static ArrayList<Doctor> doctorArrayList = new ArrayList<>(10);

    public Doctor(){
    }

    public Doctor(String name,String surname,String dateOfBirth,int mobileNumber,int medicalLicenceNumber,String specification){
        super(name, surname, dateOfBirth, mobileNumber);
        this.medicalLicenceNumber = medicalLicenceNumber;
        this.specification = specification;
    }

    public static ArrayList<Doctor> getDoctorArrayList() {
        return doctorArrayList;
    }

    public static void setDoctorArrayList(ArrayList<Doctor> doctorArrayList) {
        Doctor.doctorArrayList = doctorArrayList;
    }

    public int getMedicalLicenceNumber() {
        return medicalLicenceNumber;
    }

    public void setMedicalLicenceNumber(int medicalLicenceNumber) {
        this.medicalLicenceNumber = medicalLicenceNumber;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }


     public static void AddDoctor(Doctor doctor) {
         doctorArrayList.add(doctor);
     }

     public static void RemoveDoctor(Doctor doctor){
        doctorArrayList.remove(doctor);
     }

    @Override
    public String toString() {
        return super.toString()+ ", MedicalLicenceNumber=" + medicalLicenceNumber +
                ", Specification='" + specification + '\'';
    }

}
