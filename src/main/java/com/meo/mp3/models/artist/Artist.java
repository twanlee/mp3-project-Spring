package com.meo.mp3.models.artist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meo.mp3.models.songs.Song;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    @Column(columnDefinition = "TEXT")
    private String information;
    @ManyToMany(mappedBy = "s_singers")
    @JsonIgnore
    private List<Song> songSings;

    @ManyToMany(mappedBy = "s_authors")
    @JsonIgnore
    private List<Song> authSongs;


}
