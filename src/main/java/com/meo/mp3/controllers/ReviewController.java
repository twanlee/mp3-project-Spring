package com.meo.mp3.controllers;

import com.meo.mp3.models.interactive.Review;
import com.meo.mp3.models.interactive.ReviewResponse;
import com.meo.mp3.models.songs.Playlist;
import com.meo.mp3.models.songs.Song;
import com.meo.mp3.services.IPlaylistService;
import com.meo.mp3.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class ReviewController {

    @Autowired
    SongService songService;

    @Autowired
    IPlaylistService playlistService;

    @GetMapping("/getReview/song/{id}")
    public ResponseEntity<ReviewResponse> getReviewBySongId(@PathVariable Long id) {
        Song song = increaseViewsOfSong(id);
        ReviewResponse response = new ReviewResponse();
        response.setId(song.getReview().getId());
        response.setLikes(song.getReview().getLikes());
        response.setViews(song.getReview().getViews());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getReview/playlist/{id}")
    public ResponseEntity<ReviewResponse> getReviewByPlaylistId(@PathVariable Long id) {
        Playlist playlist = increaseViewsOfPlaylist(id);
        ReviewResponse response = new ReviewResponse();
        response.setId(playlist.getReview().getId());
        response.setLikes(playlist.getReview().getLikes());
        response.setViews(playlist.getReview().getViews());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public Song increaseViewsOfSong(Long id){
        Song song = songService.findById(id);
        int views = song.getReview().getViews() + 1;
        Review review = song.getReview();
        review.setViews(views);
        song.setReview(review);
        return songService.save(song);
    }

    public Playlist increaseViewsOfPlaylist(Long id){
        Playlist playlist = playlistService.findById(id);
        int views = playlist.getReview().getViews() + 1;
        Review review = playlist.getReview();
        review.setViews(views);
        playlist.setReview(review);
        return playlistService.save(playlist);
    }
}
