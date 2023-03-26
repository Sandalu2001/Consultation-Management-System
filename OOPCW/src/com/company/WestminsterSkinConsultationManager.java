package com.company;
import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class WestminsterSkinConsultationManager implements SkinConsultationManager{
    private static final int maxNoOfDoctors = 10;
    Scanner sc = new Scanner(System.in);
    private int input;
    private int mobileNumber;
    private int medicalLicenceNumber;
    private String specification;
    private String patientId;


    public WestminsterSkinConsultationManager() {   //Default constructor

    }


    public void consoleMenu() throws medicalNumberException {
        loadTheDataFile();  //Load the files



            try{
                do {

                System.out.println("--Menu--");
                System.out.println("1\t:\tAdd a doctor");
                System.out.println("2\t:\tDelete a doctor");
                System.out.println("3\t:\tPrint the list of the doctors");
                System.out.println("4\t:\tSave all information in a file");
                System.out.println("5\t:\tLoad information from the file");
                System.out.println("6\t:\tOpen a graphical user interface(GUI)");
                System.out.println("7\t:\tPrint the list of the consultations");
                System.out.println("8\t:\tDelete a consultation");
                System.out.println("0\t:\tExit the console");



                System.out.print("\nEnter the input\t:");
                input = sc.nextInt();


                if(input == 0)
                    break;

                if(input == 1)
                    addDoctor();

                if(input == 2)
                    removeDoctor();

                if(input == 3){
                    getAllDoctors();
                }

                if(input == 4){
                    saveInAFile();
                }

                if(input == 5){
                    loadTheDataFile();
                }

                if(input == 6){
                    OpenGUI();
                }

                if(input == 7){
                    getAllConsultations();
                }

                if(input == 8){
                    removeConsultation();
                }

                if(input < 0 || input > 9){
                    System.out.println("Invalid input.");
                }

            } while (true);



            }
            catch (InputMismatchException e){
                System.out.println("Invalid Input.");
            }

            catch (Exception e){
                System.out.println(e.getMessage());
            }






    }

    @Override
    public void addDoctor(){
        try{
            if(Doctor.getDoctorArrayList().size() != maxNoOfDoctors){
                System.out.print("Enter the name\t:");
                String name = sc.next();

                System.out.print("Enter the surname\t:");
                String surname = sc.next();

                System.out.print("Enter the date of birth\t:");
                String dateOfBirth = sc.next();


                System.out.print("Enter the mobile number\t:");
                mobileNumber = sc.nextInt();

                System.out.print("Enter the medical licence number\t:");
                medicalLicenceNumber = sc.nextInt();

                /*boolean x = true;
                while(x){
                    for(Doctor doctor : Doctor.getDoctorArrayList()) {
                        if (doctor.getMedicalLicenceNumber() == medicalLicenceNumber) {
                            System.out.println("Medical number is already exists.");
                            System.out.print("Enter the medical licence number\t:");
                            medicalLicenceNumber = sc.nextInt();
                        } else {
                            System.out.println("false");
                        }
                    }
                    break;
                }*/

                sc.nextLine();
                System.out.print("Enter the specification(cosmetic dermatology / paediatric dermatology / medical dermatology)\t:");
                specification =  sc.nextLine();

                Doctor.AddDoctor(new Doctor(name,surname,dateOfBirth,mobileNumber,medicalLicenceNumber,specification));
            }
            else{
                System.out.println("Error: maximum number of doctors reached");
            }
        }

        catch (InputMismatchException e){
            System.out.println("Invalid Input.");
            sc.nextLine();
        }

        catch (Exception e){
            System.out.println(e.getMessage());
        }

        saveInAFile();
    }





    @Override
    public void removeDoctor() {
        Doctor doctorTemp = null;

        try{
            System.out.print("Enter the medical licence number\t:");
            int medicalLicenceNumber = sc.nextInt();

            for(Doctor doctor : Doctor.getDoctorArrayList()){
                if( doctor.getMedicalLicenceNumber() == medicalLicenceNumber){
                    System.out.println(doctor.getName()+" was deleted.");
                    System.out.println("\n--Deleted doctor details--\n");
                    String s = doctor.toString();
                    System.out.println(s);
                    doctorTemp = doctor;
                    Doctor.RemoveDoctor(doctor);
                    break;
                }
            }

            if(doctorTemp == null){
                System.out.println("Doctor was not found.");
            }
        }

        catch (InputMismatchException e){
            System.out.println("The medical licence number is incorrect.");
            sc.nextLine();
        }

        catch (Exception e){
            System.out.println(e.getMessage());
            sc.nextLine();
        }

        saveInAFile();

    }

    @Override
    public void getAllDoctors() {
        try{
            for(Doctor d : Doctor.getDoctorArrayList()){
                System.out.println(d.toString());
            }
        }
        catch (IndexOutOfBoundsException e ){
            System.out.println("Index out of bounds in the array.");
        }

        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }


    public static void saveInAFile()  {
        {
            ArrayList<Doctor> doctors = Doctor.getDoctorArrayList();
            ArrayList<Consultation> consultations = Consultation.getConsultationArrayList();


            try {
                //--Save doctorArraylist to the doctor.txt file--//

                // Create a FileOutputStream to write the doctors ArrayList and consultation Arraylist to data.dat file
                FileOutputStream doctorFos = new FileOutputStream("doctor.txt");

                // Create an ObjectOutputStream to write ArrayLists to the FileOutputStream
                ObjectOutputStream doctorOos = new ObjectOutputStream(doctorFos);

                // Write the ArrayList to the ObjectOutputStream
                doctorOos.writeObject(doctors);

                // Close the ObjectOutputStream
                doctorOos.close();



                //--Save consultationArraylist to the doctor.txt file--//

                // Create a FileOutputStream to write the doctors ArrayList and consultation Arraylist to data.dat file
                FileOutputStream consultationFos = new FileOutputStream("consultation.txt");

                // Create an ObjectOutputStream to write ArrayLists to the FileOutputStream
                ObjectOutputStream consultationOos = new ObjectOutputStream(consultationFos);

                // Write the ArrayList to the ObjectOutputStream
                consultationOos.writeObject(consultations);

                // Close the ObjectOutputStream
                consultationOos.close();


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void loadTheDataFile(){

        Path doctorPath = Paths.get("doctor.txt");
        Path consultationPath = Paths.get("consultation.txt");


        if(Files.exists(doctorPath) && Files.exists(consultationPath)){
            try {
                // Create a FileInputStream to read the ArrayList from a file
                FileInputStream doctorFis = new FileInputStream("doctor.txt");

                // Create an ObjectInputStream to read the ArrayList from the FileInputStream
                ObjectInputStream doctorOis = new ObjectInputStream(doctorFis);

                // Read the ArrayList from the ObjectInputStream
                ArrayList<Doctor> doctors = (ArrayList<Doctor>) doctorOis.readObject();

                //Add doctors arraylist to the static arraylist in doctor class
                Doctor.setDoctorArrayList(doctors);

                System.out.println("Data has been loaded.");

                // Close the ObjectInputStream
                doctorOis.close();


                // Create a FileInputStream to read the ArrayList from a file
                FileInputStream consultationFis = new FileInputStream("consultation.txt");

                // Create an ObjectInputStream to read the ArrayList from the FileInputStream
                ObjectInputStream consultationOis = new ObjectInputStream(consultationFis);

                // Read the ArrayList from the ObjectInputStream
                ArrayList<Consultation> consultations = (ArrayList<Consultation>) consultationOis.readObject();

                //Add consultation arraylist to the static arraylist in consultation class
                Consultation.setConsultationArrayList(consultations);

                // Close the ObjectInputStream
                consultationOis.close();
            }
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("NO path");
        }
    }

    @Override
    public void getAllConsultations() {

        ArrayList<Consultation> consultations = Consultation.getConsultationArrayList();

        try {
            for(Consultation consultation : consultations){
                System.out.println("Date and Time\t:\t"+consultation.getDateAndTime());
                System.out.println("Patient\t:\t"+consultation.getPatient().getName()+" "+consultation.getPatient().getSurname());
                System.out.println("Doctor\t:\t"+consultation.getDoctor().getName()+" "+consultation.getDoctor().getSurname());
                System.out.println("Cost\t:\t"+consultation.getCost()+"\n");
            }
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("Index out of bounds in the array.");
        }

    }


    public void removeConsultation() {

        try{
            System.out.print("Enter the patient Id\t:");
            patientId = sc.next();

            System.out.print("Enter the medical licence number\t:");
            medicalLicenceNumber = sc.nextInt();
        }

        catch (InputMismatchException e){
            System.out.println("Invalid Input!.");
        }

        Consultation consultationTemp = null;

        try{

            for(Consultation consultation : Consultation.getConsultationArrayList()){
                boolean medicalNumberChecker = consultation.getDoctor().getMedicalLicenceNumber() == medicalLicenceNumber;
                boolean patientIdChecker = consultation.getPatient().getPatientId().equals(patientId);
                if( medicalNumberChecker && patientIdChecker ){
                    System.out.println("\n--Consultation was deleted.--\n");
                    consultationTemp = consultation;
                    Consultation.RemoveConsultation(consultation);
                    break;
                }
            }

            if(consultationTemp == null){
                System.out.println("Consultation was not found.");
            }
            saveInAFile();
        }

        catch (IndexOutOfBoundsException e ){
            System.out.println("Index out of bounds in the array.");
        }

        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void OpenGUI() {
        MainGUI showBorderLayout = new MainGUI();

        showBorderLayout.setSize(1200,1000);
        showBorderLayout.setVisible(true);
        showBorderLayout.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
}
