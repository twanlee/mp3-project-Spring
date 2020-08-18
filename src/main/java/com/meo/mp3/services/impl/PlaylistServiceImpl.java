package com.meo.mp3.services.impl;

import com.meo.mp3.comparator.PlaylistLikesComparator;
import com.meo.mp3.comparator.PlaylistViewsComparator;
import com.meo.mp3.models.songs.Playlist;
import com.meo.mp3.repositories.PlaylistRepository;
import com.meo.mp3.response.PlaylistResponse;
import com.meo.mp3.services.PlaylistService;
import com.meo.mp3.services.ReviewService;
import com.meo.mp3.services.UserService;
import com.meo.mp3.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class PlaylistServiceImpl implements PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private SongService songService;
    @Autowired
    private ReviewService reviewService;

    private PlaylistLikesComparator playlistLikesComparator = new PlaylistLikesComparator();
    private PlaylistViewsComparator playlistViewsComparator = new PlaylistViewsComparator();

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
        playlist.setReview(reviewService.createNew());
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

    @Override
    public List<Playlist> getTop9PlaylistByLikes() {
        List<Playlist> playlists = findAll();
        List<Playlist> topTen = new ArrayList<>();
        Collections.sort(playlists,playlistLikesComparator);
        for(int i=0; i<playlists.size(); i++){
            if(i == 9){
                return topTen;
            }
            topTen.add(playlists.get(i));
        }
        return topTen;
    }

    @Override
    public List<Playlist> getTop9PlaylistByViews() {
        List<Playlist> playlists = findAll();
        List<Playlist> topTen = new ArrayList<>();
        Collections.sort(playlists,playlistViewsComparator);
        for(int i=0; i<playlists.size(); i++){
            if(i == 9){
                return topTen;
            }
            topTen.add(playlists.get(i));
        }
        return topTen;
    }

    @Override
    public PlaylistResponse convertToResponse(Playlist playlist) {
        PlaylistResponse response = new PlaylistResponse();
        response.setId(playlist.getId());
        response.setTitle(playlist.getTitle());
        response.setUserCreate(playlist.getUser().getProfile().getFirstName() + " "+ playlist.getUser().getProfile().getLastName());
        response.setImgUrl(playlist.getImgUrl());
        response.setReview(playlist.getReview());
        response.setUserId(playlist.getUser().getId());

        return response;
    }

    @Override
    public List<PlaylistResponse> convertToListResponse(List<Playlist> playlists) {
        List<PlaylistResponse> playlistResponses = new ArrayList<>();
        for (Playlist playlist : playlists){
            playlistResponses.add(convertToResponse(playlist));
        }
        return playlistResponses;
    }
}
