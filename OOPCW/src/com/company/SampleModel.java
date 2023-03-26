package com.company;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class SampleModel extends AbstractTableModel {

    private ArrayList<Doctor> doctorArrayList;
    private String[] columnName = {"Name" , "Surname" , "Doctor Specification"};

    public SampleModel(ArrayList<Doctor> doctors){
        this.doctorArrayList = doctors;
    }
    @Override
    public int getRowCount() {
        return doctorArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return columnName.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
                return doctorArrayList.get(rowIndex).getSurname();
        }
        return null;
    }
}
