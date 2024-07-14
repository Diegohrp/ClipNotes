package com.diegohrp.backend.services;

import com.diegohrp.backend.dtos.UserData;
import com.diegohrp.backend.dtos.UserPublicData;
import com.diegohrp.backend.entities.User;
import com.diegohrp.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserPublicData add(User user) {
        Optional<User> existingUser = repository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("This email already exists");
        }
        return new UserPublicData(repository.save(user));
    }
}
