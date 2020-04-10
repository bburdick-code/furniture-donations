package org.hospitalityprogram.furnituredonations.controllers;

import org.hospitalityprogram.furnituredonations.data.ItemCategoryRepository;
import org.hospitalityprogram.furnituredonations.data.ItemRepository;
import org.hospitalityprogram.furnituredonations.data.UserRepository;
import org.hospitalityprogram.furnituredonations.models.User;
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

    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", "Nothing Here");
        return "volunteer/index";
    }

    @GetMapping("profile")
    public String renderProfile(HttpSession session, Model model) {
        Optional<User> result = userRepository.findById((int)session.getAttribute("user"));
        model.addAttribute("user", result.get());
        model.addAttribute("title", "Profile");
        return "volunteer/profile/index";
    }

    @GetMapping("profile/edit")
    public String renderEditProfile(HttpSession session, Model model) {
        Optional<User> result = userRepository.findById((int)session.getAttribute("user"));
        model.addAttribute("user", result.get());
        model.addAttribute("title", "Edit Info");
        return "volunteer/profile/edit";
    }

    @PostMapping("profile/edit")
    public String processEditProfile(@ModelAttribute User user, @RequestParam int id, HttpSession session, Errors errors, Model model) {
        Optional<User> resultOb = userRepository.findById(id);
        User result = resultOb.get();

        if (user.getUserAddress() != null) { result.setUserAddress(user.getUserAddress()); }
        if (user.getUserPhone() != null) { result.setUserPhone(user.getUserPhone()); }
        if (user.getFirstName() != null) { result.setFirstName(user.getFirstName()); }
        if (user.getLastName() != null) { result.setLastName(user.getLastName()); }
        if (user.getNickname() != null) { result.setNickname(user.getNickname()); }
        if (user.getPersonalEmail() != null) { result.setPersonalEmail(user.getPersonalEmail()); }
        if (user.getCountry() != null) { result.setCountry(user.getCountry()); }
        if (user.getGender() != null) { result.setGender(user.getGender()); }
        if (user.getMaritalStatus() != null) { result.setMaritalStatus(user.getMaritalStatus()); }

        userRepository.save(result);

        return "redirect:/volunteer/profile";
    }

    @GetMapping("settings")
    public String settings(Model model) {
        model.addAttribute("title", "Settings");
        return "volunteer/settings";
    }

}
