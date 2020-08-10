package com.meo.mp3.repositories;

import com.meo.mp3.models.songs.Song;
import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song,Long> {
}
