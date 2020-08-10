package com.meo.mp3.models.songs;

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
    @ManyToMany(cascade = CascadeType.ALL, fetch =  FetchType.LAZY)
    @JoinTable(name = "author_song",
            joinColumns = {@JoinColumn(name = "song_id")},
            inverseJoinColumns = {@JoinColumn(name = "artist_id")})
    private List<Song> s_authors;

    @ManyToMany(cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    @JoinTable(name = "singer_song",
            joinColumns = {@JoinColumn(name = "song_id")},
            inverseJoinColumns = {@JoinColumn(name = "artist_id" )})
    private List<Song> s_singers;

    @ManyToMany(mappedBy = "pl_songs")
    private List<Playlist> s_playlist;

}
