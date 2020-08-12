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

    @GetMapping("/{userId}/view")
    public ResponseEntity<?> getPlaylistByUserId(@PathVariable Long userId) {
        List<Playlist> playlists = playlistService.findPlaylistsByUserId(userId);
        return new ResponseEntity<>(playlists, HttpStatus.OK);
    }

    @PostMapping("/{userId}/create")
    public ResponseEntity<?> createPlaylist(@PathVariable Long userId, @RequestBody Playlist playlist) {
        Playlist playlist1 = playlistService.createPlaylist(userId, playlist);
        return new ResponseEntity<>(playlist1, HttpStatus.OK);
    }

    @PostMapping("/{playlistId}/add/{songId}/song")
    public ResponseEntity<?> addSongToPlaylist(@PathVariable Long playlistId, @PathVariable Long songId){
        Playlist playlist = playlistService.addSongToPlaylist(playlistId,songId);
        return new ResponseEntity<>(playlist,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllPlaylist(){
        List<Playlist> playlist = playlistService.findAll();
        return new ResponseEntity<>(playlist,HttpStatus.OK);
    }

}
