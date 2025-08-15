///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package control;
//
//import adt.Date;
//import entity.PatientRecord;
//import entity.Prescription;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class MedicineCTRL {
//    private List<PatientRecord> patientRecords;
//    private List<Prescription> prescriptions;
//
//    public MedicineCTRL() {
//        this.patientRecords = new ArrayList<>();
//        this.prescriptions = new ArrayList<>();
//    }
//
//    // Create a new patient record after consultation
//    public void addPatientRecord(String patientID, String doctorID, 
//                                String diagnosis, Prescription prescription) {
//        String recordID = "REC-" + System.currentTimeMillis();
//        PatientRecord record = new PatientRecord(
//            recordID, patientID, doctorID, new Date(), diagnosis, prescription
//        );
//        patientRecords.add(record);
//        prescriptions.add(prescription);
//    }
//
//    // Get all records for a patient
//    public List<PatientRecord> getPatientRecords(String patientID) {
//        return patientRecords.stream()
//            .filter(r -> r.getPatientID().equals(patientID))
//            .collect(Collectors.toList());
//    }
//}
