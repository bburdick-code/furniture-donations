package org.hospitalityprogram.furnituredonations.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("volunteer/requests")
public class VolunteerRequestsController {

    @GetMapping
    public String viewRequests(Model model) {
        model.addAttribute("title", "Student Requests");
        return "volunteer/requests/index";
    }

}
