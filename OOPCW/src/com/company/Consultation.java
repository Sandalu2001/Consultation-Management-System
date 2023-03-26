package com.company;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.Serializable;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.imageio.ImageIO;


public class Consultation implements Serializable {
    private LocalDateTime dateAndTime;
    private double cost;
    private String notes;
    private Doctor doctor;
    private Patient patient;
    private static ArrayList<Consultation> consultationArrayList = new ArrayList<>();

    private SecretKey secretKey;
    private SecretKey secretKeyImg;
    private transient Cipher cipher;
    private transient Cipher cipherImg;
    private byte[] ecryptedText;
    private byte[] ecryptedImg;





    public Consultation(LocalDateTime dateAndTime, double cost, String note, Doctor doctor, Patient patient,BufferedImage image) throws Exception{


        //note part
        // Generate a secret key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        secretKey = keyGenerator.generateKey();

        // Create the cipher
        cipher = Cipher.getInstance("AES");
        cipherImg = Cipher.getInstance("AES");

        //Image part
        // Generate a secret key
        KeyGenerator keyGeneratorImg = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        secretKeyImg = keyGeneratorImg.generateKey();

        this.dateAndTime = dateAndTime;
        this.cost = cost;
        this.setDoctor(doctor);
        this.setPatient(patient);
        this.notes = note;

        if(image != null){
            this.ecryptedImg = encryptImage(image);

        }

        if(note != null){
            this.ecryptedText = encrypt(note);
        }
    }

    public static ArrayList<Consultation> getConsultationArrayList() {
        return consultationArrayList;
    }

    public static void setConsultationArrayList(ArrayList<Consultation> consultationArrayList) {
        Consultation.consultationArrayList = consultationArrayList;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

   public static void AddConsultation(Consultation consultation) {
       consultationArrayList.add(consultation);
   }

    public static void RemoveConsultation(Consultation consultation) {
        consultationArrayList.remove(consultation);
    }



    public String getNotes() throws Exception {
        return decrypt(ecryptedText);
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public BufferedImage getImage() throws Exception{

        if(ecryptedImg != null)
            return decryptImage(ecryptedImg);
        return null;
    }



    public byte[] encrypt(String message) throws Exception {
        // Encrypt the message
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        return cipher.doFinal(message.getBytes());


    }

    public String decrypt(byte[] encryptedMessage) throws Exception {
        cipher = Cipher.getInstance("AES");

        // Decrypt the message
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedMessage = cipher.doFinal(encryptedMessage);

        // Return the decrypted message as a string
        String decyptedText =  new String(decryptedMessage);
        return decyptedText;
    }



    public byte[] encryptImage(BufferedImage image) throws Exception {

        // Convert the image to a byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        baos.flush();
        byte[] imageBytes = baos.toByteArray();
        baos.close();

        // Encrypt the image
        cipherImg.init(Cipher.ENCRYPT_MODE, secretKeyImg);
        return cipherImg.doFinal(imageBytes);
    }

    public BufferedImage decryptImage(byte[] encryptedImage) throws Exception {

        cipherImg = Cipher.getInstance("AES");
        // Decrypt the image
        cipherImg.init(Cipher.DECRYPT_MODE, secretKeyImg);
        byte[] decryptedImageBytes = cipherImg.doFinal(encryptedImage);

        // Convert the decrypted image to a BufferedImage
        return ImageIO.read(new ByteArrayInputStream(decryptedImageBytes));
    }
}
