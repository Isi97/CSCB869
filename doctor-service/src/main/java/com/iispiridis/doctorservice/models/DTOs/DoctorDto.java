package com.iispiridis.doctorservice.models.DTOs;


import com.iispiridis.doctorservice.models.Doctor;

import java.util.List;

public class DoctorDto
{
    public DoctorDto(){}

    List<Doctor> doctors;

    public DoctorDto(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }
}
