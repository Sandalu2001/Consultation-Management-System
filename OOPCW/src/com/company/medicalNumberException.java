package com.company;

public class medicalNumberException extends Exception{

    public medicalNumberException(){
        System.out.println("Medical licence number is incorrect.");
    }

    public medicalNumberException(int medicalNumber){
        for(Doctor doctor : Doctor.getDoctorArrayList()){
            if( doctor.getMedicalLicenceNumber() == medicalNumber){
                System.out.println("\nThe medical licence number is already exists.\n");
                break;
            }
        }
    }
}
