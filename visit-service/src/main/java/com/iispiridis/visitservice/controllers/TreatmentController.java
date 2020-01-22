package com.iispiridis.visitservice.controllers;


import com.iispiridis.visitservice.models.Treatment;
import com.iispiridis.visitservice.models.Visit;
import com.iispiridis.visitservice.services.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/treatment")
public class TreatmentController
{
    @Autowired
    TreatmentService treatmentService;

    @GetMapping("/add/{vid}/{did}/{pid}")
    public String startAddingTreatment(@PathVariable("vid") int vid, @PathVariable("did") int did,
                                       @PathVariable("pid") int pid, Model model)
    {
        model.addAttribute("tlist", treatmentService.getVisiTreatments(vid));
        model.addAttribute("objtreatment", new Treatment());
        model.addAttribute("cvid", vid);
        model.addAttribute("cdid", did);
        model.addAttribute("cpid", pid);

        return "doctor/add-treatment";
    }

    @PostMapping("/add")
    public String startAddingTreatment(@ModelAttribute("treatment") Treatment treatment)
    {
        System.out.println(treatment.getVid());
        treatmentService.save(treatment);
        return "redirect:/home";
    }
}
