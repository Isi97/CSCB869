package com.iispiridis.visitservice.controllers;

import com.iispiridis.visitservice.repositories.UserRepository;
import com.iispiridis.visitservice.models.Doctor;
import com.iispiridis.visitservice.models.Patient;
import com.iispiridis.visitservice.models.User;
import com.iispiridis.visitservice.services.DoctorService;
import com.iispiridis.visitservice.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class HomeController
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    PatientService patientService;

    @Autowired
    DoctorService doctorService;

    @GetMapping({ "/home", "/" })
    public String home(Model model)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        String role = auth.getAuthorities().toArray()[0].toString();

        Optional<User> user = userRepository.findByusername(name);
        int cuid = user.get().getId();

        System.out.println(name + "\t" + role);

        // patientIsRegistered actually returns whether the patient IS IN NEED of registration, TODO change method name later
        if ( user.get().getRoles().equals("Patient") && patientService.patientIsRegistered(user.get().getId()) == true )
        {
            model.addAttribute("patient", new Patient());
            model.addAttribute("doctors", doctorService.getAll());
            return "patient/patient-registration";
        }
        else if ( user.get().getRoles().equals("Doctor") && doctorService.isRegistered(user.get().getId()) == false )
        {
            model.addAttribute("doctor", new Doctor());
            model.addAttribute("cuid", cuid);
            return "doctor/doctor-registration";
        }

        model.addAttribute("cuid", cuid);
        model.addAttribute("uname", name);
        model.addAttribute("title", "Home");
        model.addAttribute("urole", role);

        return "index.html";
    }
}
