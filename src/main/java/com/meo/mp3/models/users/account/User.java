package com.meo.mp3.models.users.account;

import com.meo.mp3.models.interactive.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Set;

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

    @ManyToMany(mappedBy = "userSet")
    private Set<Review> reviewSet;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
