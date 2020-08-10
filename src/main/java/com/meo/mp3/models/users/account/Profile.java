package com.meo.mp3.models.users.account;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String avatarUrl;
    private String phoneNumber;
    private Date birthday;
    private String gender;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;
}
