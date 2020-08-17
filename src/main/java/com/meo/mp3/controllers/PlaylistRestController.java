package com.meo.mp3.controllers;

import com.meo.mp3.models.interactive.Review;
import com.meo.mp3.models.songs.Playlist;
import com.meo.mp3.models.songs.Song;
import com.meo.mp3.models.songs.Song;
import com.meo.mp3.repositories.SongRepository;
import com.meo.mp3.response.PlaylistResponse;
import com.meo.mp3.services.IPlaylistService;
import com.meo.mp3.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/playlist")
public class PlaylistRestController {

    @Autowired
    private IPlaylistService playlistService;

    @Autowired
    private SongService songService;

    @GetMapping("/{userId}/view")
    public ResponseEntity<?> getPlaylistByUserId(@PathVariable Long userId) {
        List<Playlist> playlists = playlistService.findPlaylistsByUserId(userId);
        return new ResponseEntity<>(playlists, HttpStatus.OK);
    }

    @PostMapping("/{userId}/create")
    public ResponseEntity<?> createPlaylist(@PathVariable Long userId, @RequestBody Playlist playlist) {
        Playlist playlist1 = playlistService.createPlaylist(userId, playlist);
        return new ResponseEntity<>(playlist1, HttpStatus.OK);
    }

    @PostMapping("/{playlistId}/add/{songId}/song")
    public ResponseEntity<?> addSongToPlaylist(@PathVariable Long playlistId, @PathVariable Long songId){
        Playlist playlist = playlistService.addSongToPlaylist(playlistId,songId);
        return new ResponseEntity<>(playlist,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlaylistResponse>> getAllPlaylist(){
        List<Playlist> playlist = playlistService.findAll();
        List<PlaylistResponse> playlistResponses = new ArrayList<>();
        for (Playlist pl : playlist) {
            PlaylistResponse response = new PlaylistResponse();
            response.setId(pl.getId());
            response.setTitle(pl.getTitle());
            response.setUserCreate(pl.getUser().getProfile().getFirstName() + " "+ pl.getUser().getProfile().getLastName());
            response.setImgUrl(pl.getImgUrl());
            response.setReview(pl.getReview());
            playlistResponses.add(response);
        }
        return new ResponseEntity<>(playlistResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<List<Song>> getAllSongFromPlaylist(@PathVariable Long id){
        Playlist playlist = playlistService.findById(id);
        List<Song> songs = playlist.getPl_songs();
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaylistResponse> getPlayListInfor(@PathVariable Long id) {
        //Tăng view lên mỗi lần gọi
        Playlist pl = playlistService.findById(id);
        Review review = pl.getReview();
        review.setViews(review.getViews()+1);
        pl.setReview(review);
        playlistService.save(pl);

        //Gán thành một đối tượng response trả về cho Front End
        PlaylistResponse response = new PlaylistResponse();
        response.setId(pl.getId());
        response.setTitle(pl.getTitle());
        response.setUserCreate(pl.getUser().getProfile().getFirstName() + " "+ pl.getUser().getProfile().getLastName());
        response.setImgUrl(pl.getImgUrl());
        response.setReview(pl.getReview());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/top/ten/likes")
    public ResponseEntity<List<Playlist>> getTop9PlaylistsByLikes(){
        List<Playlist> playlists = playlistService.getTop9PlaylistByLikes();
        return new ResponseEntity<List<Playlist>>(playlists, HttpStatus.OK);
    }

    @GetMapping("/top/ten/views")
    public ResponseEntity<List<Playlist>> getTop9PlaylistsByViews(){
        List<Playlist> playlists = playlistService.getTop9PlaylistByViews();
        return new ResponseEntity<List<Playlist>>(playlists, HttpStatus.OK);
    }
}
