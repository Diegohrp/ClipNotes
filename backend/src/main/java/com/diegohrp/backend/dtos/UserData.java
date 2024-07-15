package com.diegohrp.backend.dtos;

import com.diegohrp.backend.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public record UserData(@NotBlank String name,
                       @NotBlank String lastName,
                       @NotBlank String username,
                       @NotBlank @Email String email,
                       @NotBlank @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$") String password) {

    public UserData(User user) {
        this(user.getName(), user.getLastName(), user.getUsername(), user.getEmail(), user.getPassword());
    }
}
