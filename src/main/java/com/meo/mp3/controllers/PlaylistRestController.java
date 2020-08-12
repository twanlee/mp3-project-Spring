package com.meo.mp3.controllers;

import com.meo.mp3.models.songs.Playlist;
import com.meo.mp3.services.IPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/playlist")
public class PlaylistRestController {

    @Autowired
    private IPlaylistService playlistService;

    @GetMapping("/{userId}/playlist")
    public ResponseEntity<?> getPlaylistByUserId(@PathVariable Long userId){
        List<Playlist> playlists = playlistService.findPlaylistsByUserId(userId);
        return new ResponseEntity<>(playlists, HttpStatus.OK);
    }
}
