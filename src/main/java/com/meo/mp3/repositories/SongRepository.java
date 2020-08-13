package com.meo.mp3.repositories;

import com.meo.mp3.models.songs.Playlist;
import com.meo.mp3.models.songs.Song;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SongRepository extends CrudRepository<Song,Long> {
    List<Song> getSongsByUserId(Long userId);
    List<Song> getSongsByNameContains(String songName);

//    @Query("select s from Song s order by s.postTime")
    List<Song> findTop10ByOrderByPostTime();
    List<Song> findTop10ByOrderByPostTimeDesc();

//    @Query("select s from song s join s.s_play  p where s.id = p.id")
//    List<Song> getAllByS_playlist(Playlist playlist);
}
