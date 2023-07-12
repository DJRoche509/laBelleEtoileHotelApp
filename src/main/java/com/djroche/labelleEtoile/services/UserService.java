package com.djroche.labelleEtoile.services;

import com.djroche.labelleEtoile.entities.User;
import com.djroche.labelleEtoile.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(String username, String password, boolean admin) {
        User user = new User();
        user
        return userRepository.save(user);
    }
}
