package org.flipmed.service;

import org.flipmed.models.Patient;
import org.flipmed.repository.PatientRepository;

public class PatientService {
    PatientRepository patientsRepository;

    public PatientService(PatientRepository patientsRepository){
        this.patientsRepository = patientsRepository;
    }

    public void registerPatient(Patient patient){
        patientsRepository.registerPatient(patient);
        System.out.println(patient.getName()+ " registered successfully.");
    }
}
