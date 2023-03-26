package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;


import javax.swing.filechooser.FileNameExtensionFilter;


public class AddConsultationGUI extends JFrame {

    private JTextField tName;
    private JTextField tSurName;
    private JTextField tDateOfBirth;
    private JTextField tMobileNo;
    private JTextField tUniqueId;
    private JTextField tCost;
    private JTextField tDate;
    private JTextField tTime;
    private JTextField tnotes;
    private JTextField tNoOfHours;
    private JTextField doctorTextField;
    private int noOfHours;
    private JTextField specificationTextField;
    private JLabel errorLabel = new JLabel();

    private JButton submitButton;
    private JButton photoButton;
    private JButton tUniqueIdButton;

    private Doctor doctor;
    private LocalDateTime localDateTime;
    private Patient patientTemp;
    private String uniqueId;

    private ArrayList<Patient> patientArrayList;



    private BufferedImage image;


    public AddConsultationGUI(Doctor doctor, int noOfHours , String SelectedDate , String SelectedTime , LocalDateTime localDateTime){

        this.noOfHours = noOfHours;
        this.doctor = doctor;
        this.localDateTime = localDateTime;
        this.patientArrayList = Patient.getPatientArrayList();


        //SetLayout to borderLayout in frame
        setLayout(new BorderLayout(5,5));

        //Create a JPanel called addConsultationPanel
        JPanel addConsultationPanel =new JPanel();

        //Add addConsultationPanel to the frame
        add(addConsultationPanel,BorderLayout.NORTH);

        //Set addConsultationPanel layout to BorderLayout
        addConsultationPanel.setLayout(new BorderLayout(3,3));
        addConsultationPanel.setBackground(new Color(134, 229, 255));

        //Create a doctorPanel
        JPanel doctorPanel =new JPanel();

        //--addConsultationPanel--//

        //Add doctorPanel to the addConsultationPanel
        addConsultationPanel.add(doctorPanel,BorderLayout.NORTH);
        doctorPanel.setLayout(new GridLayout(2,2));
        doctorPanel.setBackground(new Color(0, 129, 201));


        if (checkAvailability()){   //Check doctor is available or not

            doctorPanel.add(new JLabel("DOCTOR"));
            doctorTextField = new JTextField(doctor.getName());
            doctorTextField.setEditable(false);
            doctorPanel.add(doctorTextField);

            doctorPanel.add(new JLabel("SPECIFICATION"));
            specificationTextField = new JTextField(doctor.getSpecification());
            doctorPanel.add(specificationTextField);

        }
        else{
            errorLabel.setText("Doctor is not Available.You'll automatically allocated to another doctor" );
            errorLabel.setForeground(Color.red);

            ArrayList<Doctor> allocatedDoctors = Doctor.getDoctorArrayList();
            allocatedDoctors.remove(doctor); //Remove selected doctor from this array

            //Create random object
            Random rand = new Random();   //Create a Random object

            //get a random number below than doctorArrayList size.
            int randomIndex = rand.nextInt(allocatedDoctors.size());

            //Get a random doctor
            this.doctor = allocatedDoctors.get(randomIndex);

            doctorPanel.add(new JLabel("DOCTOR"));
            doctorTextField = new JTextField(this.doctor.getName());
            doctorTextField.setEditable(false);
            doctorPanel.add(doctorTextField);

            doctorPanel.add(new JLabel("SPECIFICATION"));
            doctorPanel.add(new JTextField(this.doctor.getSpecification()));

        }


        JPanel dataPanel = new JPanel();  //Create a JPanel called dataPanel to add text fields and labels
        add(dataPanel,BorderLayout.CENTER); //Add dataPanel to the frame
        dataPanel.setBackground(Color.cyan);

        dataPanel.setLayout(new GridLayout(11,2,5,5));

            tUniqueId = new JTextField();
            dataPanel.add(tUniqueId);
            tUniqueIdButton = new JButton("Unique ID");
            dataPanel.add(tUniqueIdButton);


            UniqueIdListener uniqueIdListener = new UniqueIdListener();
            tUniqueIdButton.addActionListener(uniqueIdListener);


            dataPanel.add(new JLabel("\t\tName"));
            tName = new JTextField();
            dataPanel.add(tName);
            tName.setEditable(false);

            dataPanel.add(new JLabel("\t\tSurName"));
            tSurName = new JTextField();
            dataPanel.add(tSurName);
            tSurName.setEditable(false);

            dataPanel.add(new JLabel("\t\tBirth Of Date"));
            tDateOfBirth = new JTextField();
            dataPanel.add(tDateOfBirth);
            tDateOfBirth.setEditable(false);

            dataPanel.add(new JLabel("\t\tMobile No"));
            tMobileNo = new JTextField();
            dataPanel.add(tMobileNo);
            tMobileNo.setEditable(false);

            dataPanel.add(new JLabel("\t\tCost"));
            tCost = new JTextField();
            tCost.setEditable(false);
            dataPanel.add(tCost);

            dataPanel.add(new JLabel("Date"));
            tDate = new JTextField(SelectedDate);
            tDate.setEditable(false);
            dataPanel.add(tDate);

            dataPanel.add(new JLabel("Time"));
            tTime = new JTextField(SelectedTime);
            tTime.setEditable(false);
            dataPanel.add(tTime);

            dataPanel.add(new JLabel("NoOfHours"));
            tNoOfHours = new JTextField(String.valueOf(noOfHours));
            tNoOfHours.setEditable(false);
            dataPanel.add(tNoOfHours);

            dataPanel.add(new JLabel("Notes"));
            tnotes = new JTextField();
            dataPanel.add(tnotes);


        //--actionPanel--//
        photoButton =new JButton("ADD A PHOTO");  //User can add a photo
        dataPanel.add(photoButton,BorderLayout.SOUTH);

        PhotoListener photoListener = new PhotoListener(); //
        photoButton.addActionListener(photoListener);

        submitButton =new JButton("SUBMIT");
        dataPanel.add(submitButton,BorderLayout.SOUTH);

        SubmitListener handler = new SubmitListener();
        submitButton.addActionListener(handler);

        submitButton.addActionListener(e -> dispose());


        //Add a JLabel to the frame
        add(errorLabel,BorderLayout.SOUTH);  //Label1
        errorLabel.setForeground(new Color(255,0,0));
        errorLabel.setFont(new Font("Arial", Font.BOLD, 20));

    }




