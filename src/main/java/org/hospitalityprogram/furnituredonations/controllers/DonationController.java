package org.hospitalityprogram.furnituredonations.controllers;

import org.hospitalityprogram.furnituredonations.data.DonationBatchRepository;
import org.hospitalityprogram.furnituredonations.data.ItemCategoryRepository;
import org.hospitalityprogram.furnituredonations.models.DonationBatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("donate")
public class DonationController {

    @Autowired
    DonationBatchRepository donationBatchRepository;

    @Autowired
    ItemCategoryRepository itemCategoryRepository;

    @GetMapping
    public String renderDonation(Model model) {
        model.addAttribute(new DonationBatch());
        model.addAttribute("categories", itemCategoryRepository.findAll());
        model.addAttribute("title", "Donate Item");
        return "donate/index";
    }

    @PostMapping
    public String processDonation(@RequestParam(required = false) int[] itemCategoryArray, @RequestParam(required = false) String [] itemDescriptionArray, Model model) {

        for (int i : itemCategoryArray) {
            System.out.println(i);
        }
        for (String s : itemDescriptionArray) {
            System.out.println(s);
        }


        return "redirect:/donate/success";
    }

    @GetMapping("success")
    public String donationSuccessful(Model model) {
        model.addAttribute("title", "Donation Successful!");
        return "donate/success";
    }

}
