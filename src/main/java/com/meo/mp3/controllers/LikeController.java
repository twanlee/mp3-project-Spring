package com.meo.mp3.controllers;

import com.meo.mp3.models.interactive.Review;
import com.meo.mp3.models.songs.Playlist;
import com.meo.mp3.models.songs.Song;
import com.meo.mp3.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class LikeController {

    @Autowired
    LikeService likeService;

    @GetMapping("/likeSong/{songId}/{userId}")
    public ResponseEntity<Review> likeSong(@PathVariable Long songId, @PathVariable Long userId) {
        Song song = likeService.likeASong(songId, userId);
        return new ResponseEntity<>(song.getReview(), HttpStatus.OK);
    }

    @GetMapping("/likePlaylist/{playlistId}/{userId}")
    public ResponseEntity<Review> likePlaylist(@PathVariable Long playlistId, @PathVariable Long userId) {
        Playlist playlist = likeService.likeAPlaylist(playlistId, userId);
        return new ResponseEntity<>(playlist.getReview(), HttpStatus.OK);
    }
}
