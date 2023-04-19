package org.flipmed.repository;

import org.flipmed.models.Doctor;
import org.flipmed.models.Specialization;
import org.flipmed.models.TimeSlot;

import java.util.*;

public class DoctorRepository {
    Map<Integer, Doctor> doctorMap;
    Map<Specialization, List<Doctor>> doctorSpecializations = new HashMap<>();

    public void registerDoctor(Doctor doctor) {
        if (doctorMap.containsKey(doctor.getId())) {
            System.out.println("Doctor Already Present");
            return;
        }
        doctorMap.put(doctor.getId(), doctor);
        System.out.println("Welcome " + doctor.getName() + " !!");
    }

    public void updateDoctorInfo(Doctor doctor) {
        if (doctorMap.containsKey(doctor.getId())) {
            System.out.println("Doctor not Present");
            return;
        }
        doctorMap.put(doctor.getId(), doctor);
        System.out.println("Updated Doctor Info " + doctor.getName() + " !!");
    }

    public void addAvailabilityOfDoctor(int id, TimeSlot timeSlot) {
        if (doctorMap.containsKey(id)) {
            Doctor doctor = doctorMap.get(id);
            doctor.getTimeSlotMap().put(timeSlot, Boolean.TRUE);
            System.out.println("Done Doc!");
        } else {
            System.out.println("Please enter valid Doctor");
            return;
        }
    }

    public Doctor getDoctorInfo(int id) {
            return doctorMap.get(id);
    }

    public Boolean isDoctorRegistered(int id) {
        return doctorMap.containsKey(id);
    }

    public List<Doctor> showAvailabilityBySpeciality(Specialization specialization) {
        List<Doctor> doctors = new ArrayList<>();
        if (doctorSpecializations.containsKey(specialization)) {
            doctors = doctorSpecializations.get(specialization);
        }
        return doctors;
    }

    public void freeSlot(Integer doctorId, TimeSlot timeSlot){
        Boolean put = doctorMap.get(doctorId).getTimeSlotMap().put(timeSlot, true);
        if(put == null){
            doctorMap.get(doctorId).getTimeSlotMap().remove(timeSlot);
            System.out.println("Slot Not found");
        }
    }
}
