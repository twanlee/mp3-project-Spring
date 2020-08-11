package com.meo.mp3.repositories;

import com.meo.mp3.models.songs.Song;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SongRepository extends CrudRepository<Song,Long> {
    List<Song> getSongsByUserId(Long userId);
    List<Song> getSongsByNameContains(String songName);
}
