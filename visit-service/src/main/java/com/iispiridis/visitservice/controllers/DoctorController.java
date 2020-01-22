package com.iispiridis.visitservice.controllers;


import com.iispiridis.visitservice.models.*;
import com.iispiridis.visitservice.models.DTOs.PendingVisitDto;
import com.iispiridis.visitservice.repositories.UserRepository;
import com.iispiridis.visitservice.services.CustomUserDetailsService;
import com.iispiridis.visitservice.services.DoctorService;
import com.iispiridis.visitservice.services.PatientService;
import com.iispiridis.visitservice.services.VisitService;
import com.netflix.discovery.converters.Auto;
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
@RequestMapping("/doctor")
public class DoctorController
{
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CustomUserDetailsService userService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    VisitService visitService;

    @Autowired
    PatientService patientService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public String registerDoctor(@ModelAttribute("doctor") Doctor doctor)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userService.findByUsername(auth.getName());
        User u = user.get();

        doctor.setUid(u.getId());

        restTemplate.postForObject("http://doctor-service/doctors/add", doctor, Doctor.class);
        return "redirect:/home";
    }

    @ResponseBody
    @GetMapping("/all")
    public List<Doctor> getAll()
    {
        return doctorService.getAll();
    }

    @GetMapping("/pendingvisits/{uid}")
    public String pendingVisits(@PathVariable("uid") int uid, Model model)
    {
        Iterable<Visit> visits = visitService.getDoctorPendingVisits(uid);
        List<PendingVisitDto> visitdtos = new ArrayList<>();

        for ( Visit v : visits)
        {
            PendingVisitDto vdto = new PendingVisitDto();
            vdto.setVisit(v);
            vdto.setPname(patientService.getPatient(v.getPid()).getName());

            if ( v.getDiagnosis() == null ) vdto.setDiagnosed(false);
            else vdto.setDiagnosed(true);

            visitdtos.add(vdto);
        }
        model.addAttribute("visits", visitdtos);
        return "doctor/doctor-pending-visits";
    }

    @GetMapping("/visit/{vid}")
    public String visit(@PathVariable("vid") int vid, Model model)
    {
        model.addAttribute("visit", visitService.getVisit(vid));
        model.addAttribute("vid", vid);
        return "doctor/doctor-visit";
    }

    @PostMapping("/visit/{vid}")
    public String visit(@ModelAttribute("visit") Visit visit, @PathVariable("vid") int vid)
    {
        Visit v = visitService.getVisit(vid);

        v.setDiagnosis(visit.getDiagnosis());
        v.setVisitDate(visit.getVisitDate());
        v.setSickLeave(visit.isSickLeave());
        v.setSickLeaveEnd(visit.getSickLeaveEnd());

        visitService.save(v);
        return "redirect:/home";
    }

    @GetMapping("/patients/{did}")
    public String getDoctorPatients(@PathVariable("did") int did, Model model)
    {
        int result =0;
        List<Patient> plist = patientService.getAll().getPatients();
        for ( Patient p : plist)
        {
            if (p.getDid() == did )
            {
                result++;
            }
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        String role = auth.getAuthorities().toArray()[0].toString();

        Optional<User> user = userRepository.findByusername(name);
        int cuid = user.get().getId();

        model.addAttribute("cuid", cuid);
        model.addAttribute("uname", name);
        model.addAttribute("title", "Home");
        model.addAttribute("urole", role);
        model.addAttribute("pnum", result);
        return "/index.html";
    }
}
