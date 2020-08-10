package com.meo.mp3.models.artist;

import com.meo.mp3.models.songs.Song;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String fullName;
    @Column(columnDefinition = "TEXT")
    private String information;
    @ManyToMany(mappedBy = "singers")
    private List<Song> songSings;

    @ManyToMany(mappedBy = "authors")
    private List<Song> authSongs;


}
