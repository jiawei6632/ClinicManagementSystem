/**
 *
 * @author Choi Kah Wai
 */

package control;

import entity.Patient;
import boundary.PatientRegistrationForm;
import java.util.Scanner;
import adt.*;

public class PatientRegistration {

    private LinkedInterface<Patient> patientQueue = new myLinkedList<>();
    private ListInterface<Patient> patientList = new PatientArrayList();

    public LinkedInterface<Patient> getPatientQueue() {
        return patientQueue;
    }

    public ListInterface<Patient> getPatientList() {
        return patientList;
    }

    public static void main(String[] args) {
        PatientRegistration registration = new PatientRegistration();
        PatientRegistrationForm form = new PatientRegistrationForm();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {

            choice = form.getPatientChoice();

            switch (choice) {

                case 1:
                    Patient patient = form.inputPatientDetails();
                    registration.patientQueue.enqueue(patient);
                    registration.patientList.add(patient);
                    form.printPatientDetails(patient);
                    System.out.println("New patient added!");
                    break;

                case 2:
                    form.listPatientQueue(registration.getPatientQueue());
                    break;

                case 3:
                    Patient next = registration.patientQueue.dequeue();
                    System.out.println("Next patient is queued");
                    break;

                case 4:
                    form.listPatientList(registration.getPatientList());
                    break;

                case 5:
                    System.out.print("Enter patient ID to modify: ");
                    int patientID = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter field to update (name/age/gender/icnumber): ");
                    String field = scanner.nextLine();

                    System.out.print("Enter new value: ");
                    String newValue = scanner.nextLine();

                    boolean success;
                    if (field.equalsIgnoreCase("age")) {
                        success = form.updatePatientField(registration.getPatientList(), patientID, field, Integer.parseInt(newValue));
                    } else {
                        success = form.updatePatientField(registration.getPatientList(), patientID, field, newValue);
                    }
                    if (success) {
                        System.out.println("Update completed successfully!");
                    }
                    break;

                case 6:
                    form.patientReports(registration.getPatientList());
                    break;
                case 7:
                    System.out.println("Exiting system.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");

            }
        } while (choice != 7);

    }
}
