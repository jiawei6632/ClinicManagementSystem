/**
 *
 * @author Choi Kah Wai
 */

package adt;
import entity.Patient;

public class PatientArrayList implements ListInterface<Patient> {
    private Patient[] patients;
    private int size;
    
    public PatientArrayList() {
        patients = new Patient[10];
        size = 0;
    }
    
    @Override
    public Patient get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return patients[index];
    }
    
    @Override
    public void add(Patient patient) {
        if (size == patients.length) {
            Patient[] newPatients = new Patient[patients.length * 2];
            System.arraycopy(patients, 0, newPatients, 0, patients.length);
            patients = newPatients;
        }
        patients[size++] = patient;
    }
    
    @Override
    public int size() {
        return size;
    }
    
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}