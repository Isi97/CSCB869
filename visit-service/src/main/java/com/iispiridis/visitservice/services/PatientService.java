package com.iispiridis.visitservice.services;

import com.iispiridis.visitservice.models.DTOs.PatientDto;
import com.iispiridis.visitservice.models.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Service
public class PatientService {
    @Autowired
    private RestTemplate restTemplate;

    public boolean patientIsRegistered(int uid)
    {
        Boolean object = restTemplate.getForObject("http://patient-service/patient/completed/" + uid, Boolean.class);
        return object;
    }

    public Patient getPatient(@PathVariable("patientId") int patiendId)
    {
        Patient p = restTemplate.getForObject("http://patient-service/patient/" + patiendId, Patient.class);
        return p;
    }

    public PatientDto getAll()
    {
        return restTemplate.getForObject("http://patient-service/patient/all", PatientDto.class);
    }
}
