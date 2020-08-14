package com.meo.mp3.controllers;

import com.meo.mp3.models.songs.Song;
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
    @Autowired
    private SongService songServiceImpl;

    @RequestMapping(value = "/list",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Song> getList(){
        return songServiceImpl.findAll();
    }

    @RequestMapping(value = "/{id}/detail",method = RequestMethod.GET , produces = {MediaType.APPLICATION_JSON_VALUE})
    public Song getById(@PathVariable("id") Long id) {
        return songServiceImpl.findById(id);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Song save(@RequestBody Song song){
        song.setPostTime(new Timestamp(System.currentTimeMillis()));
        return songServiceImpl.save(song);
    }

    @RequestMapping(value = "/{id}/delete",method = RequestMethod.DELETE,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Song delete(@PathVariable("id") Long id){
        return songServiceImpl.delete(id);
    }

    @GetMapping("/{songName}/search")
    public ResponseEntity<List<Song>> findSongByName(@PathVariable String songName){
        List<Song> songList = songServiceImpl.getSongsByNameContains(songName);
        return new ResponseEntity<List<Song>>(songList, HttpStatus.OK);
    }

    @GetMapping("/topten")
    public ResponseEntity<List<Song>> getTenSongsByPostTime(){
        List<Song> songList = songServiceImpl.getTop10SongByPostTime();
        return new ResponseEntity<List<Song>>(songList, HttpStatus.OK);
    }

    @GetMapping("/{id}/songs")
    public ResponseEntity<List<Song>> getAllSongByUser(@PathVariable Long id){
        List<Song> songList = songServiceImpl.getSongsByUserId(id);
        return new ResponseEntity<List<Song>>(songList, HttpStatus.OK);
    }

    @GetMapping("/topsix")
    public ResponseEntity<List<Song>> getSixSongsByPostTime(){
        List<Song> songList = songServiceImpl.getTop6SongByPostTime();
        return new ResponseEntity<List<Song>>(songList, HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<List<String>> getAllSongsName(){
        List<String> songsName = songServiceImpl.getAllSongsName();
        return new ResponseEntity<List<String>>(songsName, HttpStatus.OK);
    }
}
