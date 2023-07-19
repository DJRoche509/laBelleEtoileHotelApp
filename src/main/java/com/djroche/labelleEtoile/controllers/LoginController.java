package com.djroche.labelleEtoile.controllers;

import com.djroche.labelleEtoile.dtos.UserDto;
import com.djroche.labelleEtoile.services.UserService;

import com.djroche.labelleEtoile.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping
    public ModelAndView getLoginForm() {
        return new ModelAndView("loginForm");
    }

    @PostMapping
    public ModelAndView login(String username, String password) {
        UserDto user = userServiceImpl.getUserByUsername(username);
        if (user == null) {
            return new ModelAndView("loginForm", "error", "Invalid username or password");
        }
        if (!user.getPassword().equals(password)) {
            return new ModelAndView("loginForm", "error", "Invalid username or password");
        }
        return new ModelAndView(new RedirectView("/reservationForm"));
    }
}