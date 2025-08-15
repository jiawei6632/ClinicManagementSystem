/**
 *
 * @author Choi Kah Wai
 */

package boundary;

import adt.LinkedInterface;
import adt.ListInterface;
import entity.Patient;
import java.util.Scanner;
import java.util.Queue;

public class PatientRegistrationForm {

    Scanner scanner = new Scanner(System.in);
    private static int patientIDCounter = 1;

    public int getPatientChoice() {
        System.out.println("\nMAIN MENU");
        System.out.println("1. Register patient");
        System.out.println("2. View waiting queue");
        System.out.println("3. Serve Next Patient");
        System.out.println("4. View patient list");
        System.out.println("5. Modify patient list");
        System.out.println("6. Patients Reports");
        System.out.println("7. Exit");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;
    }

    public void listPatientQueue(LinkedInterface<Patient> patientQueue) {
        System.out.println("\n==============================");
        System.out.println("        Patient Queue       ");
        System.out.println("==============================");

        if (patientQueue.isEmpty()) {
            System.out.println("No patients registered yet.");
        } else {

            String header = String.format("%-5s %-10s %-10s %-10s %-15s\n",
                    "ID", "Name", "Age", "Gender", "IC Number");
            System.out.println(header);
            System.out.println("-----------------------------------------------------");

            for (Patient patient : patientQueue.toList()) {
                String row = String.format("%-5d %-10s %-10d %-10s %-15s",
                        patient.getPatientID(),
                        patient.getName(),
                        patient.getAge(),
                        patient.getGender(),
                        patient.getIcNumber());
                System.out.println(row);
            }
        }
    }

    public void printPatientDetails(Patient patient) {
        System.out.println("Patient Details");
        System.out.println("Patient name:" + patient.getName());
        System.out.println("Patient age: " + patient.getAge());
        System.out.println("Patient gender: " + patient.getGender());
        System.out.println("Patient ic number: " + patient.getIcNumber());
    }

    public String inputPatientName() {
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();
        return name;
    }

    public int inputPatientAge() {
        int age;
        while (true) {
            System.out.print("Enter patient age: ");
            if (scanner.hasNextInt()) {
                age = scanner.nextInt();
                scanner.nextLine();
                if (age > 0) {
                    return age;
                } else {
                    System.out.println("Age must be positive.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    public String inputPatientGender() {
        while (true) {
            System.out.print("Enter patient gender (M/F): ");
            String gender = scanner.nextLine().trim().toUpperCase();

            if (gender.equals("M") || gender.equals("F")) {
                return gender;
            } else {
                System.out.println("Gender must be M or F.");
            }
        }
    }

    public String inputPatientIC() {
        while (true) {
            System.out.print("Enter patient ic number (12 digits): ");
            String icNumber = scanner.nextLine().trim();

            if (icNumber.matches("\\d{12}")) {
                return icNumber;
            } else {
                System.out.println("Invalid IC number. It must be exactly 12 digits.");
            }
        }
    }

    public Patient inputPatientDetails() {
        String patientName = inputPatientName();
        int patientAge = inputPatientAge();
        String patientGender = inputPatientGender();
        String patientIC = inputPatientIC();
        System.out.println();
        int patientID = patientIDCounter++;
        return new Patient(patientID, patientName, patientAge, patientGender, patientIC);
    }

    public Patient nextPatient(Queue<Patient> patientQueue) {
        return patientQueue.poll();
    }

    public void listPatientList(ListInterface<Patient> patientList) {
        System.out.println("\n==============================");
        System.out.println("        Patient List      ");
        System.out.println("==============================");

        if (patientList.isEmpty()) {
            System.out.println("No patient records yet.");
        } else {

            String header = String.format("%-5s %-10s %-10s %-10s %-15s\n",
                    "ID", "Name", "Age", "Gender", "IC Number");
            System.out.println(header);
            System.out.println("-----------------------------------------------------");

            for (int i = 0; i < patientList.size(); i++) {
                Patient patient = patientList.get(i);
                String row = String.format("%-5d %-10s %-10d %-10s %-15s",
                        patient.getPatientID(),
                        patient.getName(),
                        patient.getAge(),
                        patient.getGender(),
                        patient.getIcNumber());
                System.out.println(row);
            }
        }
    }

    public boolean updatePatientField(ListInterface<Patient> patientList, int patientID, String field, Object newValue) {
        Patient p = null;
        for (int i = 0; i < patientList.size(); i++) {
            Patient patient = patientList.get(i);
            if (patient.getPatientID() == patientID) {
                p = patient;
                break;
            }
        }

        if (p == null) {
            System.out.println("Patient not found.");
            return false;
        }

        switch (field.toLowerCase()) {
            case "name":
                p.setName((String) newValue);
                break;
            case "age":
                p.setAge((Integer) newValue);
                break;
            case "gender":
                p.setGender((String) newValue);
                break;
            case "icnumber":
                p.setIcNumber((String) newValue);
                break;
            default:
                System.out.println("Invalid field name.");
                return false;
        }

        return true;
    }

    public void patientReports(ListInterface<Patient> patientList) {
        int count = patientList.size();
        System.out.println("=== Patient Report ===");
        System.out.println("Total patients: " + count);

        int maleCount = 0, femaleCount = 0;
        for (int i = 0; i < patientList.size(); i++) {
            Patient p = patientList.get(i);
            if (p.getGender().equalsIgnoreCase("M")) {
                maleCount++;
            } else if (p.getGender().equalsIgnoreCase("F")) {
                femaleCount++;
            }
        }
        System.out.println("Male patients: " + maleCount);
        System.out.println("Female patients: " + femaleCount);
    }

}
