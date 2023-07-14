package com.djroche.labelleEtoile.dtos;

import com.djroche.labelleEtoile.entities.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    private Long id;
    private String username;
    private String password;
    @JsonProperty("admin")
    private boolean admin;

    // We could omit the if-statements if wanted. But, it's more cautious to avoid errors
    public UserDto (User user){
        if (user.getId() != null) {
            this.id = user.getId();
        }
        if (user.getUsername() != null){
            this.username = user.getUsername();
        }
        if (user.getPassword() != null) {
            this.password = user.getPassword();
        }
        // Ternary if-statement
        this.admin = user.getAdmin() != null ? user.getAdmin() : false;
    }

    public Boolean getAdmin() {
        return admin;
    }
}
