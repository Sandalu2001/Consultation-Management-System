package com.company;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Random;

public class ViewConsultationGUI extends JFrame{

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


        public ViewConsultationGUI(Consultation consultation) throws Exception {

            setLayout(new BorderLayout(5,5));

            JPanel addConsultationPanel =new JPanel();
            add(addConsultationPanel,BorderLayout.NORTH);
            addConsultationPanel.setLayout(new BorderLayout(3,3));
            addConsultationPanel.setBackground(new Color(134, 229, 255));

            JPanel doctorPanel =new JPanel();
            addConsultationPanel.add(doctorPanel,BorderLayout.NORTH);
            doctorPanel.setLayout(new GridLayout(2,2));
            doctorPanel.setBackground(new Color(0, 129, 201));


            doctorPanel.add(new JLabel("DOCTOR"));
            doctorTextField = new JTextField(consultation.getDoctor().getName());
            doctorTextField.setEditable(false);
            doctorPanel.add(doctorTextField);

            doctorPanel.add(new JLabel("SPECIFICATION"));
            specificationTextField = new JTextField(consultation.getDoctor().getSpecification());
            doctorPanel.add(specificationTextField);


            JPanel dataPanel = new JPanel();  //P1
            add(dataPanel,BorderLayout.CENTER);
            dataPanel.setBackground(Color.cyan);

            dataPanel.setLayout(new GridLayout(6,2,5,5));

            dataPanel.add(new JLabel("\t\tUnique ID"));
            tUniqueId = new JTextField(String.valueOf(consultation.getPatient().getPatientId()));
            dataPanel.add(tUniqueId);
            tUniqueId.setEditable(false);

            dataPanel.add(new JLabel("\t\tName"));
            tName = new JTextField(consultation.getPatient().getName());
            dataPanel.add(tName);
            tName.setEditable(false);

            dataPanel.add(new JLabel("\t\tSurName"));
            tSurName = new JTextField(consultation.getPatient().getSurname());
            dataPanel.add(tSurName);
            tSurName.setEditable(false);

            dataPanel.add(new JLabel("\t\tBirth Of Date"));
            tDateOfBirth = new JTextField(consultation.getPatient().getDateOfBirth());
            dataPanel.add(tDateOfBirth);
            tDateOfBirth.setEditable(false);

            dataPanel.add(new JLabel("\t\tMobile No"));
            tMobileNo = new JTextField(String.valueOf(consultation.getPatient().getMobileNumber()));
            dataPanel.add(tMobileNo);
            tMobileNo.setEditable(false);

            dataPanel.add(new JLabel("\t\tCost"));
            tCost = new JTextField(String.valueOf(consultation.getCost()));
            tCost.setEditable(false);
            dataPanel.add(tCost);


            //Note and Photo Panel

            JPanel NPPanel = new JPanel();
            NPPanel.setLayout(new GridLayout(1,2));
            add(NPPanel,BorderLayout.SOUTH);

            //NotePanel

            JPanel notePanel = new JPanel();
            NPPanel.add(notePanel);
            notePanel.setLayout(new GridLayout(1,2));
            notePanel.setBackground(new Color(0, 129, 201));

            notePanel.add(new JLabel("Notes"));
            tnotes = new JTextField(consultation.getNotes());
            tnotes.setEditable(false);
            notePanel.add(tnotes);


            //PhotoPanel

            JPanel PhotoPanel = new JPanel();
            NPPanel.add(PhotoPanel);
            PhotoPanel.setPreferredSize(new Dimension(100,100));



            if(consultation.getImage() != null){
                //Load the image and create a label
                ImageIcon icon = new ImageIcon(consultation.getImage());
                JLabel label = new JLabel(icon);
                NPPanel.add(label);
            }
        }
}
