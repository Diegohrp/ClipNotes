package com.diegohrp.backend.dtos;

import com.diegohrp.backend.entities.User;

public record UserData(String name, String lastName, String username, String email, String password) {

    public UserData(User user) {
        this(user.getName(), user.getLastName(), user.getUsername(), user.getEmail(), user.getPassword());
    }
}
