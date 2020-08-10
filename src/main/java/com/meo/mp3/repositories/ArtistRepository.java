package com.meo.mp3.repositories;

import com.meo.mp3.models.artist.Artist;
import com.meo.mp3.models.songs.Playlist;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends CrudRepository<Artist,Long> {
}
