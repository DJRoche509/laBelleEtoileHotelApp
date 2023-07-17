package com.djroche.labelleEtoile.controllers;

import com.djroche.labelleEtoile.dtos.UserDto;
import com.djroche.labelleEtoile.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")  //("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // POST /users
    @PostMapping("/")
    public String createUser(@ModelAttribute ("userDto") UserDto userDto) {
        // Hash the password before saving it to the database
        String passHash = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(passHash);
        userService.createUser(userDto);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String getUserDetails(@PathVariable("id") Long id, Model model) {
        UserDto user = userService.getUserById(id);
        if (user == null) {
            return "redirect:/users";
        }
        model.addAttribute("user", user);
        return "userDetails";
    }

    @GetMapping("/new")
    public String newUserForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "userForm";
    }

    // GET /users
    @GetMapping("/")
    public String getAllUsers(Model model) {
        List<UserDto> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "userList";
    }

    @GetMapping("/{id}/edit")
    public String editUserForm(@PathVariable("id") Long id, Model model) {
        UserDto user = userService.getUserById(id);
        if (user == null) {
            return "redirect:/users";
        }
        model.addAttribute("userDto", user);
        return "user-form";
    }

    @PostMapping("/{id}/update")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("userDto") UserDto userDto) {
        userDto.setId(id);
        userService.updateUser(userDto);
        return "redirect:/users/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }
}
