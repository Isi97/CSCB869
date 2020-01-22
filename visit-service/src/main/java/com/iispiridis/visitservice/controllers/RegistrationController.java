package com.iispiridis.visitservice.controllers;


import com.iispiridis.visitservice.services.CustomUserDetailsService;
import com.iispiridis.visitservice.models.User;
import com.iispiridis.visitservice.models.UserRegistrationDto;
import com.iispiridis.visitservice.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/registration")
public class RegistrationController
{
    @Autowired
    CustomUserDetailsService userDetailsService;


    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model)
    {
        List<String> roleList = new ArrayList<String>();

        roleList.add("Patient");
        roleList.add("Doctor");
        roleList.add("Admin");

        model.addAttribute("userRoles", roleList);
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
                                      BindingResult result) {

        Optional<User> existing = userDetailsService.findByUsername(userDto.getName());

        if (existing.isPresent()) {
            result.rejectValue("name", null, "There is already an account registered with that username");
        }

        if (result.hasErrors()) {
            return "registration";
        }

        userDetailsService.save(userDto);
        return "redirect:/registration?success";
    }
}
