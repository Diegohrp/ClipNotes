package com.diegohrp.backend.controllers;

import com.diegohrp.backend.dtos.UserData;
import com.diegohrp.backend.dtos.UserPublicData;
import com.diegohrp.backend.entities.User;
import com.diegohrp.backend.services.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static com.diegohrp.backend.Utils.getUserExample;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    private UserService userService;


    @Test
    public void testRegisterUser() throws Exception {
        User savedUser = getUserExample();
        UserData data = new UserData(savedUser);

        when(userService.add(any(User.class))).thenReturn(new UserPublicData(savedUser));

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(data)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(savedUser.getId()))
                .andExpect(jsonPath("$.name").value(savedUser.getName()))
                .andExpect(jsonPath("$.lastName").value(savedUser.getLastName()))
                .andExpect(jsonPath("$.username").value(savedUser.getUsername()))
                .andExpect(jsonPath("$.email").value(savedUser.getEmail()));
    }

    @Test
    @DisplayName("Can't register a user with an existing email or username")
    public void testRegisterInvalidUser() throws Exception {
        invalidUser("This email already exists");
        invalidUser("This username already exists");
    }

    @Test
    public void testGetUser() throws Exception {
        UserPublicData foundUser = new UserPublicData(getUserExample());
        when(userService.getById(any(Long.class))).thenReturn(foundUser);

        mockMvc.perform(get("/users/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(foundUser.id()))
                .andExpect(jsonPath("$.name").value(foundUser.name()))
                .andExpect(jsonPath("$.lastName").value(foundUser.lastName()))
                .andExpect(jsonPath("$.username").value(foundUser.username()))
                .andExpect(jsonPath("$.email").value(foundUser.email()));
    }

    @Test
    void testUserNotFound() throws Exception {
        when(userService.getById(any(Long.class))).thenReturn(null);

        mockMvc.perform(get("/users/{id}", 23L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value(HttpStatus.NOT_FOUND.toString()))
                .andExpect(jsonPath("$.message").value("This user does not exist"));
    }


    public void invalidUser(String errMsg) throws Exception {
        UserData data = new UserData(getUserExample());
        when(userService.add(any(User.class))).thenThrow(new RuntimeException(errMsg));
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(data)))
                .andExpect(status().isNotAcceptable())
                .andExpect(jsonPath("$.error").value(HttpStatus.NOT_ACCEPTABLE.toString()))
                .andExpect(jsonPath("$.message").value(errMsg));
    }


}