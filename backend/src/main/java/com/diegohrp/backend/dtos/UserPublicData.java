package com.diegohrp.backend.dtos;

import com.diegohrp.backend.entities.User;

public record UserPublicData(Long id, String name, String lastName, String username, String email) {

    public UserPublicData(User user) {
        this(user.getId(), user.getName(), user.getLastName(), user.getUsername(), user.getEmail());
    }
}
