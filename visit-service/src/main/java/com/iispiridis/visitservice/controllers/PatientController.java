package com.iispiridis.visitservice.controllers;


import com.iispiridis.visitservice.models.DTOs.PendingVisitDto;
import com.iispiridis.visitservice.models.Doctor;
import com.iispiridis.visitservice.models.Patient;
import com.iispiridis.visitservice.models.User;
import com.iispiridis.visitservice.models.Visit;
import com.iispiridis.visitservice.services.CustomUserDetailsService;
import com.iispiridis.visitservice.services.DoctorService;
import com.iispiridis.visitservice.services.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/patient")
public class PatientController
{
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    CustomUserDetailsService userService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    VisitService visitService;

    @RequestMapping("/{patientId}/doctor")
    public Doctor getPatientDoctor(@PathVariable("patientId") int patientId)
    {
        Patient patient = restTemplate.getForObject("http://patient-service/patient/" + patientId, Patient.class);
        Doctor doctor = restTemplate.getForObject("http://doctor-service/doctors/" + patient.getDid(), Doctor.class);

        return doctor;
    }

    @PostMapping("/register")
    public String registerPatient(@ModelAttribute("patient") Patient patient)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userService.findByUsername(auth.getName());
        User u = user.get();

        patient.setUid(u.getId());
        patient.setCompleted(true);

        System.out.println(patient.getName() + "\t" + patient.getEgn());

        restTemplate.postForObject("http://patient-service/patient/add", patient, Patient.class);
        return "redirect:/home";
    }

    @GetMapping("/requestvisit")
    public String requestVisit(Model model)
    {
        model.addAttribute("doctors", doctorService.getAll());
        model.addAttribute("visit", new Visit());

        return "patient/request-visit.html";
    }

    @PostMapping("/requestvisit")
    public String requestVisit(@ModelAttribute("visit") Visit visit)
    {
        User u = userService.getCurrentUser();
        visit.setPid(u.getId());
        visitService.save(visit);
        return "redirect:/home";
    }

    @GetMapping("/viewvisits/{pid}")
    public String viewVisits(@PathVariable("pid") int pid, Model model)
    {
        Iterable<Visit> v = visitService.getPatientVisits(pid);
        List<PendingVisitDto> vs = new ArrayList<>();

        for ( Visit visit : v )
        {
            PendingVisitDto d = new PendingVisitDto();
            d.setDname(doctorService.getDoctor(visit.getDid()).getName());
            d.setVisit(visit);
            vs.add(d);
        }

        model.addAttribute("visits", vs);
        return "patient/patient-visits";
    }


}
