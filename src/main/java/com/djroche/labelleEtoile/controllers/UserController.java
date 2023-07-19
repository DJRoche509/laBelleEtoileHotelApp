package com.djroche.labelleEtoile.controllers;

import com.djroche.labelleEtoile.dtos.UserDto;
import com.djroche.labelleEtoile.services.UserService;
import com.djroche.labelleEtoile.services.UserServiceImpl;

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
    private UserServiceImpl userServiceImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // POST /users
    @PostMapping("/login")
    public List<String> userLogin(@RequestBody UserDto userDto) {
        return userServiceImpl.userLogin(userDto);
    }

    @GetMapping("/{id}")
    public String getUserDetails(@PathVariable("id") Long id, Model model) {
        UserDto user = userServiceImpl.getUserById(id);
        if (user == null) {
            return "redirect:/users";
        }
        model.addAttribute("user", user);
        return "userDetails";
    }

    @GetMapping("/register")
    public List<String> addUser(@RequestBody UserDto userDto) {
        String passHash = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(passHash);
        return userServiceImpl.addUser(userDto);
    }

    // GET /users
    @GetMapping("/")
    public String getAllUsers(Model model) {
        List<UserDto> users = userServiceImpl.getAllUsers();
        model.addAttribute("users", users);
        return "userList";
    }

    @GetMapping("/{id}/edit")
    public String editUserForm(@PathVariable("id") Long id, Model model) {
        UserDto user = userServiceImpl.getUserById(id);
        if (user == null) {
            return "redirect:/users";
        }
        model.addAttribute("userDto", user);
        return "userForm";
    }

    @PostMapping("/{id}/update")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("userDto") UserDto userDto) {
        userDto.setId(id);
        userServiceImpl.updateUser(userDto);
        return "redirect:/users/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userServiceImpl.deleteUserById(id);
        return "redirect:/users";
    }
}
