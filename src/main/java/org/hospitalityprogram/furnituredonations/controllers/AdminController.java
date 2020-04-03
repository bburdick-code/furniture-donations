package org.hospitalityprogram.furnituredonations.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", "Profile");
        return "admin/index";
    }

    @GetMapping("requests")
    public String requests(Model model) {
        model.addAttribute("title", "All Item Requests");
        return "admin/requests";
    }

    @GetMapping("actions")
    public String settings(Model model) {
        model.addAttribute("title", "Admin Actions");
        return "admin/actions";
    }

}
