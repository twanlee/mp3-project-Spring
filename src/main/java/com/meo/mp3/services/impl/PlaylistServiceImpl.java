package com.meo.mp3.services.Impl;

import com.meo.mp3.models.songs.Playlist;
import com.meo.mp3.models.songs.Song;
import com.meo.mp3.repositories.PlaylistRepository;
import com.meo.mp3.services.IPlaylistService;
import com.meo.mp3.services.IUserService;
import com.meo.mp3.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PlaylistServiceImpl implements IPlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private SongService songService;

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

    @Override
    public List<Playlist> findPlaylistsByUserId(Long userId) {
        return playlistRepository.findPlaylistsByUserId(userId);
    }

    @Override
    public Playlist createPlaylist(Long userId, Playlist playlist) {
        playlist.setUser(userService.findById(userId));
        return playlistRepository.save(playlist);
    }

    @Override
    public Playlist addSongToPlaylist(Long playlistId, Long songId) {
        Playlist playlist = playlistRepository.findById(playlistId).get();
        playlist.getPl_songs().add(songService.findById(songId));
        return playlistRepository.save(playlist);
    }

    @Override
    public Playlist removeSongFromPlaylist(Long playlistId, Long songId) {
        Playlist playlist = playlistRepository.findById(playlistId).get();
        playlist.getPl_songs().remove(songService.findById(songId));
        return playlistRepository.save(playlist);
    }

}
