//package boundary;
//
//import adt.Date;
//import control.MedicineCTRL;
//import control.StockCTRL;
//import entity.Medicine;
//import entity.Prescription;
//import java.util.Scanner;
//
//public class PharmacyUI {
//    private MedicineCTRL medicalCTRL;
//    private StockCTRL stockCTRL;
//
//    public PharmacyUI(MedicineCTRL medicalCTRL, StockCTRL stockCTRL) {
//        this.medicalCTRL = medicalCTRL;
//        this.stockCTRL = stockCTRL;
//    }
//
//    public void prescribeMedicine(String patientID, String doctorID) {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter Diagnosis:");
//        String diagnosis = sc.nextLine();
//
//        Prescription prescription = new Prescription(
//            "PRESC-" + System.currentTimeMillis(),
//            patientID,
//            doctorID,
//            new Date() 
//        );
//
//        while (true) {
//            System.out.println("Add Medicine (Y/N)?");
//            String choice = sc.nextLine();
//            if (!choice.equalsIgnoreCase("Y")) break;
//
//            System.out.println("Enter Medicine ID:");
//            String medID = sc.nextLine();
//            if (stockCTRL.isMedicineAvailable(medID, 1)) {
//                Medicine med = stockCTRL.findMedicine(medID);
//                prescription.addMedicine(med);
//                System.out.println(med.getName() + " added.");
//            } else {
//                System.out.println("Medicine not available!");
//            }
//        }
//
//        medicalCTRL.addPatientRecord(patientID, doctorID, diagnosis, prescription);
//        System.out.println("Prescription saved!");
//    }
//}