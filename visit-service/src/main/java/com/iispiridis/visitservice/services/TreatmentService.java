package com.iispiridis.visitservice.services;

import com.iispiridis.visitservice.models.Treatment;
import com.iispiridis.visitservice.repositories.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TreatmentService
{
    @Autowired
    TreatmentRepository treatmentRepository;

    public void save(Treatment treatment)
    {
        treatmentRepository.save(treatment);
    }

    public Iterable<Treatment> getVisiTreatments(int visitId)
    {
        return treatmentRepository.getVisitTreatments(visitId);
    }

    public Iterable<Treatment> getPatientTreatments(int patientId)
    {
        return treatmentRepository.getPatientTreatments(patientId);
    }

}
