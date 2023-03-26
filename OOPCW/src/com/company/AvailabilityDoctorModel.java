package com.company;

import javax.print.Doc;
import javax.swing.table.AbstractTableModel;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AvailabilityDoctorModel extends AbstractTableModel {

    private String[] columns = {"SurName", "Name" , "Date Of Birth" , "Mobile Number" , "Medical Licence Number", "Specification"};
    private ArrayList<Doctor> allocatedDoctors;


    public AvailabilityDoctorModel(ArrayList<Doctor> allocatedDoctorsArrayList){
        this.allocatedDoctors = allocatedDoctorsArrayList;

    }


    @Override
    public int getRowCount() {
        return allocatedDoctors.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

/*        for(Consultation consultation : consultations){
            if(consultation.getDateAndTime() != selectedDateTime){
                allocatedDoctors.add(consultation.getDoctor());
            }
        }*/


        switch (columnIndex){
            case 0:
                return allocatedDoctors.get(rowIndex).getSurname();
            case 1:
                return allocatedDoctors.get(rowIndex).getName();
            case 2:
                return allocatedDoctors.get(rowIndex).getDateOfBirth();
            case 3:
                return allocatedDoctors.get(rowIndex).getMobileNumber();
            case 4:
                return allocatedDoctors.get(rowIndex).getMedicalLicenceNumber();
            case 5:
                return allocatedDoctors.get(rowIndex).getSpecification();
            default:
                return null;
        }
    }
}