    private class SubmitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {

            try{

                String name = tName.getText();
                String surname = tSurName.getText();
                String dateOfBirth = tDateOfBirth.getText();
                int mobileNo = Integer.parseInt(tMobileNo.getText());
                String uniqueId = tUniqueId.getText();
                double cost = Double.parseDouble(tCost.getText());
                String notes = tnotes.getText();

                //Create a patient object
                Patient patient = new Patient(name,surname,dateOfBirth,mobileNo,uniqueId);

                //Add patient object to patientArrayList in Patient class
                Patient.AddPatient(new Patient(name,surname,dateOfBirth,mobileNo,uniqueId));

                //Create a consultation object
                Consultation consultation = new Consultation(localDateTime,cost,notes,doctor,patient, image);

                //Add consultation object to consultationArrayList in Patient class
                Consultation.AddConsultation(consultation);

                WestminsterSkinConsultationManager.saveInAFile();

            }

            catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Invalid input!");
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    private class PhotoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {

            JFileChooser fileChooser = new JFileChooser();  //Create a JFIleChooser object

            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "png", "gif");
            fileChooser.setFileFilter(filter);  //Filter the types of images can be uploading.

            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                try{
                    File selectedFile = fileChooser.getSelectedFile();
                    image = ImageIO.read(selectedFile);


                }
                catch (IOException ioe){
                    ioe.printStackTrace();
                    JOptionPane.showMessageDialog(null, "ERROR");
                }

            }
        }
    }

    private class UniqueIdListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {

            Person patientTemp = null;
            try{
                try{

                    for(Patient patient : patientArrayList){

                        if( patient.getPatientId().equals(tUniqueId.getText())){
                            String s = patient.toString();
                            System.out.println(s);
                            patientTemp = patient;
                            break;
                        }
                    }

                    if(patientTemp == null){
                        errorLabel.setText("Hello Welcome to the SKIN CARE CENTRE");

                        //Set all the text field to editable
                        tUniqueId.setEditable(true);
                        tName.setEditable(true);
                        tSurName.setEditable(true);
                        tDateOfBirth.setEditable(true);
                        tMobileNo.setEditable(true);
                        tCost.setText(String.valueOf( 15* noOfHours));
                        tnotes.setEditable(true);
                    }

                    else{
                        errorLabel.setText("Hello Welcome Again to the SKIN CARE CENTRE");

                        //Get user inputs from text fields
                        tName.setText(patientTemp.getName());
                        tSurName.setText(patientTemp.getSurname());
                        tDateOfBirth.setText(patientTemp.getDateOfBirth());
                        tMobileNo.setText(String.valueOf(patientTemp.getMobileNumber()));
                        tCost.setText(String.valueOf(noOfHours * 25));
                        tUniqueId.setEditable(false);

                    }
                }

                catch (IndexOutOfBoundsException e){
                    System.out.println("Index out of bounds in array.");
                }

                catch (Exception e){
                    System.out.println("Error!");
                }
            }

            catch (NumberFormatException e){

                errorLabel.setText("Invalid patient ID.Input integer value !");
            }

        }


    }


    public boolean checkAvailability (){

        try{
            for(Consultation consultation : Consultation.getConsultationArrayList()){
                Doctor d = consultation.getDoctor();
                LocalDateTime dateTime = consultation.getDateAndTime();


                if((doctor.getMedicalLicenceNumber() == d.getMedicalLicenceNumber()) && localDateTime.equals(dateTime)){
                    return false;
                }
            }
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("Index Out Of Bound !");
        }

        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return true;
    }




    


}
