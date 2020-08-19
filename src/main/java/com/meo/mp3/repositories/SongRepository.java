package com.meo.mp3.repositories;

import com.meo.mp3.models.songs.Playlist;
import com.meo.mp3.models.songs.Song;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SongRepository extends CrudRepository<Song,Long> {
    List<Song> getSongsByUserId(Long userId);
    List<Song> getSongsByNameContains(String songName);
    List<Song> findTop10ByOrderByPostTimeDesc();
    List<Song> findTop6ByOrderByPostTimeDesc();
//    @Query(value = "select song_id from song_playlist WHERE playlist_id = :PL_Id", nativeQuery = true)
//    List<Song> findSongsByPlaylistId(@Param("PL_Id") Long pl_Id);
}
