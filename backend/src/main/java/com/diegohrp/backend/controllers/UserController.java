package com.diegohrp.backend.controllers;

import com.diegohrp.backend.dtos.Error;
import com.diegohrp.backend.dtos.UserData;
import com.diegohrp.backend.dtos.UserPublicData;
import com.diegohrp.backend.entities.User;
import com.diegohrp.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody UserData data) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.add(new User(data)));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(new Error(HttpStatus.NOT_ACCEPTABLE.toString(), e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        UserPublicData foundUser = userService.getById(id);
        if (foundUser != null) {
            return ResponseEntity.status(HttpStatus.OK).body(foundUser);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Error(HttpStatus.NOT_FOUND.toString(), "This user does not exist"));
    }
}
