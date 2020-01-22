package com.iispiridis.doctorservice.resources;

import com.iispiridis.doctorservice.models.DTOs.DoctorDto;
import com.iispiridis.doctorservice.models.Doctor;
import com.iispiridis.doctorservice.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorResource
{
    @Autowired
    DoctorService doctorService;

    @RequestMapping("/{doctorId}")
    public Doctor getDoctor(@PathVariable("doctorId") int doctorId)
    {
        return doctorService.getDoctorById(doctorId);

    }

    @PostMapping("/add")
    public void addDoctor(@RequestBody Doctor doctor)
    {
        System.out.println("test: " + doctor.getUid());
        doctorService.save(doctor);
    }

    @GetMapping("/isregistered/{uid}")
    public boolean isRegistered(@PathVariable("uid") int uid)
    {
        return doctorService.isRegistered(uid);
    }


    @GetMapping("/all")
    public DoctorDto getAll()
    {
        DoctorDto doc = new DoctorDto();
        doc.setDoctors(doctorService.getAll());
        return doc;
    }

}
