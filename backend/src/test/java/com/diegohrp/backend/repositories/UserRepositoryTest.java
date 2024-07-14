package com.diegohrp.backend.repositories;

import com.diegohrp.backend.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {
    @Autowired
    UserRepository repository;

    @Test
    public void testFindByEmail() {
        User user = repository.save(new User(
                1L,
                "example_name",
                "example_lastname",
                "example_username",
                "mail@example.com",
                "passwordExample"));
        Optional<User> foundUser = repository.findByEmail(user.getEmail());
        assertTrue(foundUser.isPresent());
        assertEquals(user, foundUser.get());

    }
}