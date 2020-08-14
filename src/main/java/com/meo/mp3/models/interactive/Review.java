package com.meo.mp3.models.interactive;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meo.mp3.models.users.account.User;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Review {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int views;

    private int likes;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "review_user", joinColumns = {@JoinColumn(name = "review_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    @JsonIgnore
    private Set<User> userSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }
}
