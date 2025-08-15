/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

public class Doctor {

    private String id;
    private String name;
    private String specialty;
    private String dutySchedule;
    private boolean isAvailable;

    public Doctor(String id, String name, String specialty) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.dutySchedule = "Not Assigned";
        this.isAvailable = true;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getDutySchedule() {
        return dutySchedule;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setDutySchedule(String schedule) {
        this.dutySchedule = schedule;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }
}
