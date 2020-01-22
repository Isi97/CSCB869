package com.iispiridis.patientservice.services;

import com.iispiridis.patientservice.models.Patient;
import com.iispiridis.patientservice.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService
{
    @Autowired
    PatientRepository patientRepository;

    public Patient findByEGN(String egn)
    {
        Optional<Patient> patient = patientRepository.findByegn(egn);
        return patient.get();
    }

    public void save(Patient p)
    {
        patientRepository.save(p);
    }

    public Optional<Patient> findByUid(int uid)
    {
        return patientRepository.findByuid(uid);
    }

    public List<Patient> getAll()
    {
        return patientRepository.findAll();
    }

}
