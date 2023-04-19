package org.flipmed.service;

import org.flipmed.models.Booking;
import org.flipmed.models.Doctor;
import org.flipmed.models.Patient;
import org.flipmed.models.TimeSlot;
import org.flipmed.repository.DoctorRepository;
import org.flipmed.repository.PatientRepository;

import java.util.*;

public class BookingService {
    DoctorRepository doctorRepository;
    PatientRepository patientRepository;

    Map<Integer, Booking> bookings = new HashMap<>();

    Map<Integer, List<TimeSlot>> patientSlots = new HashMap<>();

    Map<String, List<Booking>> waitListQueue = new HashMap<>();

    static int id = 1;

    public BookingService(DoctorRepository doctorRepository, PatientRepository patientRepository,
                          Map<Integer, Booking> bookings, Map<String, List<Booking>> waitListQueue) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.bookings = bookings;
        this.waitListQueue = waitListQueue;
    }

    public void addBooking(Doctor doctor, Patient patient, TimeSlot timeSlot) {
//        Check Doctor exist
        if(doctorRepository.isDoctorRegistered(doctor.getId())) {
            return;
        }
//        Check patient registered
        if (patientRepository.isPatientRegistered(patient.getId())) {
            return;
        }

        if(patientSlots.containsKey(patient.getId())){
            for(TimeSlot slot:patientSlots.get(patient.getId())){
                if(slot.getStartTime().equals(timeSlot.getStartTime())){
                    return;
                }
            }
        }else{
            patientSlots.put(patient.getId(), new ArrayList<>());
        }
//        Check for this slot whether doctor is available or not
        //        Create a booking
        Doctor doctorDetails = doctorRepository.getDoctorInfo(doctor.getId());
        Map<TimeSlot, Boolean> slots = doctorDetails.getTimeSlotMap();
        for (Map.Entry<TimeSlot,Boolean> slot: slots.entrySet()) {
            if(slot.getKey().getStartTime().equals(timeSlot.getStartTime()) && slot.getValue()){
                slots.put(slot.getKey(),false);
                doctorDetails.setTimeSlotMap(slots);
                patientSlots.get(patient.getId()).add(slot.getKey());
                Booking booking = new Booking(id++, patient, doctor, slot.getKey(), Boolean.FALSE);
                bookings.put(booking.getId(), booking);
                System.out.println("Appointment Booked Successfully Booking id "+ booking.getId());
                doctorRepository.updateDoctorInfo(doctorDetails);
                return;
            }
        }
        Booking booking = new Booking(id++, patient, doctor, timeSlot, Boolean.TRUE);
        String key = doctor.getId() + timeSlot.getStartTime();
        if (waitListQueue.containsKey(key)) {
           List<Booking> queueList = waitListQueue.get(key);
           queueList.add(booking);
        } else {
            waitListQueue.put(key, List.of(booking));
        }
        System.out.println("Added to the waitlist. Booking id: "+booking.getId());

//        If  slot is occupied - Add to waitlist queue
//        If slot is avaialble made the booking and set slot flag of doctor to false
    }

    public void cancelBooking(int bookingId) {
        if (bookings.containsKey(bookingId)) {
            Booking booking = bookings.get(bookingId);
            bookings.remove(bookingId);
            String key = booking.getDoctor().getId() + booking.getTimeSlot().getStartTime();
            if (waitListQueue.containsKey(key)) {
                List<Booking> waitLists = waitListQueue.get(key);
                waitLists.remove(booking);
            }
            doctorRepository.freeSlot(booking.getDoctor().getId(), booking.getTimeSlot());
        }
    }

}
