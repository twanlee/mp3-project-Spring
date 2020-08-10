package com.meo.mp3.models.songs;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "song_playlist",
            joinColumns = {@JoinColumn(name = "song_id")},
            inverseJoinColumns = {@JoinColumn(name = "playlist_id")})
    private List<Song> pl_songs;
}
