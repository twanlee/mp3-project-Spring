package com.meo.mp3.comparator;

import com.meo.mp3.models.songs.Song;

import java.util.Comparator;

public class SongLikesComparator implements Comparator<Song> {
    @Override
    public int compare(Song song, Song t1) {
        if (song.getReview().getLikes() == t1.getReview().getLikes()) {
            return 0;
        } else if (song.getReview().getLikes() < t1.getReview().getLikes()) {
            return 1;
        } else return -1;
    }
}
