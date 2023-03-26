package com.company;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class DoctorModel extends AbstractTableModel{

    private String[] columns = {"SurName", "Name" , "Date Of Birth" , "Mobile Number" , "Medical Licence Number", "Specification"};
    private ArrayList<Doctor> doctorArrayList;

    public DoctorModel(ArrayList<Doctor> doctorArrayList){
        this.doctorArrayList = doctorArrayList;
    }

    @Override
    public int getRowCount() {
        return doctorArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {


        switch (columnIndex){
            case 0:
                return doctorArrayList.get(rowIndex).getSurname();
            case 1:
                return doctorArrayList.get(rowIndex).getName();
            case 2:
                return doctorArrayList.get(rowIndex).getDateOfBirth();
            case 3:
                return doctorArrayList.get(rowIndex).getMobileNumber();
            case 4:
                return doctorArrayList.get(rowIndex).getMedicalLicenceNumber();
            case 5:
                return doctorArrayList.get(rowIndex).getSpecification();
            default:
                return null;
        }
    }

    public String getColumnName(int columnIndex){
        return columns[columnIndex];
    }
}
