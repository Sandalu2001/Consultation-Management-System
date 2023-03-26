package com.company;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;

public class ConsultationModel extends AbstractTableModel {

    private String[] columns = {"Date/Time", "Patient ID", "Patient Name" ,"Doctor ID", "Doctor Name" , "Doctor's specification"};
    private ArrayList<Consultation> consultationArrayList;

    public ConsultationModel(ArrayList<Consultation> consultationArrayList){
        this.consultationArrayList = consultationArrayList;
    }

    @Override
    public int getRowCount() {
        return consultationArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp = null;
        if(columnIndex == 0) {
            temp = consultationArrayList.get(rowIndex).getDateAndTime();
        }

        if(columnIndex == 1) {
            temp = consultationArrayList.get(rowIndex).getPatient().getPatientId();
        }

        if(columnIndex == 2) {
            temp = consultationArrayList.get(rowIndex).getPatient().getName();
        }

        if(columnIndex == 3) {
            temp = consultationArrayList.get(rowIndex).getDoctor().getMedicalLicenceNumber();
        }

        if(columnIndex == 4) {
            temp = consultationArrayList.get(rowIndex).getDoctor().getName();
        }

        if(columnIndex == 5) {
            temp = consultationArrayList.get(rowIndex).getDoctor().getSpecification();
        }

        return temp;
    }

    public String getColumnName(int columnIndex){
        return columns[columnIndex];
    }

}
