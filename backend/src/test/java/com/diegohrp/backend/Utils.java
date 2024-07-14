package com.diegohrp.backend;

import com.diegohrp.backend.entities.User;

public class Utils {
    public static User getUserExample() {
        return new User(
                1L,
                "example_name",
                "example_lastname",
                "example_username",
                "mail@example.com",
                "passwordExample");
    }
}
