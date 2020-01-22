package com.iispiridis.doctorservice.services;

import com.iispiridis.doctorservice.models.Doctor;
import com.iispiridis.doctorservice.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    public Doctor getDoctorById(int id)
    {
        Optional<Doctor> d = doctorRepository.findByuid(id);
        return d.get();
    }

    public void save(Doctor doctor)
    {
        doctorRepository.save(doctor);
    }

    public boolean isRegistered(int uid)
    {
        Optional<Doctor> doctor = doctorRepository.findByuid(uid);

        if ( !doctor.isPresent() || doctor.get().getName() == null ) return false;
        else return true;
    }

    public List<Doctor> getAll()
    {
        return doctorRepository.findAll();
    }
}
