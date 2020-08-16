package com.meo.mp3.models.interactive;

import com.meo.mp3.models.songs.Playlist;
import com.meo.mp3.models.songs.Song;
import com.meo.mp3.models.users.account.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String content;
    private Timestamp commentTime;
    public Comment() {
        this.commentTime = new Timestamp(System.currentTimeMillis());
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "song_id")
    private Song song;

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;

}
