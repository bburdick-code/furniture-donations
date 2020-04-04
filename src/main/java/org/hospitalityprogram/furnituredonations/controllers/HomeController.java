package org.hospitalityprogram.furnituredonations.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", "Homepage");
        return "index";
    }

    @GetMapping("donate")
    public String donate(Model model) {
        model.addAttribute("title", "Donate Item");
        return "donate";
    }
}
