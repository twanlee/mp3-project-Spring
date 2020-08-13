package com.meo.mp3.repositories;

import com.meo.mp3.models.songs.Playlist;
import com.meo.mp3.models.songs.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends CrudRepository<Playlist, Long> {
    List<Playlist> findPlaylistsByUserId(Long userId);
}
