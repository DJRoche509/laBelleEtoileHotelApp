package com.djroche.labelleEtoile.services;

import com.djroche.labelleEtoile.dtos.UserDto;
import com.djroche.labelleEtoile.entities.User;
import com.djroche.labelleEtoile.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
// UserService class with updated methods using DTOs
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDto createUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setAdmin(userDto.getAdmin());
        User savedUser = userRepository.save(user);
        userDto.setId(savedUser.getId());
        return userDto;
    }

    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setAdmin(user.getAdmin());
        return userDto;
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setUsername(user.getUsername());
            userDto.setPassword(user.getPassword());
            userDto.setAdmin(user.getAdmin());
            userDtos.add(userDto);
        }
        return userDtos;
    }
}
