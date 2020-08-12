package com.meo.mp3.security.jwt;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {
    private Long id;
    private String token;
    private String type = "Bearer";
    private String email;
    private String firstName;
    private String lastName;
    private Collection<? extends GrantedAuthority> roles;
    public JwtResponse(String accessToken, Long id, String email, String firstName, String lastName, Collection<? extends GrantedAuthority> roles){
        this.token = accessToken;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAccessToken() {
        return token;
    }
    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }
}
