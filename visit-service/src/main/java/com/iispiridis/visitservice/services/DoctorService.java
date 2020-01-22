package com.iispiridis.visitservice.services;


import com.iispiridis.visitservice.models.DTOs.DoctorDto;
import com.iispiridis.visitservice.models.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DoctorService
{
    @Autowired
    RestTemplate restTemplate;

    public boolean isRegistered(int uid)
    {
        Boolean isreg = restTemplate.getForObject("http://doctor-service/doctors/isregistered/"+uid, Boolean.class);
        return isreg;
    }

    public List<Doctor> getAll()
    {
        DoctorDto doc = restTemplate.getForObject("http://doctor-service/doctors/all", DoctorDto.class);
        return doc.getDoctors();
    }

    public Doctor getDoctor(int uid)
    {
       return restTemplate.getForObject("http://doctor-service/doctors/"+uid, Doctor.class);
    }


}
