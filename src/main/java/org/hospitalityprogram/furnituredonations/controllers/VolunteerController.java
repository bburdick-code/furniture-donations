package org.hospitalityprogram.furnituredonations.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("volunteer")
public class VolunteerController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", "Profile");
        return "volunteer/index";
    }

    @GetMapping("requests")
    public String requests(Model model) {
        model.addAttribute("title", "All Item Requests");
        return "volunteer/requests";
    }

}
