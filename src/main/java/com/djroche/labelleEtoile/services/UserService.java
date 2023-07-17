package com.djroche.labelleEtoile.services;

import com.djroche.labelleEtoile.dtos.UserDto;
import com.djroche.labelleEtoile.entities.User;
import com.djroche.labelleEtoile.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/*
* This class separates the conversion methods from the business logic methods and uses Java streams
* for the getAllUsers() method.
* */
@Service
@Transactional
// UserService class with updated methods using DTOs
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //
    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setAdmin(user.getAdmin());
        return userDto;
    }

    private User convertToEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setAdmin(userDto.getAdmin());
        return user;
    }

    // uses the UserDto class instead of the User entity class in the createUser() method
    public UserDto createUser(UserDto userDto) {
        User user = convertToEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }

    public UserDto getUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        return  user.map(this::convertToDto).orElse(null);
    }

    public void updateUser(UserDto userDto) {
        User user = convertToEntity(userDto);
        userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return null;
        }
        return convertToDto(user);
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
