package com.meo.mp3.models.users.account;

import com.meo.mp3.models.songs.Song;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
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
    @OneToOne
    private Profile profile;

}
