/**
 *
 * @author Choi Kah Wai
 */

package dao;

import adt.ListInterface;
import adt.PatientArrayList;
import entity.Patient;
import java.io.*;

public class PatientDAO {

    private String fileName = "patients.dat";

    public void saveToFile(ListInterface<Patient> patientList) {
        File file = new File(fileName);
        try (ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file))) {
            ooStream.writeObject(patientList);
            System.out.println("Patients saved to file successfully.");
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found.");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file.");
        }
    }

    public ListInterface<Patient> retrieveFromFile() {
        File file = new File(fileName);
        ListInterface<Patient> patientList = new PatientArrayList();
        try (ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file))) {
            patientList = (PatientArrayList) oiStream.readObject();
            System.out.println("Patients loaded from file successfully.");
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file. Returning empty patient list.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        }
        return patientList;
    }
}
