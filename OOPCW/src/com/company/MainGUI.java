package com.company;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

public class MainGUI extends JFrame {

    private JTable doctortable;
    private JTable consultationTable;
    private JSpinner dateSpinner;
    private JSpinner timeSpinner;
    private Doctor selectedDoctor;
    private int JNoOfHours;
    private String SelectedDate;
    private String SelectedTime;
    private LocalDateTime localDateTime;
    private JComboBox comboBox;
    private JLabel errorLabel;
    private JLabel doctorLabel;
    private JButton addConsultationButton;


    private ArrayList<Consultation> consultations;
    private ArrayList<Doctor> allocatedDoctors;
    private ArrayList<Doctor> doctors;

    private TableModel availabilityDoctorModel;

    public MainGUI() {

        this.consultations = Consultation.getConsultationArrayList();
        this.doctors = sortDoctorsBySurName(Doctor.getDoctorArrayList());

        //SetLayout to borderLayout in frame
        setLayout(new BorderLayout(5, 5));

        //Add a JLabel to the frame
        JLabel titleLabel = new JLabel("SKIN CARE SYSTEM");
        add(titleLabel, BorderLayout.NORTH);  //titleLable
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 20));


        //Create a Panel dataPanel for the tables and set gridLayout
        JPanel dataPanel = new JPanel();  //P1
        dataPanel.setLayout(new GridLayout(2, 1, 2, 2));

        //Add dataPanel to the frame
        add(dataPanel, BorderLayout.CENTER);

        //-----dataPanel---//

        //Add a JPanel called doctorPanel to the dataPanel for the doctor table and set BorderLayout
        JPanel doctorPanel = new JPanel(); //P11
        doctorPanel.setLayout(new BorderLayout(0, 0));

        //Add doctorPanel to the dataPanel
        dataPanel.add(doctorPanel);

        //---doctor Panel--//

        //Create a Jlabel and add it to the doctorPanel
        doctorLabel = new JLabel("Select A Doctor");
        doctorPanel.add(doctorLabel, BorderLayout.NORTH);  //add doctor label
        doctorLabel.setFont(new Font("Arial", Font.BOLD, 20));
        doctorPanel.setBackground(new Color(0, 129, 201));


        //Construct the doctortable
        doctortable = new JTable();


        //Create a model
        TableModel doctorModel = new DoctorModel(doctors);

        //Set model to the table
        doctortable.setModel(doctorModel);


        //Add a selection listener to the table
        doctortable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                //Get the selectedRow
                int selectedRow = doctortable.getSelectedRow();

                //Get the corresponding doctor
                selectedDoctor = doctors.get(selectedRow);


            }
        });

        //Create a scrollPane and add doctortable to that
        JScrollPane scrollPane = new JScrollPane(doctortable);
        doctortable.setBackground(new Color(134, 229, 255));
        doctortable.setFont(new Font("Serif", Font.PLAIN, 15));

        //Add scrollPane to the doctorPanel
        doctorPanel.add(scrollPane, BorderLayout.CENTER);


        /* Add a JPanel called ActionPanel and set BorderLayout as the layout */
        JPanel ActionPanel = new JPanel();
        ActionPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 3));
        ActionPanel.setBackground(new Color(91, 192, 248));

        //Add ActionPanel to the doctorPanel
        doctorPanel.add(ActionPanel, BorderLayout.SOUTH);

        //--ActionPanel--//

        //Create a jPanel called hoursPickerPanel
        JPanel hoursPickerPanel = new JPanel();
        hoursPickerPanel.setLayout(new BorderLayout(5, 2));
        hoursPickerPanel.setBackground(new Color(91, 192, 248));

        //Add hoursPickerPanel to the dateTimePanel
        //ActionPanel.add(hoursPickerPanel,BorderLayout.WEST);
        ActionPanel.add(hoursPickerPanel);

        //--hoursPickerPanel--//
        //Add JLabel to the hourPickerPanel
        hoursPickerPanel.add(new JLabel("No Of Hours\t:\t"), BorderLayout.WEST);

        //Create the ComboBox for Pick the no of hours and add it to the hourPickerPanel
        comboBox = new JComboBox(new Integer[]{1, 2, 3, 4, 5, 6});
        hoursPickerPanel.add(comboBox, BorderLayout.CENTER);

        //Create a panel called dateTimePanel and set layout type to FlowLayout
        JPanel dateTimePanel = new JPanel();
        //ActionPanel.add(dateTimePanel,BorderLayout.CENTER);
        ActionPanel.add(dateTimePanel);
        dateTimePanel.setLayout(new GridLayout(2, 2, 2, 2));
        dateTimePanel.setBackground(new Color(91, 192, 248));

        //DateTimePanel//

        //Created the date spinner
        SpinnerDateModel dateModel = new SpinnerDateModel();
        dateModel.setCalendarField(java.util.Calendar.DAY_OF_MONTH);
        dateSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);

        dateTimePanel.add(new JLabel("Date\t:"));
        dateTimePanel.add(dateSpinner);

        //Created the time spinner
        SpinnerDateModel timeModel = new SpinnerDateModel();
        timeModel.setCalendarField(java.util.Calendar.MINUTE);
        timeSpinner = new JSpinner(timeModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:00");
        timeSpinner.setEditor(timeEditor);

        dateTimePanel.add(new JLabel("Time\t:"));
        dateTimePanel.add(timeSpinner);


 /*       //Create a button called AvailabiltyButton
        JButton AvailabilityButton = new JButton("CHECK AVAILABILITY");
        ActionPanel.add(AvailabilityButton);

*/
        //Create a button called addCosultationButton
        addConsultationButton = new JButton("ADD CONSULTATION");

        //addConsultationButton.addActionListener(e -> dispose());  // When click the button frame will be disposed.

        //Add addCosulationButton to the ActionPanel
        ActionPanel.add(addConsultationButton);  //add button to the ActionPanel

        //register the event handler to the addConsultationButton
        MainListener mainListener = new MainListener();
        addConsultationButton.addActionListener(mainListener);


        //---consultation Panel--//


        JPanel consultationPanel = new JPanel(); //p12
        consultationPanel.setLayout(new BorderLayout(5, 5));
        dataPanel.add(consultationPanel);

        //consultation Panel
        JLabel consultationLabel = new JLabel("ConsultionTable");
        consultationPanel.add(consultationLabel, BorderLayout.NORTH);  //Label2
        consultationLabel.setFont(new Font("Arial", Font.BOLD, 20));
        consultationPanel.setBackground(new Color(0, 129, 201));

        consultationTable = new JTable();
        TableModel consultationModel = new ConsultationModel(consultations);
        consultationTable.setModel(consultationModel);

        //--To view a consultation

        //Add a selection listener to the table
        consultationTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                //Get the selectedRow
                int selectedRow = consultationTable.getSelectedRow();

                //Get the corresponding doctor
                Consultation selectedConsultation = consultations.get(selectedRow);

                ////Create a object of ViewConsultationGUI and call it
                ViewConsultationGUI viewConsultationGUI = null;
                try {
                    viewConsultationGUI = new ViewConsultationGUI(selectedConsultation);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                viewConsultationGUI.setVisible(true);
                viewConsultationGUI.setSize(500, 400);
                viewConsultationGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            }
        });

        JScrollPane consultationPane = new JScrollPane(consultationTable);
        consultationTable.setBackground(new Color(134, 229, 255));
        consultationTable.setFont(new Font("Serif", Font.PLAIN, 15));
        consultationTable.setGridColor(Color.BLACK);

        consultationPanel.add(consultationPane);


        //Add a JLabel to the frame
        errorLabel = new JLabel();
        add(errorLabel, BorderLayout.SOUTH);  //Label1
        errorLabel.setForeground(new Color(255, 0, 0));
        errorLabel.setFont(new Font("Arial", Font.BOLD, 20));


    }


    //Private inner class to for evenHandling
    private class MainListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {

            SelectedDate = getSelectedDate();
            SelectedTime = getSelectedTime();
            localDateTime = getSelectedDateTime();

            //get the selected no of hours
            JNoOfHours = comboBox.getSelectedIndex() + 1;

            if (selectedDoctor != null) {
                //if (checkAvailability(selectedDoctor,localDateTime)){
                addConsultationButton.addActionListener(e -> dispose());  // When click the button frame will be disposed.

                //Create a object of AddConsultationGUI and call it
                AddConsultationGUI patientInformationGUI = new AddConsultationGUI(selectedDoctor, JNoOfHours, SelectedDate, SelectedTime, localDateTime);
                patientInformationGUI.setVisible(true);
                patientInformationGUI.setSize(500, 400);
                patientInformationGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                errorLabel.setText("");
                errorLabel.setForeground(Color.red);


                // }
            } else {
                errorLabel.setText("Select a doctor.");
                errorLabel.setForeground(Color.red);
            }
        }
    }


    public String getSelectedTime() {  //Get the time as localTimeDate type
        java.util.Date time = (java.util.Date) timeSpinner.getValue();
        LocalDateTime timeOnly = LocalDateTime.ofInstant(time.toInstant(), java.time.ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:00");
        return timeOnly.format(formatter);

    }

    public String getSelectedDate() {  //Get the date as localTimeDate type
        java.util.Date date = (java.util.Date) dateSpinner.getValue();
        LocalDateTime dateOnly = LocalDateTime.ofInstant(date.toInstant(), java.time.ZoneId.systemDefault());
        return dateOnly.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
    }

    public LocalDateTime getSelectedDateTime() { //Get the date and time as localTimeDate type
        java.util.Date date = (java.util.Date) dateSpinner.getValue();
        java.util.Date time = (java.util.Date) timeSpinner.getValue();
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), java.time.ZoneId.systemDefault());
        LocalDateTime timeOnly = LocalDateTime.ofInstant(time.toInstant(), java.time.ZoneId.systemDefault());
        return dateTime.withHour(timeOnly.getHour()).withMinute(timeOnly.getMinute()).withSecond(0).withNano(0);
    }


    public static ArrayList<Doctor> sortDoctorsBySurName(ArrayList<Doctor> doctors) {

        ArrayList<Doctor> sortedDoctors = new ArrayList<>(doctors);

        int arrayListLength = sortedDoctors.size();


        //Used bubble sort for sorting

        try {
            for (int i = 0; i < arrayListLength; i++) {
                for (int j = i + 1; j < arrayListLength; j++) {

                    String jSurName = sortedDoctors.get(j).getSurname();
                    String iSurName = sortedDoctors.get(i).getSurname();

                    if (jSurName.compareTo(iSurName) < 0) {

                        Doctor temp = sortedDoctors.get(j);
                        sortedDoctors.set(j, sortedDoctors.get(i));
                        sortedDoctors.set(i, temp);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sortedDoctors;
    }

    public boolean checkAvailability(Doctor selectedDoctor, LocalDateTime selectedDateTime) {

        for (Consultation consultation : consultations) {
            Doctor doctor = consultation.getDoctor();
            LocalDateTime dateTime = consultation.getDateAndTime();

            if ((selectedDoctor == doctor) && (selectedDateTime == dateTime)) {
                return false;
            }
        }
        return true;
    }

}