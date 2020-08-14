package com.meo.mp3.controllers;

import com.meo.mp3.models.songs.Song;
import com.meo.mp3.services.IUserService;
import com.meo.mp3.models.users.account.User;
import com.meo.mp3.services.IUserService;
import com.meo.mp3.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/song")
public class SongRestController {
    Long user_id;
    @Autowired
    private SongService songService;
    @Autowired
    private IUserService userService;
    @PostMapping("/user_id")
    @ResponseBody
    public void getUser_id(@RequestBody Long id){
        user_id = id;
    }
    @RequestMapping(value = "/list",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Song>> getList(){
        List<Song> songs = songService.findAll();
        if (songs.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/detail",method = RequestMethod.GET , produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Song> getById(@PathVariable("id") Long id) {
        Song song = this.songService.findById(id);
        if(song == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } return new ResponseEntity<>(song,HttpStatus.OK);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Song save(@RequestBody Song song){
        song.setPostTime(new Timestamp(System.currentTimeMillis()));
        User user = userService.findById(user_id);
        song.setUser(user);
        return songService.save(song);
    }

    @RequestMapping(value = "/{id}/delete",method = RequestMethod.DELETE,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Song delete(@PathVariable("id") Long id){
        return songService.delete(id);
    }

    @GetMapping("/{songName}/search")
    public ResponseEntity<List<Song>> findSongByName(@PathVariable String songName){
        List<Song> songList = songService.getSongsByNameContains(songName);
        return new ResponseEntity<List<Song>>(songList, HttpStatus.OK);
    }

    @GetMapping("/topten")
    public ResponseEntity<List<Song>> getTenSongsByPostTime(){
        List<Song> songList = songService.getTop10SongByPostTime();
        return new ResponseEntity<List<Song>>(songList, HttpStatus.OK);
    }
}
