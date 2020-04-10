package org.hospitalityprogram.furnituredonations.controllers;

import org.hospitalityprogram.furnituredonations.data.DonationBatchRepository;
import org.hospitalityprogram.furnituredonations.data.ItemCategoryRepository;
import org.hospitalityprogram.furnituredonations.models.Donation;
import org.hospitalityprogram.furnituredonations.models.DonationBatch;
import org.hospitalityprogram.furnituredonations.models.ItemCategory;
import org.hospitalityprogram.furnituredonations.models.enums.DonationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

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
    public String processDonation(@ModelAttribute @Valid DonationBatch donationBatch, Errors errors, @RequestParam(required = false) int[] itemCategoryArray, @RequestParam(required = false) String [] itemDescriptionArray, Model model) {

        for (int i : itemCategoryArray) {
            System.out.println(i);
        }
        for (String s : itemDescriptionArray) {
            System.out.println(s);
        }

        if(errors.hasErrors()) {
            model.addAttribute("title", "Donation");
            return "donate/index";
        }

        int length = itemCategoryArray.length;
        boolean found = false;
        for (int i=0; i < length; i++) {
            if (itemCategoryArray[i] != 0) {
                found = true;
            }
        }

        if(!found) {
            model.addAttribute("title", "Donation");
            model.addAttribute("itemError", "A donation must include at least 1 item");
            return "donate/index";
        }

        for (int i =0; i < length ; i++) {
            if (itemCategoryArray[i] != 0 ) {
                Optional<ItemCategory> result = itemCategoryRepository.findById(itemCategoryArray[i]);
                ItemCategory itemCategory = result.get();
                Donation donation = new Donation(itemCategory, itemDescriptionArray[i], DonationStatus.POSTED, donationBatch);
                donationBatch.addDonation(donation);
            }
        }
        donationBatch.setDonorPostedDate(new Date());
        donationBatchRepository.save(donationBatch);

        return "redirect:/donate/success";
    }

    @GetMapping("success")
    public String donationSuccessful(Model model) {
        model.addAttribute("title", "Donation Successful!");
        return "donate/success";
    }

}
