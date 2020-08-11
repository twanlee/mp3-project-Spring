package com.meo.mp3.services;

import com.meo.mp3.models.songs.Song;

import java.util.List;

public interface SongService extends IService<Song> {
    List<Song> getSongsByUserId(Long id);
    List<Song> getSongsByNameContains(String songName);
}
