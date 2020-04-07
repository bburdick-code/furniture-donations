package org.hospitalityprogram.furnituredonations.controllers;

import org.hospitalityprogram.furnituredonations.data.UserRepository;
import org.hospitalityprogram.furnituredonations.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("profile")
    public String index(HttpSession session, Model model) {
        Optional<User> result = userRepository.findById((int)session.getAttribute("user"));
        model.addAttribute("user", result.get());
        model.addAttribute("title", "Profile");
        return "admin/profile/index";
    }

    @GetMapping("requests")
    public String requests(Model model) {
        model.addAttribute("title", "All Item Requests");
        return "admin/requests";
    }

    @GetMapping("actions")
    public String settings(Model model) {
        model.addAttribute("title", "Admin Actions");
        return "admin/actions/index";
    }

}
