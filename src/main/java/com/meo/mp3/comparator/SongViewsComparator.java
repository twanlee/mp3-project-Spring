package com.meo.mp3.comparator;

import com.meo.mp3.models.songs.Song;

import java.util.Comparator;

public class SongViewsComparator implements Comparator<Song> {

    @Override
    public int compare(Song song, Song t1) {
        if (song.getReview().getLikes() == t1.getReview().getViews()) {
            return 0;
        } else if (song.getReview().getViews() < t1.getReview().getViews()) {
            return 1;
        } else return -1;
    }
}
