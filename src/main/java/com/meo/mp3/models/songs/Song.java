package com.meo.mp3.models.songs;

import lombok.Data;

import javax.persistence.Entity;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String lyric;
    private String fileUrl;
    private String imageUrl;
    @Column(columnDefinition = "TEXT")
    private String description;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "author_song",
            joinColumns = {@JoinColumn(name = "song_id")},
            inverseJoinColumns = {@JoinColumn(name = "artist_id")})
    private List<Song> authors;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "singer_song",
            joinColumns = {@JoinColumn(name = "song_id")},
            inverseJoinColumns = {@JoinColumn(name = "artist_id" )})
    private List<Song> singers;

    @ManyToMany(mappedBy = "pl_songs")
    private List<Playlist> s_playlist;

}
