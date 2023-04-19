package org.flipmed.service;

import org.flipmed.models.Doctor;
import org.flipmed.models.Specialization;
import org.flipmed.models.TimeSlot;
import org.flipmed.repository.DoctorRepository;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

public class DoctorService {

    DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void registerDoctor(Doctor doctor) {
        doctorRepository.registerDoctor(doctor);
    }

    public void addAvailability (int id, TimeSlot timeSlot) {
        LocalTime start = LocalTime.parse(timeSlot.getStartTime());
        LocalTime end = LocalTime.parse(timeSlot.getEndTime());
        if (start.until(end, ChronoUnit.MINUTES) == 30) {
            doctorRepository.addAvailabilityOfDoctor(id, timeSlot);
        } else {
            Doctor doctor = doctorRepository.getDoctorInfo(id);
            System.out.println("Sorry Dr. " + doctor.getName() +" slots are 30 mins only");
        }
    }

    public void showAvailabilityBySpeciality(Specialization specialization) {
        List<Doctor> doctors = doctorRepository.showAvailabilityBySpeciality(specialization);
        if (doctors.size() > 0 ) {
            for (Doctor doctor : doctors) {
                for ( var timeSlot : doctor.getTimeSlotMap().entrySet()) {
                    if (timeSlot.getValue()) {
                        System.out.printf("%s: ( %s - %s )%n", doctor.getName(), timeSlot.getKey().getStartTime(),
                                timeSlot.getKey().getEndTime());
                    }
                }
            }
        }
    }
}
