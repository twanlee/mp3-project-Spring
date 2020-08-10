package com.meo.mp3.services.impl;

import com.meo.mp3.models.artist.Artist;
import com.meo.mp3.repositories.ArtistRepository;
import com.meo.mp3.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService implements IService<Artist> {
    @Autowired
    private ArtistRepository artistRepository;
    @Override
    public List<Artist> findAll() {
        return (List<Artist>) artistRepository.findAll();
    }

    @Override
    public Artist findById(Long id) {
        return artistRepository.findById(id).get();
    }

    @Override
    public Artist save(Artist model) {
        return artistRepository.save(model);
    }

    @Override
    public Artist delete(Long id) {
        Artist artist = findById(id);
        artistRepository.delete(artist);
        return artist;
    }
}
