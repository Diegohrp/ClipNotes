package com.diegohrp.backend.services;

import com.diegohrp.backend.dtos.UserPublicData;
import com.diegohrp.backend.entities.User;
import com.diegohrp.backend.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;


    @Test
    @DisplayName("Shoud return a UserPublicData record with the data of the saved user")
    public void testsaveUser() {
        User user = this.returnUserExample();
        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserPublicData savedUser = userService.add(user);

        verify(userRepository, times(1)).save(any(User.class));
        assertNotNull(savedUser.id());
        assertEquals(user.getName(), savedUser.name());
        assertEquals(user.getLastName(), savedUser.lastName());
        assertEquals(user.getEmail(), savedUser.email());

    }

    @Test
    @DisplayName("Can't add a new user with an existing email")
    public void testUniqueEmail() {
        User existingUser = this.returnUserExample();
        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.of(existingUser));

        assertThrows(RuntimeException.class, () -> userService.add(existingUser));
    }


    User returnUserExample() {
        return new User(1L, "name", "lastName", "username", "email@mail.com", "password");
    }

}