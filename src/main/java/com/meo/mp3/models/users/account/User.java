package com.meo.mp3.models.users.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Email
    private String email;

    @Size(min = 6)
    private String password;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Role role;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Profile profile;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
