package com.meo.mp3.models.songs;

import lombok.Data;

import javax.persistence.*;

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

}
