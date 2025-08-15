/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package boundary;

import entity.Doctor;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.*;

public class DoctorManagements {

    public static List<Doctor> doctorList = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);
    public static final String FILE_NAME = "src/doctormanagements/DoctorManagements.txt";

    public void doctorManagement() {
        loadDoctorsFromFile();

        int choice;
        do {
            System.out.println("\nDoctor Management Menu:");
            System.out.println("1. Add Doctor");
            System.out.println("2. View Doctor List");
            System.out.println("3. Assign Duty Schedule");
            System.out.println("4. Duty Schedule List");
            System.out.println("5. Track Availability");
            System.out.println("6. Edit/Update Doctor");
            System.out.println("7. Remove Doctor");
            System.out.println("8. Exit");
            System.out.print("Enter your choice(1-8): ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input, enter a number.");
                sc.next();
                System.out.print("Enter your choice(only 1-8): ");
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addDoctor();
                    break;
                case 2:
                    viewDoctorList();
                    break;
                case 3:
                    assignDuty();
                    break;
                case 4:
                    listDutySchedules();
                    break;
                case 5:
                    trackAvailability();
                    break;
                case 6:
                    editDoctor();
                    break;
                case 7:
                    removeDoctor();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    return; 
                default:
                    System.out.println("Invalid Choice!");
            }
        } while (choice != 8);
    }

    public static void addDoctor() {
        System.out.print("Enter Doctor ID: ");
        String id = sc.nextLine().trim();

        for (Doctor d : doctorList) {
            if (d.getId().equalsIgnoreCase(id)) {
                System.out.println("Error: Doctor ID already exists!");
                return;
            }
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine().trim();

        String[] specialties = {
            "Cardiology",
            "Dermatology",
            "Emergency Medicine",
            "Family Medicine",
            "Internal Medicine",
            "Obstetrics and Gynecology (O&G)",
            "Ophthalmology",
            "Orthopaedics",
            "Otorhinolaryngology",
            "Psychiatry",};

        System.out.println("\nDoctor Specialty:");
        for (int i = 0; i < specialties.length; i++) {
            System.out.println((i + 1) + ". " + specialties[i]);
        }

        String specialty = "";
        while (true) {
            System.out.print("Select Specialty (1-" + specialties.length + "): ");
            String specialtyInput = sc.nextLine().trim();
            try {
                int specialtyNum = Integer.parseInt(specialtyInput);
                if (specialtyNum >= 1 && specialtyNum <= specialties.length) {
                    specialty = specialties[specialtyNum - 1];
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and " + specialties.length + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        for (Doctor d : doctorList) {
            if (d.getName().equalsIgnoreCase(name) && d.getSpecialty().equalsIgnoreCase(specialty)) {
                System.out.println("Error: A doctor with the same name and specialty already exists!");
                return;
            }
        }

        if (id.isEmpty() || name.isEmpty() || specialty.isEmpty()) {
            System.out.println("Error: All fields are required!");
            return;
        }

        Doctor newDoctor = new Doctor(id, name, specialty);
        doctorList.add(newDoctor);
        appendDoctorToFile(newDoctor);
        System.out.println("Doctor added successfully!");
    }

    public static void viewDoctorList() {
        if (doctorList.isEmpty()) {
            System.out.println("--------------------------------------------------------------------------");
            System.out.printf("| %-10s | %-20s | %-20s | %-12s |\n", "ID", "Name", "Specialty", "Availability");
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("|                      No doctors in the system.                         |");
            System.out.println("--------------------------------------------------------------------------");
            return;
        }
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-20s | %-20s | %-12s |\n", "ID", "Name", "Specialty", "Availability");
        System.out.println("--------------------------------------------------------------------------");
        for (Doctor doc : doctorList) {
            System.out.printf("| %-10s | %-20s | %-20s | %-12s |\n",
                    doc.getId(), doc.getName(), doc.getSpecialty(), doc.isAvailable() ? "Available" : "Not Available");
        }
        System.out.println("--------------------------------------------------------------------------");
    }

    public static void assignDuty() {
        if (doctorList.isEmpty()) {
            System.out.println("--------------------------");
            System.out.printf("| %-10s | %-10s|\n", "ID", "Name");
            System.out.println("--------------------------");
            System.out.println("|No doctors in the system.|");
            System.out.println("--------------------------");
            return;
        }
        System.out.println("--------------------------");
        System.out.printf("| %-10s | %-10s|\n", "ID", "Name");
        System.out.println("--------------------------");
        for (Doctor doc : doctorList) {
            System.out.printf("| %-10s | %-10s|\n",
                    doc.getId(), doc.getName());
        }
        System.out.println("--------------------------");
        System.out.print("Enter Doctor ID to assign duty: ");
        String id = sc.nextLine().trim();
        Doctor doc = findDoctorById(id);
        if (doc != null) {
            if (!doc.isAvailable()) {
                System.out.println("This doctor is currently NOT AVAILABLE and cannot be assigned a duty schedule.");
                return;
            }
            String pattern1 = "^(Mon|Tue|Wed|Thu|Fri|Sat|Sun)-(Mon|Tue|Wed|Thu|Fri|Sat|Sun) \\d{1,2}(am|pm)-\\d{1,2}(am|pm)$";
            String pattern2 = "^(Mon|Tue|Wed|Thu|Fri|Sat|Sun)(,(Mon|Tue|Wed|Thu|Fri|Sat|Sun))* \\d{1,2}(am|pm)-\\d{1,2}(am|pm)$";
            String schedule;
            while (true) {
                System.out.print("Enter duty schedule (e.g., Mon-Fri 9am-5pm or Mon,Wed,Fri 9am-5pm): ");
                schedule = sc.nextLine().trim();
                if (schedule.matches(pattern1) || schedule.matches(pattern2)) {
                    break;
                } else {
                    System.out.println("Invalid format! Please use 'Mon-Fri 9am-5pm' or 'Mon,Wed,Fri 9am-5pm'.");
                }
            }
            doc.setDutySchedule(schedule);
            saveAllDoctorsToFile();
            System.out.println("Duty assigned to Doctor " + doc.getName());
        } else {
            System.out.println("Doctor not found.");
        }
    }

    public static void listDutySchedules() {
        if (doctorList.isEmpty()) {
            System.out.println("No doctors in the system.");
            return;
        }
        System.out.println("-------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-20s | %-20s | %-25s |\n", "ID", "Name", "Specialty", "Duty Schedule");
        System.out.println("-------------------------------------------------------------------------------");
        for (Doctor doc : doctorList) {
            System.out.printf("| %-10s | %-20s | %-20s | %-25s |\n",
                    doc.getId(), doc.getName(), doc.getSpecialty(), doc.getDutySchedule());
        }
        System.out.println("-------------------------------------------------------------------------------");
    }

    public static void trackAvailability() {
        if (doctorList.isEmpty()) {
            System.out.println("--------------------------");
            System.out.printf("| %-10s | %-10s|\n", "ID", "Name");
            System.out.println("--------------------------");
            System.out.println("|No doctors in the system.|");
            System.out.println("--------------------------");
            return;
        }
        System.out.println("--------------------------");
        System.out.printf("| %-10s | %-10s|\n", "ID", "Name");
        System.out.println("--------------------------");
        for (Doctor doc : doctorList) {
            System.out.printf("| %-10s | %-10s|\n",
                    doc.getId(), doc.getName());
        }
        System.out.println("--------------------------");
        System.out.print("Enter Doctor ID to update availability: ");
        String id = sc.nextLine().trim();
        Doctor doc = findDoctorById(id);
        if (doc != null) {
            System.out.println("Current availability: " + (doc.isAvailable() ? "Available" : "Not Available"));
            int choice = 0;
            while (true) {
                System.out.print("Set availability (1=Available, 2=Not Available): ");
                String input = sc.nextLine().trim();
                if (input.equals("1")) {
                    doc.setAvailable(true);
                    break;
                } else if (input.equals("2")) {
                    doc.setAvailable(false);
                    break;
                } else {
                    System.out.println("Invalid input, please enter 1 for Available or 2 for Not Available.");
                }
            }
            saveAllDoctorsToFile();
            System.out.println("Availability updated!");
        } else {
            System.out.println("Doctor not found.");
        }
    }

    public static void editDoctor() {
        System.out.print("Enter Doctor ID to edit: ");
        String id = sc.nextLine().trim();
        Doctor doc = findDoctorById(id);
        if (doc == null) {
            System.out.println("Doctor not found.");
            return;
        }
        System.out.println("Editing Doctor: " + doc.getName() + " (" + doc.getId() + ")");

        System.out.print("Enter new Name (leave blank to keep current): ");
        String name = sc.nextLine().trim();

        String[] specialties = {
            "Cardiology",
            "Dermatology",
            "Emergency Medicine",
            "Family Medicine",
            "Internal Medicine",
            "Obstetrics and Gynecology (O&G)",
            "Ophthalmology",
            "Orthopaedics",
            "Otorhinolaryngology",
            "Psychiatry",};

        System.out.println("\nDoctor Specialties:");
        for (int i = 0; i < specialties.length; i++) {
            System.out.println((i + 1) + ". " + specialties[i]);
        }
        System.out.print("Enter new Specialty number (leave blank to keep current): ");
        String specialtyInput = sc.nextLine().trim();
        String specialty = doc.getSpecialty();

        if (!specialtyInput.isEmpty()) {
            try {
                int specialtyNum = Integer.parseInt(specialtyInput);
                if (specialtyNum >= 1 && specialtyNum <= specialties.length) {
                    specialty = specialties[specialtyNum - 1];
                } else {
                    System.out.println("Invalid choice. Keeping current specialty.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Keeping current specialty.");
            }
        }

        System.out.print("Enter new Duty Schedule (e.g., Mon-Fri 9am-5pm)(leave blank to keep current): ");
        String dutySchedule = sc.nextLine().trim();

        String newName = name.isEmpty() ? doc.getName() : name;
        if ((!name.isEmpty() || !specialtyInput.isEmpty())) {
            for (Doctor d : doctorList) {
                if (d != doc && d.getName().equalsIgnoreCase(newName) && d.getSpecialty().equalsIgnoreCase(specialty)) {
                    System.out.println("Error: A doctor with the same name and specialty already exists!");
                    return;
                }
            }
        }

        if (!name.isEmpty()) {
            doc.setName(name);
        }
        if (!specialtyInput.isEmpty()) {
            doc.setSpecialty(specialty);
        }
        if (!dutySchedule.isEmpty()) {
            doc.setDutySchedule(dutySchedule);
        }

        saveAllDoctorsToFile();
        System.out.println("Doctor updated successfully!");
    }

    public static void removeDoctor() {
        if (doctorList.isEmpty()) {
            System.out.println("--------------------------");
            System.out.printf("| %-10s | %-10s|\n", "ID", "Name");
            System.out.println("--------------------------");
            System.out.println("|No doctors in the system.|");
            System.out.println("--------------------------");
            return;
        }
        System.out.println("--------------------------");
        System.out.printf("| %-10s | %-10s|\n", "ID", "Name");
        System.out.println("--------------------------");
        for (Doctor doc : doctorList) {
            System.out.printf("| %-10s | %-10s|\n",
                    doc.getId(), doc.getName());
        }
        System.out.println("--------------------------");

        System.out.print("Enter Doctor ID to remove: ");
        String id = sc.nextLine().trim();
        Doctor doc = findDoctorById(id);

        if (doc == null) {
            System.out.println("Doctor not found.");
            return;
        }

        System.out.println("Doctor found:");
        System.out.println("ID: " + doc.getId());
        System.out.println("Name: " + doc.getName());
        System.out.println("Specialty: " + doc.getSpecialty());
        System.out.println("Duty Schedule: " + doc.getDutySchedule());
        System.out.println("Availability: " + (doc.isAvailable() ? "Available" : "Not Available"));

        while (true) {
            System.out.print("Are you sure you want to remove this doctor? (Y/N): ");
            String confirm = sc.nextLine().trim();
            if (confirm.equalsIgnoreCase("Y")) {
                doctorList.remove(doc);
                saveAllDoctorsToFile();
                System.out.println("Doctor removed successfully!");
                break;
            } else if (confirm.equalsIgnoreCase("N")) {
                System.out.println("Doctor removal cancelled.");
                break;
            } else {
                System.out.println("Invalid input. Please enter 'Y' or 'N'.");
            }
        }
    }

    public static Doctor findDoctorById(String id) {
        for (Doctor doc : doctorList) {
            if (doc.getId().equalsIgnoreCase(id)) {
                return doc;
            }
        }
        return null;
    }

    public static void appendDoctorToFile(Doctor doctor) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(doctor.getId() + "," + doctor.getName() + "," + doctor.getSpecialty() + ","
                    + doctor.getDutySchedule() + "," + doctor.isAvailable());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void saveAllDoctorsToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, false))) {
            for (Doctor doctor : doctorList) {
                bw.write(doctor.getId() + "," + doctor.getName() + "," + doctor.getSpecialty() + ","
                        + doctor.getDutySchedule() + "," + doctor.isAvailable());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    public static void loadDoctorsFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length >= 5) {
                    Doctor doc = new Doctor(parts[0], parts[1], parts[2]);
                    doc.setDutySchedule(parts[3]);
                    doc.setAvailable(Boolean.parseBoolean(parts[4]));
                    doctorList.add(doc);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
}
