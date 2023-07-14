package com.djroche.labelleEtoile.dtos;

import com.djroche.labelleEtoile.entities.User;
import com.djroche.labelleEtoile.dtos.UserDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMapper {
    public static User fromDto(UserDto userDto) {
        User user = new User();
        if (userDto.getId() != null) {
            user.setId(userDto.getId());
        }
        if (userDto.getUsername() != null){
            user.setUsername(userDto.getUsername());
        }
        if (userDto.getPassword() != null) {
            user.setPassword(userDto.getPassword());
        }
        user.setAdmin(userDto.getAdmin());
        return user;
    }
}