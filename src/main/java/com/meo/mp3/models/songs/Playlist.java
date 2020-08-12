package com.meo.mp3.models.songs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meo.mp3.models.users.account.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String imgUrl;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;
    @ManyToMany(cascade = CascadeType.ALL)
//    @JsonIgnore
    @JoinTable(name = "song_playlist",
            joinColumns = {@JoinColumn(name = "playlist_id")},
            inverseJoinColumns = {@JoinColumn(name = "song_id")})
    private List<Song> pl_songs;
}
