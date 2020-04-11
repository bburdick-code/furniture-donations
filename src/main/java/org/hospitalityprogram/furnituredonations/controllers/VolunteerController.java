package org.hospitalityprogram.furnituredonations.controllers;

import org.hospitalityprogram.furnituredonations.data.ItemCategoryRepository;
import org.hospitalityprogram.furnituredonations.data.ItemRepository;
import org.hospitalityprogram.furnituredonations.data.UserRepository;
import org.hospitalityprogram.furnituredonations.data.VolunteerRepository;
import org.hospitalityprogram.furnituredonations.models.User;
import org.hospitalityprogram.furnituredonations.models.VolunteerProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("volunteer")
public class VolunteerController {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemCategoryRepository itemCategoryRepository;

    @Autowired
    VolunteerRepository volunteerRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", "Nothing Here");
        return "volunteer/index";
    }

    @GetMapping("profile")
    public String renderProfile(HttpSession session, Model model) {
        int userId = (int)session.getAttribute("user");
        Optional<User> result = userRepository.findById(userId);
        VolunteerProfile volunteer = volunteerRepository.findByUserId(userId);
        model.addAttribute("user", result.get());
        model.addAttribute("volunteer", volunteer);
        model.addAttribute("title", "Profile");
        return "volunteer/profile/index";
    }

    @GetMapping("profile/edit")
    public String renderEditProfile(HttpSession session, Model model) {
        int userId = (int)session.getAttribute("user");
        Optional<User> result = userRepository.findById(userId);
        VolunteerProfile volunteer = volunteerRepository.findByUserId(userId);
        model.addAttribute("user", result.get());
        model.addAttribute("volunteer", volunteer);
        model.addAttribute("title", "Edit Info");
        return "volunteer/profile/edit";
    }

    @PostMapping("profile/edit")
    public String processEditProfile(@ModelAttribute User updateUser, @ModelAttribute VolunteerProfile updateVolunteer, HttpSession session, Errors errors, Model model) {
        int userId = (int)session.getAttribute("user");
        Optional<User> resultOb = userRepository.findById(userId);
        User user = resultOb.get();
        VolunteerProfile volunteer = volunteerRepository.findByUserId(userId);

        if (updateUser.getUserAddress() != null) { user.setUserAddress(updateUser.getUserAddress()); }
        if (updateUser.getUserPhone() != null) { user.setUserPhone(updateUser.getUserPhone()); }
        if (updateVolunteer.getName() != null) { volunteer.setName(updateVolunteer.getName()); }

        userRepository.save(user);
        volunteerRepository.save(volunteer);

        return "redirect:/volunteer/profile";
    }

    @GetMapping("settings")
    public String settings(Model model) {
        model.addAttribute("title", "Settings");
        return "volunteer/settings";
    }

}
