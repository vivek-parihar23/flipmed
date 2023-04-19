package org.flipmed.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Doctor {
    int id;
    String name;
    Specialization specialization;

    Map<TimeSlot, Boolean> timeSlotMap;

    public Doctor(int id, String name, Specialization specialization, Map<TimeSlot, Boolean> timeSlotMap) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.timeSlotMap = timeSlotMap;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<TimeSlot, Boolean> getTimeSlotMap() {
        return timeSlotMap;
    }

    public void setTimeSlotMap(Map<TimeSlot, Boolean> timeSlotMap) {
        this.timeSlotMap = timeSlotMap;
    }
}
