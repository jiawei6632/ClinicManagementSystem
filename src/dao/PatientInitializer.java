/**
 *
 * @author Choi Kah Wai
 */

package dao;

import adt.ListInterface;
import adt.PatientArrayList;
import entity.Patient;

public class PatientInitializer {

    public ListInterface<Patient> initializePatients() {
        ListInterface<Patient> pList = new PatientArrayList();

        pList.add(new Patient(1, "John Doe", 25, "M", "990101011234"));
        pList.add(new Patient(2, "Jane Smith", 30, "F", "950303032345"));
        pList.add(new Patient(3, "Adam Lee", 40, "M", "850707074567"));

        return pList;
    }

    public static void main(String[] args) {
        PatientInitializer initializer = new PatientInitializer();
        ListInterface<Patient> patientList = initializer.initializePatients();
        System.out.println("\nPatients:\n" + patientList);
    }
}
