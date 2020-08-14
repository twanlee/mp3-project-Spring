package com.meo.mp3.controllers;

import com.meo.mp3.models.artist.Artist;
import com.meo.mp3.services.ArtistService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping("/api/artist")
public class ArtistRestController {
    @Autowired
    private ArtistService artistServiceImpl;

    @RequestMapping(value = "/list",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Artist> getList(){
        return artistServiceImpl.findAll();
    }

    @RequestMapping(value = "/{id}/detail",method = RequestMethod.GET , produces = {MediaType.APPLICATION_JSON_VALUE})
    public Artist getById(@PathVariable("id") Long id){
        return artistServiceImpl.findById(id);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Artist save(@RequestBody Artist artist){
        return artistServiceImpl.save(artist);
    }

    @RequestMapping(value = "/{id}/delete",method = RequestMethod.DELETE,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Artist delete(@PathVariable("id") Long id){
        return artistServiceImpl.delete(id);
    }
}
