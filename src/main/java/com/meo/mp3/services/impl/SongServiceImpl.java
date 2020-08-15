package com.meo.mp3.services.Impl;

import com.meo.mp3.models.songs.Playlist;
import com.meo.mp3.models.songs.Song;
import com.meo.mp3.repositories.SongRepository;
import com.meo.mp3.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongServiceImpl implements SongService {
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

    @Override
    public List<Song> getSongsByUserId(Long id) {
        return songRepository.getSongsByUserId(id);
    }

    @Override
    public List<Song> getSongsByNameContains(String songName) {
        return songRepository.getSongsByNameContains(songName);
    }

    @Override
    public List<Song> getTop10SongByPostTime() {
        return songRepository.findTop10ByOrderByPostTimeDesc();
    }

    @Override
    public List<Song> getTop6SongByPostTime() {
        return songRepository.findTop6ByOrderByPostTimeDesc();
    }

    @Override
    public List<Song> getSongFromPlaylist(Playlist playlist) {
        return null;
    }

    @Override
    public List<String> getAllSongsName() {
        List<String> songsName = new ArrayList<>();
        List<Song> songList = findAll();
        for (Song song: songList){
            songsName.add(song.getName());
        }
        return songsName;
    }
}
