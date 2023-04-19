package org.flipmed.models;

public class Booking {
    int id;
    Patient patient;
    Doctor doctor;
    TimeSlot timeSlot;
    boolean waitList;

    public Booking(int id, Patient patient, Doctor doctor, TimeSlot timeSlot, boolean waitList) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.timeSlot = timeSlot;
        this.waitList = waitList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public boolean isWaitList() {
        return waitList;
    }

    public void setWaitList(boolean waitList) {
        this.waitList = waitList;
    }
}
