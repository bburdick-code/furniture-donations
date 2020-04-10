package org.hospitalityprogram.furnituredonations.models;

import org.hospitalityprogram.furnituredonations.controllers.AuthenticationController;
import org.hospitalityprogram.furnituredonations.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);

        // The user is logged in
        if (user != null) {
            if (user.getUserType().ordinal()==0 && request.getRequestURI().startsWith("/student")) {
                return true;
            } else if (user.getUserType().ordinal()==1 && request.getRequestURI().startsWith("/volunteer")) {
                return true;
            } else if (user.getUserType().ordinal()==2 && request.getRequestURI().startsWith("/admin")) {
                return true;
            }
        }
        // The user is NOT logged in
        response.sendRedirect("/login");  //  "/login"
        return false;
    }

    private static boolean isWhitelisted(String path) {
        // TODO: Remove system printouts
         System.out.println(path);
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

