package org.hospitalityprogram.furnituredonations.models;

import org.hospitalityprogram.furnituredonations.controllers.AuthenticationController;
import org.hospitalityprogram.furnituredonations.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AuthenticationFilter extends HandlerInterceptorAdapter {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationController authenticationController;

    private static final List<String> whitelist = Arrays.asList("/home", "/login", "/student/register", "/volunteer/register", "/logout", "/donate", "/styles", "/javascript", "/favicon", "/error");

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {

        // Don't require sign-in for whitelisted pages;
        if (isWhitelisted(request.getRequestURI())) {
            // returning true indicates that the request may proceed
            return true;
        }

        HttpSession session = request.getSession(false);
        User user = authenticationController.getUserFromSession(session);

        // The user is logged in
        if (user != null) {
            int userType = user.getUserType().ordinal();
            if (userType==0 && request.getRequestURI().startsWith("/student")) {
                return true;
            } else if ((userType==1 || userType==2) && request.getRequestURI().startsWith("/volunteer")) {
                return true; }
        }
        // The user is NOT logged in
        response.sendRedirect("/");  //  "/index"
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);

        if (request.getRequestURI().equals("/logout")) {
            return;
        }

        if (modelAndView != null) {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("user") == null ) {  // No session or no user assigned, render the Login HTML element
//                modelAndView.getModelMap().addAttribute("isLoggedIn", false);
            } else {  // A session is active, render the Logout HTML element
//                modelAndView.getModelMap().addAttribute("isLoggedIn", true);
                Optional<User> user = userRepository.findById((int)session.getAttribute("user"));
                switch (user.get().getUserType().ordinal()) { //Check if the user is an admin to render admin actions element
                    case 0:
                        modelAndView.getModelMap().addAttribute("isStudent",true);
                        break;
                    case 1 :
                        modelAndView.getModelMap().addAttribute("isVolunteer", true);
                        break;
                    case 2:
                        modelAndView.getModelMap().addAttribute("isAdmin", true);
                        break;
                }
            }
        }
    }

    private static boolean isWhitelisted(String path) {
        // TODO: Remove system printouts
        // System.out.println(path);
        // System.out.println(path.length());
        if (path.equals("/") || path.equals("")) {
            return true; }
        for (String pathRoot : whitelist) {
            if (path.startsWith(pathRoot)) {
                return true;
            }
        }
        return false;
    }
}

