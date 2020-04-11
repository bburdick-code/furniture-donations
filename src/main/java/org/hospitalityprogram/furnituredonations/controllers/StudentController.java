package org.hospitalityprogram.furnituredonations.controllers;

import org.hospitalityprogram.furnituredonations.data.ItemCategoryRepository;
import org.hospitalityprogram.furnituredonations.data.ItemRepository;
import org.hospitalityprogram.furnituredonations.data.StudentRepository;
import org.hospitalityprogram.furnituredonations.data.UserRepository;
import org.hospitalityprogram.furnituredonations.models.StudentProfile;
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
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ItemCategoryRepository itemCategoryRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", "Nothing Here");
        return "student/index";
    }

    @GetMapping("profile")
    public String renderProfile(HttpSession session, Model model) {
        Optional<User> result = userRepository.findById((int)session.getAttribute("user"));
        model.addAttribute("user", result.get());

        StudentProfile student = studentRepository.findByUserId((int)session.getAttribute("user"));
        model.addAttribute("student", student);
        model.addAttribute("title", "Profile");
        return "student/profile/index";
    }

    @GetMapping("profile/edit")
    public String renderEditProfile(HttpSession session, Model model) {
        Optional<User> result = userRepository.findById((int)session.getAttribute("user"));
        model.addAttribute("user", result.get());

        StudentProfile student = studentRepository.findByUserId((int)session.getAttribute("user"));
        model.addAttribute("student", student);
        model.addAttribute("title", "Edit Info");
        return "student/profile/edit";
    }

    @PostMapping("profile/edit")
    public String processEditProfile(@ModelAttribute User updateUser, @ModelAttribute StudentProfile updateStudent, HttpSession session, Errors errors, Model model) {
        int userId = (int)session.getAttribute("user");

        StudentProfile student = studentRepository.findByUserId(userId);
        Optional<User> resultOb = userRepository.findById(userId);
        User user = resultOb.get();

        if (updateUser.getUserAddress() != null) { user.setUserAddress(updateUser.getUserAddress()); }
        if (updateUser.getUserPhone() != null) { user.setUserPhone(updateUser.getUserPhone()); }

        if (updateStudent.getFirstName() != null) { student.setFirstName(updateStudent.getFirstName()); }
        if (updateStudent.getLastName() != null) { student.setLastName(updateStudent.getLastName()); }
        if (updateStudent.getNickname() != null) { student.setNickname(updateStudent.getNickname()); }
        if (updateStudent.getPersonalEmail() != null) { student.setPersonalEmail(updateStudent.getPersonalEmail()); }
        if (updateStudent.getCountry() != null) { student.setCountry(updateStudent.getCountry()); }
        if (updateStudent.getGender() != null) { student.setGender(updateStudent.getGender()); }
        if (updateStudent.getMaritalStatus() != null) { student.setMaritalStatus(updateStudent.getMaritalStatus()); }

        studentRepository.save(student);
        userRepository.save(user);

        return "redirect:/student/profile";
    }

    @GetMapping("settings")
    public String settings(Model model) {
        model.addAttribute("title", "Settings");
        return "student/settings";
    }

}
