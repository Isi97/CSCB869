package com.iispiridis.visitservice.controllers;

import com.iispiridis.visitservice.models.Doctor;
import com.iispiridis.visitservice.models.Patient;
import com.iispiridis.visitservice.models.Treatment;
import com.iispiridis.visitservice.models.Visit;
import com.iispiridis.visitservice.services.DoctorService;
import com.iispiridis.visitservice.services.PatientService;
import com.iispiridis.visitservice.services.TreatmentService;
import com.iispiridis.visitservice.services.VisitService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/visit")
public class VisitController {
    @Autowired
    VisitService visitService;

    @Autowired
    PatientService patientService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    TreatmentService treatmentService;

    @GetMapping("/{vid}")
    public String getVisit(@PathVariable("vid") int vid, Model model)
    {
        Visit v = visitService.getVisit(vid);
        Patient p = patientService.getPatient(v.getPid());
        Doctor d = doctorService.getDoctor(v.getDid());
        Iterable<Treatment> t = treatmentService.getVisiTreatments(vid);

        model.addAttribute("visit", v);
        model.addAttribute("tlist", t);

        return "visits/view-visit";
    }
}
