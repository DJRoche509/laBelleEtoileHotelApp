package com.djroche.labelleEtoile.controllers;

import com.djroche.labelleEtoile.entities.User;
import com.djroche.labelleEtoile.dtos.UserDto;
import com.djroche.labelleEtoile.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/")
    public UserDto createUser(@RequestBody UserDto userDto) {
        String passHash = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(passHash);
        User createdUser = userService.createUser(userDto);
        return userService.convertToDto(createdUser);
    }

    @GetMapping("/{username}")
    public UserDto getUserByUsername(@PathVariable String username) {
        UserDto userDto = userService.getUserByUsername(username);
        return userDto;
    }

    @GetMapping("/")
    public List<UserDto> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return users;
    }
}
