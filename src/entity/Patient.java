/**
 *
 * @author Choi Kah Wai
 */

package entity;

import java.io.Serializable;

/**
 *
 * @author Alvin
 */
public class Patient implements Serializable {
    private int patientID;
    private String name;
    private int age;
    private String gender;
    private String icNumber;


public Patient() {
}

public Patient (int patientID, String name, int age, String gender, String icNumber){
    this.patientID = patientID;
    this.name = name;
    this.age = age;
    this.gender = gender;
    this.icNumber = icNumber;
}


public int getPatientID() {
    return patientID;
}


public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}


public int getAge() {
    return age;
}

public void setAge(int age) {
    this.age = age;
}


public String getGender() {
    return gender;
}

public void setGender(String gender) {
    this.gender = gender;
}


public String getIcNumber() {
    return icNumber;
}

public void setIcNumber(String icNumber) {
    this.icNumber = icNumber;
}

}

