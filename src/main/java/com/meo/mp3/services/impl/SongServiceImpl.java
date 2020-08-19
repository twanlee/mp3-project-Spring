package com.meo.mp3.services.impl;
import com.meo.mp3.comparator.SongLikesComparator;
import com.meo.mp3.comparator.SongViewsComparator;
import com.meo.mp3.models.interactive.Comment;
import com.meo.mp3.models.interactive.Review;
import com.meo.mp3.models.songs.Playlist;
import com.meo.mp3.models.songs.Song;
import com.meo.mp3.repositories.SongRepository;
import com.meo.mp3.services.CommentService;
import com.meo.mp3.services.ReviewService;
import com.meo.mp3.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private CommentService commentService;

    private SongLikesComparator likesComparator = new SongLikesComparator();
    private SongViewsComparator songViewsComparator = new SongViewsComparator();

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
        if  (model.getReview() == null) {
            Review review = reviewService.createNew();
            model.setReview(review);
        }
        return songRepository.save(model);
    }

    @Override
    public Song delete(Long id) {
        Song song = findById(id);
        List<Comment> comments = commentService.findAllBySongId(id);
        for (Comment cmt : comments) {
            commentService.delete(cmt.getId());
        }
//        Review review = song.getReview();
//        reviewService.delete(review);
//        song.setReview(null);
        List<Playlist> playlists = song.getS_playlist();
        playlists.removeAll(playlists);
        song.setS_playlist(playlists);
        songRepository.save(song);
        songRepository.delete(song);
        return null;
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

    @Override
    public List<Song> getTop10SongByLikes() {
        List<Song> songList = (List<Song>) songRepository.findAll();
        Collections.sort(songList,likesComparator);
        List<Song> topTen = new ArrayList<>();
        for(int i=0; i<songList.size(); i++){
            if(i == 10){
                return topTen;
            }
            topTen.add(songList.get(i));
        }
        return topTen;
    }

    @Override
    public List<Song> getTop10SongByViews() {
        List<Song> songList = (List<Song>) songRepository.findAll();
        Collections.sort(songList,songViewsComparator);
        List<Song> topTen = new ArrayList<>();
        for(int i=0; i<songList.size(); i++){
            if(i == 10){
                return topTen;
            }
            topTen.add(songList.get(i));
        }
        return topTen;
    }

    @Override
    public Song theBestSong() {
        List<Song> songList = (List<Song>) songRepository.findAll();
        Collections.sort(songList,likesComparator);
        Song song = songList.get(0);
        return song;
    }
}
