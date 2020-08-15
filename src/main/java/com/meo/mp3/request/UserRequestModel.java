package com.meo.mp3.request;

import lombok.Data;

@Data
public class UserRequestModel {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String avatarUrl;
}
