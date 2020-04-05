package org.hospitalityprogram.furnituredonations.controllers;

import org.hospitalityprogram.furnituredonations.data.StudentProfileRepository;
import org.hospitalityprogram.furnituredonations.models.StudentProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", "Nothing Here");
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

    @GetMapping("profile")
    public String renderProfile(HttpSession session, Model model) {
        System.out.println(session.getAttribute("user"));
        Optional<StudentProfile> result = studentProfileRepository.findById(18);
        model.addAttribute("studentProfile", result.get());
        model.addAttribute("title", "Profile");
        return "student/profile/index";
    }

    @GetMapping("profile/create")
    public String renderCreateProfile(Model model) {
        model.addAttribute(new StudentProfile());
        model.addAttribute("title", "Create Profile");
        return "student/profile/create";
    }

    @PostMapping("profile/create")
    public String processCreateProfile(@ModelAttribute @Valid StudentProfile newStudent, Errors errors, Model model)  {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Profile");
            model.addAttribute("errorMsg", "Bad data!");
            return "student/profile/create";
        }
        studentProfileRepository.save(newStudent);
        return "redirect:/student/profile";
    }

    @GetMapping("profile/edit")
    public String renderEditProfile(Model model) {
        model.addAttribute("title", "Edit Profile");
        return "student/profile/edit";
    }

}
