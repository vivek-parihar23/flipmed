package org.flipmed.repository;

import org.flipmed.models.Patient;

import java.util.HashMap;
import java.util.Map;

public class PatientRepository {
    Map<Integer, Patient> patients = new HashMap<>();

    public void registerPatient(Patient patient){
        if(patients.containsKey(patient.getId())){
                System.out.println("Patient already registered");
                return;
        }
        patients.put(patient.getId(), patient);
    }

    public boolean isPatientRegistered(Integer patientId){
        return patients.containsKey(patientId);
    }
}
