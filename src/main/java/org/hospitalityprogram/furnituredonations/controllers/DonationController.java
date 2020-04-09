package org.hospitalityprogram.furnituredonations.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("donate")
public class DonationController {

    @GetMapping
    public String renderDonation(Model model) {
        model.addAttribute("title", "Donate Item");
        return "donate";
    }

    @PostMapping
    public String processDonation(Model model) {

        return "redirect:/donate/success";
    }

}
