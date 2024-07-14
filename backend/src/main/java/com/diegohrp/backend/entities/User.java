package com.diegohrp.backend.entities;

import com.diegohrp.backend.dtos.UserData;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "last_name")
    private String lastName;
    private String username;
    private String email;
    private String password;

    public User(UserData user) {
        this.name = user.name();
        this.lastName = user.lastName();
        this.username = user.username();
        this.email = user.email();
        this.password = user.password();
    }
}
