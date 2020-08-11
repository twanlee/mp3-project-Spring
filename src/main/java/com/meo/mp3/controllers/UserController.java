package com.meo.mp3.controllers;

import com.meo.mp3.models.songs.Song;
import com.meo.mp3.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
public class UserController{

    @Autowired
    private SongService songService;

    @GetMapping("/{id}/get-songs")
    public ResponseEntity<List<Song>> getAllSongByUser(@PathVariable Long id){
        List<Song> songList = songService.getSongsByUserId(id);
        return new ResponseEntity<List<Song>>(songList,HttpStatus.OK);
    }

}
