package com.iispiridis.patientservice.resources;

import com.iispiridis.patientservice.models.Patient;
import com.iispiridis.patientservice.models.PatientDto;
import com.iispiridis.patientservice.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/patient")
public class PatientResource {
    @Autowired
    PatientService patientService;

    @RequestMapping("/{uid}")
    public Patient getPatientById(@PathVariable("uid") int uid)
    {
        return patientService.findByUid(uid).get();
    }

    @PostMapping("/add")
    public void addPatient(@RequestBody Patient p)
    {
        //System.out.println(p.toString());
        patientService.save(p);
    }

    @GetMapping("/completed/{patientuid}")
    public boolean patientRequiresRegistration(@PathVariable("patientuid") int patientuid)
    {
        Optional<Patient> p = patientService.findByUid(patientuid);
        if ( !p.isPresent() || p.get().isCompleted() ==false)
        {
            return true;
        } else return false;
    }

    @GetMapping("/all")
    public PatientDto getAll()
    {
        PatientDto d = new PatientDto();
        d.setPatients(patientService.getAll());
        return d;
    }
}
