package org.hospitalityprogram.furnituredonations.controllers;

import org.hospitalityprogram.furnituredonations.data.UserRepository;
import org.hospitalityprogram.furnituredonations.models.User;
import org.hospitalityprogram.furnituredonations.models.dto.LoginDTO;
import org.hospitalityprogram.furnituredonations.models.dto.RegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;

    private static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }

    @GetMapping("/student/register")
    public String displayRegistrationForm(Model model) {
        model.addAttribute(new RegistrationDTO());
        model.addAttribute("title", "Register");
        return "student/register";
    }

    @PostMapping("/student/register")
    public String processRegistrationForm(@ModelAttribute @Valid RegistrationDTO registrationDTO,
                                          Errors errors, HttpServletRequest request, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            return "student/register";
        }

        User existingUser = userRepository.findByUsername(registrationDTO.getUsername());

        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
            model.addAttribute("title", "Register");
            return "student/register";
        }

        String password = registrationDTO.getPassword();
        String verifyPassword = registrationDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Register");
            return "student/register";
        }

        User newUser = new User(registrationDTO.getUsername(), registrationDTO.getPassword(), registrationDTO.getUserAddress(), registrationDTO.getUserPhone(), registrationDTO.getUserType());
        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);

        return "redirect:/student";
    }

    @GetMapping("/volunteer/register")
    public String displayVolunteerRegistrationForm(Model model) {
        model.addAttribute(new RegistrationDTO());
        model.addAttribute("title", "Register");
        return "volunteer/register";
    }

    @PostMapping("/volunteer/register")
    public String processVolunteerRegistrationForm(@ModelAttribute @Valid RegistrationDTO registrationDTO,
                                          Errors errors, HttpServletRequest request, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            return "volunteer/register";
        }

        User existingUser = userRepository.findByUsername(registrationDTO.getUsername());

        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
            model.addAttribute("title", "Register");
            return "volunteer/register";
        }

        String password = registrationDTO.getPassword();
        String verifyPassword = registrationDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Register");
            return "volunteer/register";
        }

        User newUser = new User(registrationDTO.getUsername(), registrationDTO.getPassword(), registrationDTO.getUserAddress(), registrationDTO.getUserPhone(), registrationDTO.getUserType());
        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);

        return "redirect:/volunteer";
    }

    @GetMapping("/login")
    public String displayLoginForm(Model model) {
        model.addAttribute(new LoginDTO());
        model.addAttribute("title", "Log In");
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute @Valid LoginDTO loginDTO,
                                   Errors errors, HttpServletRequest request,
                                   Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Log In");
            return "login";
        }

        User theUser = userRepository.findByUsername(loginDTO.getUsername());

        if (theUser == null) {
            errors.rejectValue("username", "user.invalid", "The given username does not exist");
            model.addAttribute("title", "Log In");
            return "login";
        }

        String password = loginDTO.getPassword();

        if (!theUser.isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
            model.addAttribute("title", "Log In");
            return "login";
        }

        setUserInSession(request.getSession(), theUser);
        int userTypeOrd = theUser.getUserType().ordinal();

        if (userTypeOrd == 0) {
            return "redirect:/student"; }
        else if (userTypeOrd == 1) {
            return "redirect:/volunteer"; }
        else if (userTypeOrd == 2) {
            return "redirect:/admin"; }
        else { return "redirect:/home"; }

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login";
    }
}
