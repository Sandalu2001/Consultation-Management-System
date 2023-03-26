package com.company;

import java.io.IOException;
import java.util.ArrayList;

public interface SkinConsultationManager {

    public void addDoctor() throws medicalNumberException;
    public void removeDoctor();
    public void getAllDoctors();
    //public void saveInAFile() throws IOException;
    public void loadTheDataFile();
    public void getAllConsultations();
    public void OpenGUI();
    public void removeConsultation();




}
