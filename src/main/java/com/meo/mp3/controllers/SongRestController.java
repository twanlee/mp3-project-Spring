package com.meo.mp3.controllers;

import com.meo.mp3.models.songs.Song;
import com.meo.mp3.services.IUserService;
import com.meo.mp3.models.users.account.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import java.sql.Timestamp;
import java.util.List;
import com.meo.mp3.services.SongService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/song")
public class SongRestController {
    private Long user_id;

    @Autowired
    private IUserService userService;
    private SongService songService;

    @PostMapping("/user_id")
    @ResponseBody
    public void getUser_id(@RequestBody Long id){
        user_id = id;
    }
    @RequestMapping(value = "/list",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Song> getList(){
        return songService.findAll();
    }

    @RequestMapping(value = "/{id}/detail",method = RequestMethod.GET , produces = {MediaType.APPLICATION_JSON_VALUE})
    public Song getById(@PathVariable("id") Long id) {
        Song song = songService.findById(id);
        return songService.save(song);
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

    @GetMapping("/{id}/songs")
    public ResponseEntity<List<Song>> getAllSongByUser(@PathVariable Long id){
        List<Song> songList = songService.getSongsByUserId(id);
        return new ResponseEntity<List<Song>>(songList, HttpStatus.OK);
    }

    @GetMapping("/topsix")
    public ResponseEntity<List<Song>> getSixSongsByPostTime(){
        List<Song> songList = songService.getTop6SongByPostTime();
        return new ResponseEntity<List<Song>>(songList, HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<List<String>> getAllSongsName(){
        List<String> songsName = songService.getAllSongsName();
        return new ResponseEntity<List<String>>(songsName, HttpStatus.OK);
    }
}
