package org.hospitalityprogram.furnituredonations.controllers;

import org.hospitalityprogram.furnituredonations.data.DonationBatchRepository;
import org.hospitalityprogram.furnituredonations.data.StudentRepository;
import org.hospitalityprogram.furnituredonations.models.StudentProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("volunteer/requests")
public class VolunteerRequestsController {

    @Autowired
    public StudentRepository studentRepository;

    @Autowired
    public DonationBatchRepository donationBatchRepository;

    @GetMapping
    public String viewRequests(Model model) {
        List<StudentProfile> students = studentRepository.findAllWithItems();


        model.addAttribute("students", students);
        model.addAttribute("donationBatches", donationBatchRepository.findAll());
        model.addAttribute("title", "Student Requests");
        return "volunteer/requests/index";
    }

}
