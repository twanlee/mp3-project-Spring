package com.meo.mp3.services.impl;

import com.meo.mp3.models.songs.Song;
import com.meo.mp3.repositories.SongRepository;
import com.meo.mp3.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SongService implements IService<Song> {
    @Autowired
    private SongRepository songRepository;
    @Override
    public List<Song> findAll() {
        return (List<Song>) songRepository.findAll();
    }

    @Override
    public Song findById(Long id) {
        return songRepository.findById(id).get();
    }

    @Override
    public Song save(Song model) {
        return songRepository.save(model);
    }

    @Override
    public Song delete(Long id) {
        Song song = findById(id);
        songRepository.delete(song);
        return song;
    }
}
