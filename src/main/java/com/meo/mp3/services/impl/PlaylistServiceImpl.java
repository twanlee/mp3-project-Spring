package com.meo.mp3.services.impl;

import com.meo.mp3.models.songs.Playlist;
import com.meo.mp3.repositories.PlaylistRepository;
import com.meo.mp3.services.IPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PlaylistServiceImpl implements IPlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Override
    public List<Playlist> findAll() {
        return (List<Playlist>) playlistRepository.findAll();
    }

    @Override
    public Playlist findById(Long id) {
        return playlistRepository.findById(id).get();
    }

    @Override
    public Playlist save(Playlist model) {
        return playlistRepository.save(model);
    }

    @Override
    public Playlist delete(Long id) {
        Playlist playlist = findById(id);
        playlistRepository.deleteById(id);
        return playlist;
    }
}
