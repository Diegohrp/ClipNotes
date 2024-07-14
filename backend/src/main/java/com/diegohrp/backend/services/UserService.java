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
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public UserPublicData add(User user) {
        Optional<User> existingUser = repository.findByEmailOrUsername(user.getEmail(), user.getUsername());
        if (existingUser.isPresent()) {
            if (existingUser.get().getEmail().equals(user.getEmail())) {
                throw new RuntimeException("This email already exists");
            }
            throw new RuntimeException("This username already exists");
        }

        return new UserPublicData(repository.save(user));
    }

    public UserPublicData getById(Long id) {
        Optional<User> userFound = userRepository.findById(id);
        return userFound.map(UserPublicData::new).orElse(null);
    }
}
