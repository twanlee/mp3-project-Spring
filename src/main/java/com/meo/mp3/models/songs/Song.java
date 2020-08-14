package com.meo.mp3.models.songs;

import com.meo.mp3.models.artist.Artist;
import com.meo.mp3.models.users.account.User;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.*;
import java.sql.Timestamp;
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
    private Timestamp postTime;
    public Song() {
        this.postTime = new java.sql.Timestamp(System.currentTimeMillis());
    }
    @Column(columnDefinition = "TEXT")
    private String description;
    @ManyToMany
    @JoinTable(name = "author_song",
            joinColumns = {@JoinColumn(name = "song_id")},
            inverseJoinColumns = {@JoinColumn(name = "artist_id")})
    private List<Artist> s_authors;

    @ManyToMany
    @JoinTable(name = "singer_song",
            joinColumns = {@JoinColumn(name = "song_id")},
            inverseJoinColumns = {@JoinColumn(name = "artist_id" )})
    private List<Artist> s_singers;

    @ManyToMany(mappedBy = "pl_songs")
    private List<Playlist> s_playlist;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;
}
