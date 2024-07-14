package com.diegohrp.backend.repositories;

import com.diegohrp.backend.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.diegohrp.backend.Utils.getUserExample;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {
    @Autowired
    UserRepository repository;

    @Test
    public void testFindByEmail() {
        User user = repository.save(getUserExample());
        Optional<User> foundUser = repository.findByEmail(user.getEmail());
        assertTrue(foundUser.isPresent());

    }

    @Test
    public void testFindByUsername() {
        User user = repository.save(getUserExample());
        Optional<User> foundUser = repository.findByUsername(user.getUsername());
        assertTrue(foundUser.isPresent());
    }

    @Test
    public void testFindByEmailOrUsername() {
        List<User> users = new ArrayList<>();
        User user = repository.save(getUserExample());
        User user2 = getUserExample();
        user2.setEmail("another@mail.com");
        User user3 = getUserExample();
        user3.setUsername("another_username");
        users.add(user);
        users.add(user2);
        users.add(user3);

        for (int i = 0; i < 3; i++) {
            Optional<User> foundUser = repository.findByEmailOrUsername(
                    users.get(i).getEmail(),
                    users.get(i).getUsername()
            );
            assertTrue(foundUser.isPresent());
        }

    }
}