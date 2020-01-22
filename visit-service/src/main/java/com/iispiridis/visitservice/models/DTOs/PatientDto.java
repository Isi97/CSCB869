package com.iispiridis.visitservice.models.DTOs;

import com.iispiridis.visitservice.models.Patient;

import java.util.List;

public class PatientDto
{
    List<Patient> patients;

    public PatientDto(){}

    public PatientDto(List<Patient> patients) {
        this.patients = patients;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}
