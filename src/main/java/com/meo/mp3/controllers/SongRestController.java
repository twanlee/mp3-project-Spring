package com.meo.mp3.controllers;

import com.meo.mp3.models.songs.Song;
import com.meo.mp3.services.impl.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "localhost:8080")
@RestController
@RequestMapping("/api/song")
public class SongRestController {
    @Autowired
    private SongService songService;

    @RequestMapping(value = "/list",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Song> getList(){
        return songService.findAll();
    }

    @RequestMapping(value = "/{id}/detail",method = RequestMethod.GET , produces = {MediaType.APPLICATION_JSON_VALUE})
    public Song getById(@PathVariable("id") Long id){
        return songService.findById(id);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Song save(@RequestBody Song song){
        return songService.save(song);
    }

    @RequestMapping(value = "/{id}/delete",method = RequestMethod.DELETE,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Song delete(@PathVariable("id") Long id){
        return songService.delete(id);
    }

}
