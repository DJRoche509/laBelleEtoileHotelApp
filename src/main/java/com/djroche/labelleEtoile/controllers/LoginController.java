package com.djroche.labelleEtoile.controllers;

import com.djroche.labelleEtoile.dtos.UserDto;
import com.djroche.labelleEtoile.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView getLoginForm() {
        return new ModelAndView("login");
    }

    @PostMapping
    public ModelAndView login(String username, String password) {
        UserDto user = userService.getUserByUsername(username);
        if (user == null) {
            return new ModelAndView("login", "error", "Invalid username or password");
        }
        if (!user.getPassword().equals(password)) {
            return new ModelAndView("login", "error", "Invalid username or password");
        }
        return new ModelAndView("redirect:/");
    }
}