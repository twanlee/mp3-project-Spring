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

    @Autowired
    private IUserService userService;
    @Autowired
    private SongService songService;

    @RequestMapping(value = "/{id}/detail",method = RequestMethod.GET , produces = {MediaType.APPLICATION_JSON_VALUE})
    public Song getById(@PathVariable("id") Long id) {
        Song song = songService.findById(id);
        return songService.save(song);
    }

    @RequestMapping(value = "/{user_id}/save",method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Song save(@RequestBody Song song, @PathVariable Long user_id){
        song.setPostTime(new Timestamp(System.currentTimeMillis()));
        User user = userService.findById(user_id);
        song.setUser(user);
        return songService.save(song);
    }

    @RequestMapping(value = "/{id}/delete",method = RequestMethod.DELETE,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        songService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
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

    @GetMapping("/top/ten/likes")
    public ResponseEntity<List<Song>> getTenSongsByLikes(){
        List<Song> songsName = songService.getTop10SongByLikes();
        return new ResponseEntity<List<Song>>(songsName, HttpStatus.OK);
    }

    @GetMapping("/top/ten/views")
    public ResponseEntity<List<Song>> getTenSongsByViews(){
        List<Song> songsName = songService.getTop10SongByViews();
        return new ResponseEntity<List<Song>>(songsName, HttpStatus.OK);
    }

    @GetMapping("/best")
    public ResponseEntity<Song> getTheBestSong(){
        Song song = songService.theBestSong();
        return new ResponseEntity<Song>(song,HttpStatus.OK);
    }
}
