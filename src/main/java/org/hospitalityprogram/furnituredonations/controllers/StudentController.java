package org.hospitalityprogram.furnituredonations.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("student")
public class StudentController {


    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", "Profile");
        return "student/index";
    }

    @GetMapping("items")
    public String items(Model model) {
        model.addAttribute("title", "Requested Items");
        return "student/items";
    }

    @GetMapping("settings")
    public String settings(Model model) {
        model.addAttribute("title", "Settings");
        return "student/settings";
    }
}
