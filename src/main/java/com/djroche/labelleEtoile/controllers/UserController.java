package com.djroche.labelleEtoile.controllers;

import com.djroche.labelleEtoile.dtos.UserDto;
import com.djroche.labelleEtoile.dtos.UserMapper;
import com.djroche.labelleEtoile.entities.User;
import com.djroche.labelleEtoile.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // POST /users
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        // Hash the password before saving it to the database
        String passHash = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(passHash);
        User user = UserMapper.fromDto(userDto);  // Convert UserDto to User
        UserDto savedUserDto = userService.createUser(user);
        return new ResponseEntity<>(savedUserDto, HttpStatus.CREATED);
    }

    // GET /users/{username}
    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        UserDto userDto = userService.getUserByUsername(username);
        if (userDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDto);
    }

    // GET /users
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtos = userService.getAllUsers();
        if (userDtos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDtos);
    }
}
