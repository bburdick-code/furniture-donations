package org.hospitalityprogram.furnituredonations.controllers;

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
@RequestMapping("student")
public class StudentController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", "Nothing Here");
        return "student/index";
    }

    @GetMapping("items")
    public String items(Model model) {
        model.addAttribute("title", "Requested Items");
        return "student/items/index";
    }

    @GetMapping("settings")
    public String settings(Model model) {
        model.addAttribute("title", "Settings");
        return "student/settings";
    }

    @GetMapping("profile")
    public String renderProfile(HttpSession session, Model model) {
        Optional<User> result = userRepository.findById((int)session.getAttribute("user"));
        model.addAttribute("user", result.get());
        model.addAttribute("title", "Profile");
        return "student/profile/index";
    }

    @GetMapping("profile/edit")
    public String renderEditProfile(HttpSession session, Model model) {
        Optional<User> result = userRepository.findById((int)session.getAttribute("user"));
        model.addAttribute("user", result.get());
        model.addAttribute("title", "Edit Info");
        return "student/profile/edit";
    }

    @PostMapping("profile/edit")
    public String processEditProfile(@ModelAttribute User user, @RequestParam int id, HttpSession session, Errors errors, Model model) {
//        System.out.println("The user id for this UPDATE is: " + user.getId());
//        System.out.println("The First Name is " + user.getFirstName());

//        @RequestParam int userId,
//        @RequestParam String userAddress, @RequestParam String userPhone,
//        @RequestParam String firstName, @RequestParam String lastName,
//        @RequestParam String nickname, @RequestParam String personalEmail,
//        @RequestParam String country, @RequestParam String gender,
//        @RequestParam String maritalStatus,

//        if(errors.hasErrors()){
//            System.out.println("had errors");
//            model.addAttribute("title", "Edit Again");
//            return "student/profile/edit";
//        }
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

        return "redirect:/student/profile";
    }

}
