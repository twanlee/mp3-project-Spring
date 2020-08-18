package com.meo.mp3.controllers;

import com.meo.mp3.models.interactive.Review;
import com.meo.mp3.models.songs.Playlist;
import com.meo.mp3.models.songs.Song;
import com.meo.mp3.response.PlaylistResponse;
import com.meo.mp3.services.PlaylistService;
import com.meo.mp3.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/playlist")
public class PlaylistRestController {

    @Autowired
    private PlaylistService playlistService;

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
    @GetMapping("/all")
    public ResponseEntity<List<PlaylistResponse>> getAllPlaylist(){
        List<Playlist> playlists = playlistService.findAll();
        List<PlaylistResponse> playlistResponses = playlistService.convertToListResponse(playlists);
        return new ResponseEntity<>(playlistResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}/songs")
    public ResponseEntity<List<Song>> getAllSongFromPlaylist(@PathVariable Long id){
        Playlist playlist = playlistService.findById(id);
        List<Song> songs = playlist.getPl_songs();
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<PlaylistResponse> getPlayListInfor(@PathVariable Long id) {
//        Tăng view lên mỗi lần gọi
        Playlist pl = playlistService.findById(id);
        Review review = pl.getReview();
        review.setViews(review.getViews()+1);
        pl.setReview(review);
        playlistService.save(pl);
//
//        //Gán thành một đối tượng response trả về cho Front End
        PlaylistResponse response = this.playlistService.convertToResponse(pl);
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
    @PostMapping("/{id}/add/song")
    public ResponseEntity<Playlist> addSong(@PathVariable("id") Long id, @RequestBody List<Long> songsId){
        Playlist playlist = playlistService.findById(id);
        List<Song> songs = playlist.getPl_songs() ;
        for (Long aLong : songsId) {
            songs.add(songService.findById(aLong));
        }
        playlist.setPl_songs(songs);
        playlistService.save(playlist);
        return new ResponseEntity<Playlist>(playlist,HttpStatus.OK);
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deletePlaylist(@PathVariable Long id){
        playlistService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{playlistId}/song/{songId}/delete")
    public ResponseEntity<?> removeSongFromPlaylist(@PathVariable Long playlistId, @PathVariable Long songId){
        Playlist playlist = playlistService.findById(playlistId);
        playlist.getPl_songs().remove(songService.findById(songId));
        playlistService.save(playlist);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
