/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

//import boundary.PharmacyUI;
import boundary.StockManagementUI;
import boundary.PatientRegistrationForm;
import boundary.DoctorManagements;
//import control.MedicineCTRL;
import control.StockCTRL;
import java.util.Scanner;

/**
 *
 * @author TOWJIAWEI
 */
public class ClinicManagementSystem {
    public static void main(String[] args) {
//        MedicineCTRL medicalCTRL = new MedicineCTRL();
        StockCTRL stockCTRL = new StockCTRL();
//        PharmacyUI medicineUI = new PharmacyUI(medicalCTRL, stockCTRL);
        StockManagementUI stockUI = new StockManagementUI(stockCTRL);
        PatientRegistrationForm patient = new PatientRegistrationForm();
        DoctorManagements doctor = new DoctorManagements();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== CLINIC MANAGEMENT ===");
//            System.out.println("1. Prescribe Medicine");
            System.out.println("1. Medicine Stock");
            System.out.println("2. Doctor");
            System.out.println("3. Patient");
            System.out.println("4. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
//                case 1:
//                    System.out.print("Enter Patient ID: ");
//                    String patientID = sc.nextLine();
//                    System.out.print("Enter Doctor ID: ");
//                    String doctorID = sc.nextLine();
//                    medicineUI.prescribeMedicine(patientID, doctorID);
//                    break;
                case 1:
                    stockUI.displayMenu();
                    break;
                case 2:
                    doctor.doctorManagement();
                    break;
                case 3:
                    patient.getPatientChoice();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
