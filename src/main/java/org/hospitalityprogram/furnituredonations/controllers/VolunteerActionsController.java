package org.hospitalityprogram.furnituredonations.controllers;

import org.hospitalityprogram.furnituredonations.data.*;
import org.hospitalityprogram.furnituredonations.models.*;
import org.hospitalityprogram.furnituredonations.models.enums.DonationStatus;
import org.hospitalityprogram.furnituredonations.models.enums.ItemStatus;
import org.hospitalityprogram.furnituredonations.models.enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("volunteer/actions")
public class VolunteerActionsController {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private DonationBatchRepository donationBatchRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public String displayActions(Model model) {
        model.addAttribute("title", "Admin Actions");
        return "volunteer/actions/index";
    }

    @GetMapping("itemCategory")
    public String renderViewItemCats(Model model) {
        model.addAttribute("title", "Item Categories");
        model.addAttribute("categories", itemCategoryRepository.findAll());
        model.addAttribute(new ItemCategory());
        return "volunteer/actions/itemCategory";
    }

    @PostMapping("itemCategory")
    public String processAddItemCatForm(@ModelAttribute @Valid ItemCategory newItemCategory, Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("errorMsg", "Invalid category name.");
        } else {
            itemCategoryRepository.save(newItemCategory);
        }

        model.addAttribute("title", "Item Categories");
        model.addAttribute("categories", itemCategoryRepository.findAll());
        model.addAttribute(new ItemCategory());

        return "/volunteer/actions/itemCategory";
    }

    @GetMapping("seed")
    public String displaySeedDB(Model model) {
        model.addAttribute("title", "Seed Database");
        return "volunteer/actions/seed";
    }

    @PostMapping("seed")
    public String processSeedDB(Model model) {
        model.addAttribute("title", "Seed Database");

        donationBatchRepository.deleteAll();
        donationRepository.deleteAll();
        volunteerRepository.deleteAll();
        studentRepository.deleteAll();
        itemRepository.deleteAll();
        itemCategoryRepository.deleteAll();
        userRepository.deleteAll();
        System.out.println("Database Reset");

        User user1 = new User("admin@mail.com","asdfasdf", "123 Franklin Blvd", "555-555-5555", UserType.ADMIN);
        userRepository.save(user1);
        VolunteerProfile volunteer1 = new VolunteerProfile("Ben", "Burdick", user1);
        volunteerRepository.save(volunteer1);
        System.out.println("Admin Account created");

        ItemCategory itemCat1 = new ItemCategory("Table");
        ItemCategory itemCat2 = new ItemCategory("Desk");
        ItemCategory itemCat3 = new ItemCategory("Twin Bed");

        itemCategoryRepository.save(itemCat1);
        itemCategoryRepository.save(itemCat2);
        itemCategoryRepository.save(itemCat3);
        itemCategoryRepository.save(new ItemCategory("Full Bed"));
        itemCategoryRepository.save(new ItemCategory("Sofa"));
        itemCategoryRepository.save(new ItemCategory("Teapot"));
        itemCategoryRepository.save(new ItemCategory("Toaster"));

        User user2 = new User("becky@mail.com", "asdfasdf", "456 Harvard Ave", "555-555-5555", UserType.VOLUNTEER);
        userRepository.save(user2);
        VolunteerProfile volunteer2 = new VolunteerProfile("Becky", "Robinson", user2);
        volunteerRepository.save(volunteer2);
        System.out.println("Volunteer 1 created");

        User user3 = new User("jason@mail.com", "asdfasdf", "789 Brooklyn Rd", "555-555-5555", UserType.STUDENT);
        userRepository.save(user3);
        StudentProfile student1 = new StudentProfile("Jason", "Hinnman", "", "", "Germany", "Male", "Single", user3);
        student1.addItem(new Item(itemCat1, ItemStatus.REQUESTED, 1, new Date(), student1));
        student1.addItem(new Item(itemCat2, ItemStatus.REQUESTED, 2, new Date(), student1));
        student1.addItem(new Item(itemCat3, ItemStatus.REQUESTED, 3, new Date(), student1));
        studentRepository.save(student1);
        System.out.println("Student 1 created");

        DonationBatch donationBatch1 = new DonationBatch("Gary", "gary@mail.com", "444 Queenland Pkwy", "62025", "555-555-5555", new Date());
        donationBatch1.addDonation(new Donation(itemCat2, "", DonationStatus.POSTED, donationBatch1));
        donationBatch1.addDonation(new Donation(itemCat3, "", DonationStatus.POSTED, donationBatch1));
        donationBatchRepository.save(donationBatch1);
        System.out.println("DonationBatch 1 created");



        return "redirect:/logout";
    }

}
