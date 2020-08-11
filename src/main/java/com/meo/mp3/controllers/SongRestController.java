package com.meo.mp3.controllers;

import com.meo.mp3.models.songs.Song;
import com.meo.mp3.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

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
    public Song getById(@PathVariable("id") Long id){
        return songServiceImpl.findById(id);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Song save(@RequestBody Song song){
        return songServiceImpl.save(song);
    }

    @RequestMapping(value = "/{id}/delete",method = RequestMethod.DELETE,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Song delete(@PathVariable("id") Long id){
        return songServiceImpl.delete(id);
    }

}
